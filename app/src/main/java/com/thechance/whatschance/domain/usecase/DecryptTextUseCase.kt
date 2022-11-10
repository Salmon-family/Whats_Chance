package com.thechance.whatschance.domain.usecase

import com.thechance.whatschance.domain.DataEncryptor
import com.thechance.whatschance.domain.DataEncryptorImp
import javax.inject.Inject

class DecryptTextUseCase @Inject constructor(
    private val dataEncryptor: DataEncryptor,
) {
    operator fun invoke(cipherText:String):String{
        return dataEncryptor.decrypt(cipherText)
    }
}