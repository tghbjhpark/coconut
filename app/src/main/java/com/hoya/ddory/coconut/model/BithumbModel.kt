package com.hoya.ddory.coconut.model

import android.util.Base64
import com.hoya.ddory.coconut.cloud.BithumbClient
import com.hoya.ddory.coconut.cloud.response.Account
import com.hoya.ddory.coconut.cloud.response.Balance
import com.hoya.ddory.coconut.cloud.response.Orders
import com.hoya.ddory.coconut.cloud.response.Trade
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.http.*
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

    suspend fun getAccountKtor(orderCurrency: String): com.hoya.ddory.coconut.cloud.response.ktor.Account {
        val params = hashMapOf(
            Pair("endpoint", INFO_ACCOUNT_URL),
            Pair("order_currency", orderCurrency)
        )
        val headers = makeHeader(INFO_ACCOUNT_URL, params)
        return com.hoya.ddory.coconut.cloud.ktor.BithumbClient()
            .httpClient
            .request("https://api.bithumb.com$INFO_ACCOUNT_URL") {
                method = HttpMethod.Post
                headers {
                    headers.forEach { (t, u) ->
                        append(t, u)
                    }
                }
                body = FormDataContent(Parameters.build {
                    params.forEach { (t, u) ->
                        append(t, u)
                    }
                })
            }
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

    fun getOrders(id: String, orderCurrency: String, paymentCurrency: String = "KRW"): Single<Orders> {
        val params = hashMapOf(
            Pair("endpoint", INFO_ORDERS_URL),
            Pair("order_id", id),
            Pair("order_currency", orderCurrency),
            Pair("payment_currency", paymentCurrency)
        )
        val header = makeHeader(INFO_ORDERS_URL, params)
        return BithumbClient.getPrivateService()
            .getOrders(header, params)
    }

    fun buy(orderCurrency: String, units: Float): Single<Trade> {
        val params = hashMapOf(
            Pair("endpoint", TRADE_BUY_URL),
            Pair("units", units.toString()),
            Pair("order_currency", orderCurrency),
            Pair("payment_currency", "KRW")
        )
        val header = makeHeader(TRADE_BUY_URL, params)
        return BithumbClient.getTradeService()
            .buyMarketPrice(header, params)
    }

    fun sell(orderCurrency: String, units: Float): Single<Trade> {
        val params = hashMapOf(
            Pair("endpoint", TRADE_SELL_URL),
            Pair("units", units.toString()),
            Pair("order_currency", orderCurrency),
            Pair("payment_currency", "KRW")
        )
        val header = makeHeader(TRADE_SELL_URL, params)
        return BithumbClient.getTradeService()
            .sellMarketPrice(header, params)
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
        private const val INFO_ORDERS_URL = "/info/order_detail"

        private const val TRADE_BUY_URL = "/trade/market_buy"
        private const val TRADE_SELL_URL = "/trade/market_sell"
    }
}
