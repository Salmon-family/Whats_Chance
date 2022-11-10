package com.thechance.whatschance.domain.usecase

import com.thechance.whatschance.domain.DataEncryptorImp
import javax.inject.Inject

class DecryptTextUseCase @Inject constructor(
    private val cryptoManger: DataEncryptorImp,
) {
    operator fun invoke(cipherText:String):String{
        return cryptoManger.decrypt(cipherText)
    }
}