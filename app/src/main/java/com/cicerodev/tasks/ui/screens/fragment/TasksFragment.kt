package com.cicerodev.tasks.ui.screens.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.cicerodev.tasks.adapter.TasksAdapter
import com.cicerodev.tasks.databinding.FragmentTasksBinding
import com.cicerodev.tasks.model.Task
import com.cicerodev.tasks.ui.base.BaseFragment
import com.cicerodev.tasks.ui.state.UIState
import com.cicerodev.tasks.ui.viewmodels.TasksFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class TasksFragment : BaseFragment<TasksFragmentViewModel, FragmentTasksBinding>() {
    override val viewModel: TasksFragmentViewModel by viewModel()
    private lateinit var tasks: MutableList<Task>


    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTasksBinding = FragmentTasksBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        uiResponse()

    }

    private fun configTasksList(tasks: MutableList<Task>) {
        val tasksAdapter = TasksAdapter(tasks, viewModel)
        val recyclerTasks = binding.recyclerView
        recyclerTasks.apply {
            adapter = tasksAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun uiResponse() {
        viewModel.loadTasks()

        viewModel.uiState.observe(requireActivity()) { uiState ->
            when (uiState) {
                is UIState.Success -> {
                    tasks = uiState.data
                    configTasksList(tasks)
                }

                is UIState.Error -> {
                    val errorMessage = uiState.message
                    // Handle the error here
                }

                UIState.Loading -> {
                    // Show loading indicator
                }

                null -> {}
            }
        }
    }


    override fun setupClickListener() {

    }


}