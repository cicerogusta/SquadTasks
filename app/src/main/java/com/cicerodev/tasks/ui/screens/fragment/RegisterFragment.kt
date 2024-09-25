package com.cicerodev.tasks.ui.screens.fragment



import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.cicerodev.tasks.R
import com.cicerodev.tasks.databinding.FragmentRegisterBinding
import com.cicerodev.tasks.model.User
import com.cicerodev.tasks.ui.base.BaseFragment
import com.cicerodev.tasks.ui.state.UIState
import com.cicerodev.tasks.ui.viewmodels.RegisterFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class RegisterFragment : BaseFragment<RegisterFragmentViewModel, FragmentRegisterBinding>() {
    private lateinit var spinner: Spinner
    override val viewModel: RegisterFragmentViewModel by viewModel()


    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentRegisterBinding = FragmentRegisterBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSpinner()
        configBoxes()
    }

    private fun setupSpinner() {
        spinner = binding.spinnerSquad
        val squads = listOf("Squad 1", "Squad 2", "Squad 3")
        val adapter = ArrayAdapter(requireContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, squads)
        spinner.adapter = adapter
    }

    override fun setupClickListener() {
        binding.buttonSignUp.setOnClickListener {
            if (checkRegisterForm()) {
                val isHightLevelUser = checkHightLevelUser()
                signUp(
                    User(
                        name = binding.editTextName.text.toString(),
                        email = binding.editUserEmail.text.toString(),
                        password = binding.editTextTextPassword.text.toString(),
                        isHighLevelUser = isHightLevelUser,
                        squad = spinner.selectedItem.toString()
                    )
                )
                uiResponse()
            } else {
                Toast.makeText(requireContext(), "Please check the form", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun checkRegisterForm(): Boolean {
        val email = binding.editUserEmail.text.toString()
        val password = binding.editTextTextPassword.text.toString()
        val name = binding.editTextName.text.toString()

        val noBox = binding.checkBoxNoo
        val yesBox = binding.checkBoxYes

        val hasBoxChecked = yesBox.isChecked || noBox.isChecked

        return email.isNotBlank() && password.isNotBlank() && name.isNotBlank() && hasBoxChecked
    }

    private fun signUp(user: User) {
        viewModel.registerUser(user)
    }

    private fun uiResponse() {
        viewModel.uiState.observe(requireActivity()) { uiState ->
            when (uiState) {
                is UIState.Success -> {
                    Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
                }

                is UIState.Error -> {
                    Toast.makeText(requireContext(), uiState.message, Toast.LENGTH_SHORT).show()
                }

                is UIState.Loading -> {
                    Toast.makeText(requireContext(), "Loading...", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }

    private fun configBoxes() {
        binding.checkBoxNoo.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) binding.checkBoxYes.isChecked = false
        }

        binding.checkBoxYes.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) binding.checkBoxNoo.isChecked = false
        }
    }

    private fun checkHightLevelUser(): Boolean {
        val priority: Boolean = binding.checkBoxYes.isChecked
        return priority
    }


}