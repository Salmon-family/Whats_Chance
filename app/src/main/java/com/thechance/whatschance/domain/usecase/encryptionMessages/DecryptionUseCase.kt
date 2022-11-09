package com.thechance.whatschance.domain.usecase.encryptionMessages

import android.annotation.SuppressLint
import android.util.Base64
import com.thechance.whatschance.BuildConfig
import com.thechance.whatschance.utilties.Constants.TRANSFORMATION
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec
import javax.inject.Inject

class DecryptionUseCase @Inject constructor(
    private val generateKeyUseCase: GenerateKeyUseCase
) {
    @SuppressLint("GetInstance")
    operator fun invoke(data: String?): String {
        val key: SecretKeySpec = generateKeyUseCase(BuildConfig.ENCRYPTION_KEY)
        val c: Cipher = Cipher.getInstance(TRANSFORMATION)
        c.init(Cipher.DECRYPT_MODE, key)
        val decodedValue: ByteArray = Base64.decode(
            data,
            Base64.DEFAULT
        )
        val decValue: ByteArray = c.doFinal(decodedValue)
        return String(decValue)
    }
}
