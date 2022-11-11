package com.thechance.whatschance.ui

interface Mapper<I, O> {
    fun map(input: I): O
}