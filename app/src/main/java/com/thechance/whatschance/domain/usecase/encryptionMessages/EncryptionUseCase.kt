package com.thechance.whatschance.domain.usecase.encryptionMessages

import android.annotation.SuppressLint
import android.util.Base64
import com.thechance.whatschance.BuildConfig
import com.thechance.whatschance.utilties.Constants.TRANSFORMATION
import com.thechance.whatschance.utilties.Constants.UTF_8
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec
import javax.inject.Inject

class EncryptionUseCase @Inject constructor(
    private val generateKeyUseCase: GenerateKeyUseCase
) {
    @SuppressLint("GetInstance")
    operator fun invoke(data: String): String? {
        val key: SecretKeySpec = generateKeyUseCase(BuildConfig.ENCRYPTION_KEY)
        val c: Cipher = Cipher.getInstance(TRANSFORMATION)
        c.init(Cipher.ENCRYPT_MODE, key)
        val encVal: ByteArray = c.doFinal(data.toByteArray(charset(UTF_8)))
        return Base64.encodeToString(encVal, Base64.DEFAULT)
    }
}
