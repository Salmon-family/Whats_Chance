package com.thechance.whatschance.utilities

import androidx.databinding.BindingAdapter
import com.hbb20.CountryCodePicker

@BindingAdapter("bind:countryValue")
fun setCountryValue(picker: CountryCodePicker, code: String) {
    picker.selectedCountryCode
}
