package com.globant.imdb.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.globant.imdb.R
import com.globant.imdb.activity.MainActivity
import com.globant.imdb.databinding.SignupFragmentBinding
import com.globant.imdb.viewmodel.LoginVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment: Fragment() {

    val vm: LoginVM by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = SignupFragmentBinding.bind(view)
        binding.backArrow.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
        }

        binding.acceptBtn.setOnClickListener {
            val name = binding.nameInput.text.toString()
            val email = binding.emailInput.text.toString()
            val password = binding.passwordInput.editText?.text.toString()
            vm.signUpUser(email, name, password)
        }

        vm.errorLogin.observe(viewLifecycleOwner){
                error -> Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show()
        }

        vm.loggedUser.observe(viewLifecycleOwner){
            user ->
            if(user.isLogged){
                startActivity(Intent(requireContext(), MainActivity::class.java))
            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = SignupFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
}