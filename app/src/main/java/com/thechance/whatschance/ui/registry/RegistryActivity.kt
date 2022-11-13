package com.thechance.whatschance.ui.registry

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import com.thechance.whatschance.R
import com.thechance.whatschance.databinding.ActivityRegistryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_registry)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_registry) as NavHostFragment

        val navController = navHostFragment.navController

        val navGraph = navController.graph
        navGraph.setStartDestination(R.id.loginFragment)
        navController.graph = navGraph
    }
}