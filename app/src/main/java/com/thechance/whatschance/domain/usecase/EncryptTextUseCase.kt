package com.thechance.whatschance.domain.usecase

import com.thechance.whatschance.domain.DataEncryptor
import com.thechance.whatschance.domain.DataEncryptorImp
import javax.inject.Inject

class EncryptTextUseCase @Inject constructor(
    private val dataEncryptor: DataEncryptor,
) {
    operator fun invoke(plainText:String):String{
        return dataEncryptor.encrypt(plainText)
    }
}