package com.cicerodev.tasks.ui.base

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.cicerodev.tasks.R

abstract class BaseActivity<VM : ViewModel, VB : ViewBinding> : AppCompatActivity() {
    private var _binding: VB? = null
    protected val binding get() = _binding!!
    protected abstract val viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = getViewBinding()
        setContentView(binding.root)
        enableEdgeToEdge()
        setupWindowInsets()
        setupClickListener()
    }

    override fun onDestroy() {
        super.onDestroy()
        // Clean up the binding to avoid memory leaks
        _binding = null
    }

    abstract fun setupWindowInsets()

    abstract fun getViewBinding(): VB
    abstract fun setupClickListener()
}