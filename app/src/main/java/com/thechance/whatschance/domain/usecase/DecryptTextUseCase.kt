package com.thechance.whatschance.domain.usecase

import com.thechance.whatschance.domain.CryptoManger
import javax.inject.Inject

class DecryptTextUseCase @Inject constructor(
    private val cryptoManger: CryptoManger,
) {
    operator fun invoke(cipherText:String):String{
        return cryptoManger.decrypt(cipherText)
    }
}