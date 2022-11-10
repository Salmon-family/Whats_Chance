package com.thechance.whatschance

import javax.inject.Inject

class Keys @Inject constructor(){

    init {
        System.loadLibrary("native-lib")
    }

    external fun secretKey(): String

    external fun secretIv(): String
}