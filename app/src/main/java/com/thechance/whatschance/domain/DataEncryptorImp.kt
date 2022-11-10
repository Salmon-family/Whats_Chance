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


class DataEncryptorImp @Inject constructor() : DataEncryptor{

    private val iv = IvParameterSpec(SECRET_IV.toByteArray())
    private val cipher = Cipher.getInstance(TRANSFORMATION)

    override fun encrypt(plainText : String): String {
        val keySpec = SecretKeySpec(SECRET_KEY.toByteArray(), "AES")
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, iv)
        val encrypted = cipher.doFinal(plainText.toByteArray())
        val encodedByte = Base64.encode(encrypted, Base64.DEFAULT)
        return String(encodedByte)
    }

    override fun decrypt(cipherText:String): String {
        val decodedByte: ByteArray = Base64.decode(cipherText, Base64.DEFAULT)
        val keySpec = SecretKeySpec(SECRET_KEY.toByteArray(), "AES")
        cipher.init(Cipher.DECRYPT_MODE, keySpec, iv)
        val output = cipher.doFinal(decodedByte)
        return String(output)
    }

    companion object{
        val  SECRET_KEY = Keys.secretKey()
        val SECRET_IV = Keys.secretIv()
        private const val ALGORITHM = "AES"
        private const val BLOCK_MODE = "CBC"
        private const val PADDING = "PKCS5PADDING"
        private const val TRANSFORMATION = "$ALGORITHM/$BLOCK_MODE/$PADDING"
    }
}