package com.fronties.socialeventchat.authentication.register

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fronties.socialeventchat.authentication.repo.AuthenticationRepo
import com.fronties.socialeventchat.authentication.validator.EmailValidator
import com.fronties.socialeventchat.helperClasses.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authenticationRepo: AuthenticationRepo,
    private val emailValidator: EmailValidator
) : ViewModel(), Observable {

    private val callbacks: PropertyChangeRegistry by lazy { PropertyChangeRegistry() }

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

    private val _listenerForNavToProfileSection = MutableLiveData<Event<Unit>>()
    val listenerForNavToProfileSection: LiveData<Event<Unit>>
        get() = _listenerForNavToProfileSection

    private val _listenerForRegisterError = MutableLiveData<Event<String?>>()
    val listenerForRegisterError: LiveData<Event<String?>>
        get() = _listenerForRegisterError

    private val _listenerForLoginTv = MutableLiveData<Event<Unit>>()
    val listenerForLoginTv: LiveData<Event<Unit>>
        get() = _listenerForLoginTv

    fun registerButtonClicked() {
        _usernameForRegister.value = usernameRegisterEtContent.value
        _passwordForRegister.value = passwordRegisterEtContent.value
        _usernameForRegister.value?.let { email ->
            if (emailValidator.validatePatriotsEmail(email)) {
                viewModelScope.launch {
                    try {
                        if (authenticationRepo.registerUser(email, "password")) { // TODO for testing - remove later
                            _listenerForNavToProfileSection.value = Event(Unit)
                        }else{
                            _listenerForRegisterError.value = Event("register")
                        }
                    } catch (e: IOException) {
                        // TODO show some error screen
                        _listenerForRegisterError.value = Event(e.localizedMessage)
                        return@launch
                    } catch (e: HttpException) {
                        // TODO show some error screen
                        _listenerForRegisterError.value = Event(e.localizedMessage)
                        return@launch
                    }
                }
            }
        }
    }

    fun loginTvClicked() {
        _listenerForLoginTv.value = Event(Unit)
    }
}
