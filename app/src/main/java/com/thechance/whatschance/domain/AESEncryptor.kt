package com.thechance.whatschance.domain

import android.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec
import javax.inject.Inject

interface DataEncryptor{

    fun encrypt(plainText : String): String

    fun decrypt(cipherText:String): String
}


class AESEncryptor @Inject constructor(
    private val cipher: Cipher,
    private val ivParameterSpec: IvParameterSpec,
    private val secretKey: SecretKeySpec
) : DataEncryptor {

    override fun encrypt(plainText: String): String {
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec)
        val encrypted = cipher.doFinal(plainText.toByteArray())
        val encodedByte = Base64.encode(encrypted, Base64.DEFAULT)
        return String(encodedByte)
    }

    override fun decrypt(cipherText: String): String {
        val decodedByte: ByteArray = Base64.decode(cipherText, Base64.DEFAULT)
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec)
        val plainText = cipher.doFinal(decodedByte)
        return String(plainText)
    }

}