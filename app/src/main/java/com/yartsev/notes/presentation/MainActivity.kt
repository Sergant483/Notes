package com.yartsev.notes.presentation

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.yartsev.notes.R
import com.yartsev.notes.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private val host by lazy { supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment }
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.bottomBar.setupWithNavController(host.navController)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if (item.itemId != host.navController.currentDestination?.id) {
            host.navController.navigate(
                item.itemId, null,
                NavOptions.Builder().setPopUpTo(R.id.notesFragment, false).build()
            )
        }
        return true
    }

}