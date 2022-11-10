package com.thechance.whatschance.ui.chat

interface Mapper<I, O> {
    fun map(input: I): O
}