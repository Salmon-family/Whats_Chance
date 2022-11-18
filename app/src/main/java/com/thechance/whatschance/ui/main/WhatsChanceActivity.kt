package com.thechance.whatschance.ui.main

import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import com.thechance.whatschance.R
import com.thechance.whatschance.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WhatsChanceActivity : BaseActivity<ActivityWhatsChanceBinding>() {
    override val viewModel: WhatsChanceViewModel by viewModels()
    override val layoutIdActivity: Int = R.layout.activity_whats_chance

    override fun onStart() {
        super.onStart()
        window.statusBarColor = Color.parseColor(viewModel.brandColor.value)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        window.statusBarColor = Color.parseColor(viewModel.brandColor.value)
        setNavigationGraph()
    }

    private fun setNavigationGraph() {
        if (!viewModel.isUserLogin()) {
            startActivity(Intent(this, AuthenticationActivity::class.java))
            this.finish()
        }

    }
}