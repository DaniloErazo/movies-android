package com.globant.imdb.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.globant.imdb.R
import com.globant.imdb.activity.MainActivity
import com.globant.imdb.databinding.SigninFragmentBinding
import com.globant.imdb.viewmodel.LoginVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment: Fragment() {

    val vm: LoginVM by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = SigninFragmentBinding.bind(view)

        binding.host.setOnClickListener {
            startActivity(Intent(requireContext(), MainActivity::class.java))
        }
        binding.tvRegister.setOnClickListener {
            findNavController().navigate(R.id.action_signinFragment_to_signupFragment)
        }

        vm.errorLogin.observe(viewLifecycleOwner){
            error -> Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show()
        }

        vm.loggedUser.observe(viewLifecycleOwner){
            state ->
            if(state.isLogged){
                startActivity(Intent(requireContext(), MainActivity::class.java))
            }
        }

        binding.loginButton.setOnClickListener {
            val email = binding.emailInput.text.toString()
            val password = binding.passwordInput.text.toString()
            vm.signInUser(email, password)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = SigninFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
}