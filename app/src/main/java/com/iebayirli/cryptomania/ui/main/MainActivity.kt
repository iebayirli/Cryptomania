package com.iebayirli.cryptomania.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.iebayirli.cryptomania.R
import com.iebayirli.cryptomania.base.BaseActivity
import com.iebayirli.cryptomania.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val layoutId: Int = R.layout.activity_main

    override val fragmentContainerId: Int = R.id.navHostFragmentContainer

    override val viewModel: MainViewModel by viewModels()

    override fun onReady(savedInstanceState: Bundle?) {
        withToolbar(binding.navHostFragmentContainer, binding.toolbar)
        withBottomNavView(binding.bottomNavigationView)
    }

}