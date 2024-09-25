package com.cicerodev.tasks.ui.screens.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.cicerodev.tasks.adapter.MyPagerAdapter
import com.cicerodev.tasks.ui.base.BaseActivity
import com.cicerodev.tasks.databinding.ActivityMainBinding
import com.cicerodev.tasks.ui.viewmodels.MainActivityViewModel
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : BaseActivity<MainActivityViewModel, ActivityMainBinding>() {
    override val viewModel: MainActivityViewModel by viewModels()

    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    override fun setupClickListener() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configFragmentPages()

    }

    override fun setupWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(binding.main.id)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun configFragmentPages() {
        val adapter = MyPagerAdapter(this)
        binding.viewPager.adapter = adapter
//        val viewPager = binding.viewPager
//        val tabLayout = binding.tabLayout
//        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
//            tab.text = when (position) {
//                0 -> "Tasks"
//                1 -> "New Task"
//                2 -> "My Squad"
//                else -> null
//            }
//        }.attach()
    }

}