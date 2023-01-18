package com.hoya.ddory.coconut.model

import android.content.Context
import android.util.Base64
import com.hoya.ddory.coconut.cloud.BithumbClient
import com.hoya.ddory.coconut.cloud.response.Balance
import com.hoya.ddory.coconut.cloud.response.OrderResult
import com.hoya.ddory.coconut.cloud.response.Order
import com.hoya.ddory.coconut.cloud.response.Account
import com.hoya.ddory.coconut.cloud.response.TransactionHistory
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.http.*
import org.apache.commons.codec.binary.Hex
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

class BithumbModel(context: Context) {

    private val apiKey: String by lazy {
        readAssets(context, "api_key")
    }

    private val apiSecret: String by lazy {
        readAssets(context, "api_secret")
    }

    private fun readAssets(context: Context, fileName: String): String {
        val inputStream = context.resources.assets.open(fileName)
        val key = ByteArray(32)
        inputStream.read(key)
        inputStream.close()
        return String(key)
    }

    // Public API
    suspend fun getTransactionHistory(orderCurrency: String): TransactionHistory {
        return BithumbClient()
            .httpClient
            .request("https://api.bithumb.com/public/transaction_history/${orderCurrency}_KRW?count=1") {
                method = HttpMethod.Get
            }
    }

    suspend fun getAccountKtor(orderCurrency: String): Account {
        val params = hashMapOf(
            Pair("endpoint", INFO_ACCOUNT_URL),
            Pair("order_currency", orderCurrency)
        )
        val headers = makeHeader(INFO_ACCOUNT_URL, params)
        return BithumbClient()
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

    suspend fun getBalanceKtor(currency: String = "ALL"): Balance {
        val params = hashMapOf(
            Pair("endpoint", INFO_BALANCE_URL),
            Pair("currency", currency)
        )
        val headers = makeHeader(INFO_BALANCE_URL, params)
        return BithumbClient()
            .httpClient
            .request("https://api.bithumb.com$INFO_BALANCE_URL") {
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

    suspend fun getOrdersKtor(
        id: String,
        orderCurrency: String,
        paymentCurrency: String = "KRW"
    ): Order {
        val params = hashMapOf(
            Pair("endpoint", INFO_ORDERS_URL),
            Pair("order_id", id),
            Pair("order_currency", orderCurrency),
            Pair("payment_currency", paymentCurrency)
        )
        val headers = makeHeader(INFO_ORDERS_URL, params)
        return BithumbClient()
            .httpClient
            .request("https://api.bithumb.com$INFO_ORDERS_URL") {
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

    suspend fun buyKtor(orderCurrency: String, units: Float): OrderResult {
        val params = hashMapOf(
            Pair("endpoint", TRADE_BUY_URL),
            Pair("units", units.toString()),
            Pair("order_currency", orderCurrency),
            Pair("payment_currency", "KRW")
        )
        val headers = makeHeader(TRADE_BUY_URL, params)
        return BithumbClient()
            .httpClient
            .request("https://api.bithumb.com$TRADE_BUY_URL") {
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

    suspend fun buyKtor(orderCurrency: String, price: Float, units: Float): OrderResult {
        val params = hashMapOf(
            Pair("endpoint", TRADE_PLACE),
            Pair("price", price.toString()),
            Pair("units", units.toString()),
            Pair("type", "bid"),
            Pair("order_currency", orderCurrency),
            Pair("payment_currency", "KRW")
        )
        val headers = makeHeader(TRADE_PLACE, params)
        return BithumbClient()
            .httpClient
            .request("https://api.bithumb.com$TRADE_PLACE") {
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

    suspend fun sellKtor(orderCurrency: String, units: Float): OrderResult {
        val params = hashMapOf(
            Pair("endpoint", TRADE_SELL_URL),
            Pair("units", units.toString()),
            Pair("order_currency", orderCurrency),
            Pair("payment_currency", "KRW")
        )
        val headers = makeHeader(TRADE_SELL_URL, params)
        return BithumbClient()
            .httpClient
            .request("https://api.bithumb.com$TRADE_SELL_URL") {
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

    suspend fun sellKtor(orderCurrency: String, price: Float, units: Float): OrderResult {
        val params = hashMapOf(
            Pair("endpoint", TRADE_PLACE),
            Pair("price", price.toString()),
            Pair("units", units.toString()),
            Pair("type", "ask"),
            Pair("order_currency", orderCurrency),
            Pair("payment_currency", "KRW")
        )
        val headers = makeHeader(TRADE_PLACE, params)
        return BithumbClient()
            .httpClient
            .request("https://api.bithumb.com$TRADE_PLACE") {
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

    private fun makeHeader(url: String, param: HashMap<String, String>): HashMap<String, String> {
        val nonce = System.currentTimeMillis().toString()

        var formUrlEncoded = ""
        param.forEach { (key, value) ->
            formUrlEncoded += "$key=${value.replace("/", "%2F")}&"
        }
        val str = "$url;${
            formUrlEncoded.removeRange(
                formUrlEncoded.length - 1,
                formUrlEncoded.length
            )
        };$nonce"
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
        private const val TRADE_PLACE = "/trade/place"
    }
}
