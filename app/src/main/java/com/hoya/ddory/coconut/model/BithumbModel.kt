package com.hoya.ddory.coconut.model

import android.util.Base64
import org.apache.commons.codec.binary.Hex
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

class BithumbModel {

    private fun encrypted(text: String, secret: ByteArray): String {
        val macData = Mac.getInstance(CRYPTO_ALGORITHM).let {
            it.init(SecretKeySpec(secret, CRYPTO_ALGORITHM))
            it.doFinal(text.toByteArray())
        }

        return String(Base64.encode(Hex().encode(macData), Base64.NO_WRAP))
    }

    companion object {
        private const val CRYPTO_ALGORITHM = "HmacSHA512"
    }
}
