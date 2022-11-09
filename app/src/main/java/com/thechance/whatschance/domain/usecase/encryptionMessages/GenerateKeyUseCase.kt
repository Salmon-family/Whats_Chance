package com.thechance.whatschance.domain.usecase.encryptionMessages

import com.thechance.whatschance.utilties.Constants
import java.security.MessageDigest
import javax.crypto.spec.SecretKeySpec
import javax.inject.Inject

class GenerateKeyUseCase @Inject constructor() {

    operator fun invoke(password: String): SecretKeySpec {
        val digest: MessageDigest =
            MessageDigest.getInstance(Constants.ALGORITHM_SHA_256)
        val bytes = password.toByteArray(charset(Constants.UTF_8))
        digest.update(bytes, 0, bytes.size)
        val key: ByteArray =
            digest.digest()
        return SecretKeySpec(key, Constants.ALGORITHM_ASE)
    }
}
