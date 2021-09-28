package com.fronties.socialeventchat.authentication.login

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fronties.socialeventchat.helperClasses.Event

class LoginViewModel: ViewModel(), Observable {

    private val callbacks: PropertyChangeRegistry by lazy { PropertyChangeRegistry()}

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.add(callback)
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.remove(callback)
    }

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

    private val _listenerForNavToRegister = MutableLiveData<Event<Unit>>()
    val listenerForNavToRegister: LiveData<Event<Unit>>
        get() = _listenerForNavToRegister

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
        _listenerForNavToRegister.value = Event(Unit)
    }

}// class ends here
