package com.fronties.socialeventchat.authentication.register

import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegisterViewModel: ViewModel() {

    @Bindable
    val usernameRegisterEtContent = MutableLiveData<String>()

    private val _usernameForRegister = MutableLiveData<String>()
    val usernameForRegister: LiveData<String>
        get() = _usernameForRegister

    @Bindable
    val passwordRegisterEtContent = MutableLiveData<String>()

    private val _passwordForRegister = MutableLiveData<String>()
    val passwordForRegister: LiveData<String>
        get() = _passwordForRegister

    fun registerButtonClicked(){
        _usernameForRegister.value = usernameRegisterEtContent.value
        _passwordForRegister.value = passwordRegisterEtContent.value

//        Register user
//        *** Uncomment Below Method***
//        registerUser(usernameForRegister.value,passwordForRegister.value)

//        Take User to Profile Section
//        ***Write code that handles that***
    }
}