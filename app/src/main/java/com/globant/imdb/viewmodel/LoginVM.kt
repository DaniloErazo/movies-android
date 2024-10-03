package com.globant.imdb.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.globant.imdb.model.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.viewModelScope
import com.globant.imdb.database.entities.User
import com.globant.imdb.model.entity.AuthState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginVM @Inject constructor(private val userRepository: UserRepository): ViewModel() {

    var loggedUser = MutableLiveData<AuthState>()
    var errorLogin = MutableLiveData<String>()

    fun signInUser(email: String, password: String){

        viewModelScope.launch(Dispatchers.IO){

            try{
                val user = userRepository.getUserByEmail(email)

                if(user.password == password){
                    withContext(Dispatchers.Main){
                        loggedUser.postValue(AuthState(true, user))
                    }
                }else{
                    withContext(Dispatchers.Main){
                        errorLogin.postValue("Contraseña incorrecta")
                    }
                }

            }catch (e: Exception){
                withContext(Dispatchers.Main){
                    errorLogin.postValue("Problemas iniciando sesión")
                }
            }


        }
    }

    fun signUpUser(email: String, name: String, password: String){

        viewModelScope.launch(Dispatchers.IO) {

            try {
                val user = userRepository.getUserByEmail(email)

                if(user.email == email){
                    withContext(Dispatchers.Main){
                        errorLogin.postValue("El correo ya se encuentra registrado, por favor inicia sesión")
                    }
                }


            }catch (e: Exception){
                val newUSer = User(email = email, name= name, password = password)
                userRepository.addUser(newUSer)
                withContext(Dispatchers.Main){
                    loggedUser.postValue(AuthState(true, newUSer))
                }
            }

        }
    }



}