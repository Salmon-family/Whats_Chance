package com.thechance.whatschance.domain.usecase

import com.thechance.whatschance.domain.CryptoManger
import javax.inject.Inject

class EncryptTextUseCase @Inject constructor(
    private val cryptoManger: CryptoManger,
) {
    operator fun invoke(plainText:String):String{
        return cryptoManger.encrypt(plainText)
    }
}