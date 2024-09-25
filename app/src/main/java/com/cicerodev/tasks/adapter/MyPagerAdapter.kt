package com.cicerodev.tasks.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.cicerodev.tasks.ui.screens.fragment.NewTaskFragment
import com.cicerodev.tasks.ui.screens.fragment.RegisterFragment
import com.cicerodev.tasks.ui.screens.fragment.TasksFragment

class MyPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 3 // Número de tabs
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> RegisterFragment()
            1 -> TasksFragment()
            2 -> NewTaskFragment()
            3 -> Fragment()
            else -> throw IllegalStateException("Unexpected position $position")
        }
    }
}