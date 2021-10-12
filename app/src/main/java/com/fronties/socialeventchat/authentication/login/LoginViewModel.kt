package com.fronties.socialeventchat.authentication.login

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.auth0.android.jwt.JWT
import com.fronties.socialeventchat.authentication.repo.AuthenticationRepo
import com.fronties.socialeventchat.authentication.validator.EmailValidator
import com.fronties.socialeventchat.helperClasses.AuthException
import com.fronties.socialeventchat.helperClasses.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import java.util.*
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authenticationRepo: AuthenticationRepo,
    private val emailValidator: EmailValidator
) : ViewModel(), Observable {

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

    private val _listenerForNavToEventFeed = MutableLiveData<Event<Unit>>()
    val listenerForNavToEventFeed: LiveData<Event<Unit>>
        get() = _listenerForNavToEventFeed

    private val _listenerForError = MutableLiveData<Event<String?>>()
    val listenerForError: LiveData<Event<String?>>
        get() = _listenerForError

    fun loginButtonClicked() {

        _usernameForLogin.value = usernameLoginEtContent.value
        _passwordForLogin.value = passwordLoginEtContent.value
        _usernameForLogin.value?.let { email ->
            if (emailValidator.validatePatriotsEmail(email)) {
                viewModelScope.launch {
                    try {
                        if (authenticationRepo.loginUser(
                                email,
                                "password"
                            )
                        ) {// TODO for testing = remove later
                            _listenerForNavToEventFeed.value = Event(Unit)
                        } else{
                            _listenerForError.value = Event("login")
                        }
                    } catch (e: IOException) {
                        // TODO show some error screen
                        _listenerForError.value = Event(e.localizedMessage)
                        return@launch
                    } catch (e: HttpException) {
                        // TODO show some error screen
                        _listenerForError.value = Event(e.localizedMessage)
                        return@launch
                    }
                }
            }
        }
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
