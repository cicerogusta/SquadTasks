package com.cicerodev.tasks.ui.screens.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.cicerodev.tasks.databinding.FragmentNewTaskBinding
import com.cicerodev.tasks.model.Task
import com.cicerodev.tasks.ui.base.BaseFragment
import com.cicerodev.tasks.ui.state.UIState
import com.cicerodev.tasks.ui.viewmodels.NewTaskFragmentViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class NewTaskFragment : BaseFragment<NewTaskFragmentViewModel, FragmentNewTaskBinding>() {
    override val viewModel: NewTaskFragmentViewModel by viewModel()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNewTaskBinding = FragmentNewTaskBinding.inflate(layoutInflater)

    override fun setupClickListener() {
        configButtons()
        configBoxes()

    }

    private fun configButtons() {
        binding.buttonSendTask.setOnClickListener {
            createTask()

        }
    }

    private fun createTask() {
        val priorityTask = checkPriority()
        lifecycleScope.launch {
            val taskUser = Task(
                taskTitle = binding.editTitle.text.toString(),
                taskDescription = binding.editDesc.text.toString(),
                priorityTask = priorityTask
            )
            viewModel.addNewTask(
                taskUser

            )
            uiResponse()
        }

    }

    private fun uiResponse() {
        viewModel.uiState.observe(requireActivity()) { uiState ->
            when (uiState) {
                is UIState.Success -> {
                    Toast.makeText(requireContext(), "Task created", Toast.LENGTH_SHORT).show()
                }

                is UIState.Error -> {
                    Toast.makeText(requireContext(), "Task failed", Toast.LENGTH_SHORT).show()
                }

                is UIState.Loading -> {
                    Toast.makeText(requireContext(), "Loading...", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }

    private fun checkPriority(): Boolean {
        var priority = false
        if (binding.checkBoxYes.isChecked) {
            priority = true
        }
        return priority
    }

    private fun configBoxes() {
        binding.checkBoxNoo.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) binding.checkBoxYes.isChecked = false
        }

        binding.checkBoxYes.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) binding.checkBoxNoo.isChecked = false
        }
    }

}