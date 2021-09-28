package com.fronties.socialeventchat.authentication.register

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fronties.socialeventchat.helperClasses.Event

class RegisterViewModel: ViewModel(), Observable {

    private val callbacks: PropertyChangeRegistry by lazy { PropertyChangeRegistry()}

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.add(callback)
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.remove(callback)
    }

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

    private val _listenerForNavToMainScreen = MutableLiveData<Event<Unit>>()
    val listenerForNavToMainScreen: LiveData<Event<Unit>>
        get()=_listenerForNavToMainScreen


    fun registerButtonClicked(){
        _usernameForRegister.value = usernameRegisterEtContent.value
        _passwordForRegister.value = passwordRegisterEtContent.value

//        Register user
//        *** Uncomment Below Method***
//        registerUser(usernameForRegister.value,passwordForRegister.value)

//        Take User to Profile Section
        _listenerForNavToMainScreen.value = Event(Unit)
    }
}