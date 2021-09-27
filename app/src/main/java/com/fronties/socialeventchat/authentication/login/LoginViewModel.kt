package com.fronties.socialeventchat.authentication.login

import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {
    @Bindable
    val usernameLoginEtContent = MutableLiveData<String>()

    private val _usernameForLogin = MutableLiveData<String>()
    val usernameForLogin: LiveData<String>
        get() = _usernameForLogin

    @Bindable
    val passwordLoginEtContent = MutableLiveData<String>()

    private val _passwordForLogin = MutableLiveData<String>()
    val passwordForLogin: LiveData<String>
        get() = _passwordForLogin

    fun loginButtonClicked(){
        _usernameForLogin.value = usernameLoginEtContent.value
        _passwordForLogin.value = passwordLoginEtContent.value

//        Login user
//        *** Uncomment Below ***
//        loginUser(usernameForLogin.value,passwordForLogin.value)

//        *** Write Code to Take User to Main Screen***
    }

    fun registerButtonClicked(){
//        *** Write Code to Take User to Register Screen***
    }


}