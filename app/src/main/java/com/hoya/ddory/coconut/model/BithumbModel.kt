package com.hoya.ddory.coconut.model

import android.util.Base64
import com.hoya.ddory.coconut.cloud.BithumbClient
import com.hoya.ddory.coconut.cloud.response.Account
import com.hoya.ddory.coconut.cloud.response.Balance
import com.hoya.ddory.coconut.cloud.response.Orders
import io.reactivex.Single
import org.apache.commons.codec.binary.Hex
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

class BithumbModel(
    private val apiKey: String,
    private val apiSecret: String
) {
    fun getAccount(orderCurrency: String): Single<Account> {
        val params = hashMapOf(
            Pair("endpoint", INFO_ACCOUNT_URL),
            Pair("order_currency", orderCurrency)
        )
        val header = makeHeader(INFO_ACCOUNT_URL, params)
        return BithumbClient.getPrivateService()
            .getAccount(header, params)
    }

    fun getBalance(currency: String = "ALL"): Single<Balance> {
        val params = hashMapOf(
            Pair("endpoint", INFO_BALANCE_URL),
            Pair("currency", currency)
        )
        val header = makeHeader(INFO_BALANCE_URL, params)
        return BithumbClient.getPrivateService()
            .getBalance(header, params)
    }

    fun getOrders(id: String? = null, type: String? = null, orderCurrency: String): Single<Orders> {
        val params = hashMapOf(
            Pair("endpoint", INFO_ORDERS_URL),
            Pair("order_currency", orderCurrency)
        ).apply {
            id?.let {
                put("order_id", it)
            }
            type?.let {
                put("type", it)
            }
        }
        val header = makeHeader(INFO_ORDERS_URL, params)
        return BithumbClient.getPrivateService()
            .getOrders(header, params)
    }

    private fun makeHeader(url: String, param: HashMap<String, String>): HashMap<String, String> {
        val nonce = System.currentTimeMillis().toString()

        var formUrlEncoded = ""
        param.forEach { (key, value) ->
            formUrlEncoded += "$key=${value.replace("/", "%2F")}&"
        }
        val str = "$url;${formUrlEncoded.removeRange(
            formUrlEncoded.length - 1,
            formUrlEncoded.length
        )};$nonce"
        encrypted(str)

        return hashMapOf(
            Pair(HEADER_API_CLIENT_TYPE, "2"),
            Pair(HEADER_API_SIGN, encrypted(str)),
            Pair(HEADER_API_NONCE, nonce),
            Pair(HEADER_API_KEY, apiKey)
        )
    }

    private fun encrypted(text: String): String {
        val macData = Mac.getInstance(CRYPTO_ALGORITHM).let {
            it.init(SecretKeySpec(apiSecret.toByteArray(), CRYPTO_ALGORITHM))
            it.doFinal(text.toByteArray())
        }

        return String(Base64.encode(Hex().encode(macData), Base64.NO_WRAP))
    }

    companion object {
        private const val CRYPTO_ALGORITHM = "HmacSHA512"

        private const val HEADER_API_CLIENT_TYPE = "api-client-type"
        private const val HEADER_API_SIGN = "Api-Sign"
        private const val HEADER_API_NONCE = "Api-Nonce"
        private const val HEADER_API_KEY = "Api-Key"

        private const val INFO_ACCOUNT_URL = "/info/account"
        private const val INFO_BALANCE_URL = "/info/balance"
        private const val INFO_ORDERS_URL = "/info/orders"
    }
}
