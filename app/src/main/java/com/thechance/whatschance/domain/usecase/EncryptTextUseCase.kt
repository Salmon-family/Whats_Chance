package com.thechance.whatschance.domain.usecase

import com.thechance.whatschance.domain.DataEncryptorImp
import javax.inject.Inject

class EncryptTextUseCase @Inject constructor(
    private val cryptoManger: DataEncryptorImp,
) {
    operator fun invoke(plainText:String):String{
        return cryptoManger.encrypt(plainText)
    }
}