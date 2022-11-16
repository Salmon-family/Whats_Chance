package com.thechance.whatschance.ui.authentication

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.thechance.whatschance.R
import com.thechance.whatschance.databinding.ActivityAuthenticationBinding
import com.thechance.whatschance.ui.base.BaseActivity
import com.thechance.whatschance.ui.main.WhatsChanceActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthenticationActivity : BaseActivity<ActivityAuthenticationBinding>() {
    override val layoutIdActivity: Int
        get() = R.layout.activity_authentication
    override val viewModel: AuthenticationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        window.statusBarColor = Color.parseColor(viewModel.brandColor.value)
    }

}