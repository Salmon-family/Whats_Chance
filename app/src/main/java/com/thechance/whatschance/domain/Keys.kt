package com.thechance.whatschance.domain

object Keys {

    init {
        System.loadLibrary("native-lib")
    }

    external fun secretKey(): String

    external fun secretIv(): String
}