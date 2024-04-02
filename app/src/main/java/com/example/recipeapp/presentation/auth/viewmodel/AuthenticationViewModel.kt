package com.example.recipeapp.presentation.auth.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.data.setting.UserPreferences
import com.example.recipeapp.domain.model.User
import com.example.recipeapp.domain.usecases.AuthUseCases
import com.example.recipeapp.presentation.auth.forgot_password.ForgotPasswordState
import com.example.recipeapp.presentation.auth.sigin.event.SignInScreenEvent
import com.example.recipeapp.presentation.auth.sigin.event.SignInUiEvent
import com.example.recipeapp.presentation.auth.sigin.state.SignInLocalState
import com.example.recipeapp.presentation.auth.sigin.state.SignInState
import com.example.recipeapp.presentation.auth.signup.event.SignUpScreenEvent
import com.example.recipeapp.presentation.auth.signup.event.SignUpUIEvent
import com.example.recipeapp.presentation.auth.signup.state.SignUpLocalState
import com.example.recipeapp.presentation.auth.signup.state.SignUpState
import com.example.recipeapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val authUseCases: AuthUseCases,
    private val userPreferences: UserPreferences
) : ViewModel() {

    var signInLocalState by mutableStateOf(SignInLocalState())
        private set

    var signUpLocalState by mutableStateOf(SignUpLocalState())
        private set

    var signUpState by mutableStateOf(SignUpState())
        private set

    var signInState by mutableStateOf(SignInState())
        private set

    var forgotPasswordState by mutableStateOf(ForgotPasswordState())
        private set


    var isUserLogin = MutableSharedFlow<Boolean?>()
        private set

    init {
        readUserLoginState()
    }

    private val _SignUp_uiEventFlow = MutableSharedFlow<SignUpUIEvent>()
    val uiEventFlow = _SignUp_uiEventFlow.asSharedFlow()

    private val _signIn_uiEventFlow = MutableSharedFlow<SignInUiEvent>()
    val signIn_uiEventFlow = _signIn_uiEventFlow

    fun onSignUpEvent(signUpScreenEvent: SignUpScreenEvent) {

        when (signUpScreenEvent) {

            is SignUpScreenEvent.EmailEntered -> {
                signUpLocalState = signUpLocalState.copy(
                    email = signUpScreenEvent.email
                )
            }

            is SignUpScreenEvent.UserNameEntered -> {
                signUpLocalState = signUpLocalState.copy(
                    name = signUpScreenEvent.name
                )
            }

            is SignUpScreenEvent.UsrPasswordEntered -> {
                signUpLocalState = signUpLocalState.copy(
                    password = signUpScreenEvent.password
                )
            }

            is SignUpScreenEvent.UsrCnfrmPassword -> {
                signUpLocalState = signUpLocalState.copy(
                    cnfrmPassword = signUpScreenEvent.cnfrmPassword
                )
            }

            is SignUpScreenEvent.OnPasswordTogle -> {
                signUpLocalState = signUpLocalState.copy(
                    isPasswordVisbile = !signUpLocalState.isPasswordVisbile
                )
            }

            is SignUpScreenEvent.OnCnfrmPsswrdToogle -> {
                signUpLocalState = signUpLocalState.copy(
                    isCnfrmPasswordVisible = !signUpLocalState.isCnfrmPasswordVisible
                )
            }

            is SignUpScreenEvent.OnSignupClicked -> {
                registerUser()
            }

        }

    }

    private fun registerUser() {
        viewModelScope.launch {

            if (signUpLocalState.email.isEmpty()) {
                _SignUp_uiEventFlow.emit(
                    SignUpUIEvent.ShowMessage("Please input your email")
                )
                return@launch
            }

            if (signUpLocalState.name.isEmpty()) {
                _SignUp_uiEventFlow.emit(
                    SignUpUIEvent.ShowMessage("Please input your name")
                )
                return@launch
            }

            if (signUpLocalState.password.isEmpty() || signUpLocalState.password.length < 5) {

                _SignUp_uiEventFlow.emit(
                    SignUpUIEvent.ShowMessage("Please input your password")
                )
                return@launch
            }

            if (signUpLocalState.cnfrmPassword != signUpLocalState.password) {
                _SignUp_uiEventFlow.emit(
                    SignUpUIEvent.ShowMessage("Password doesn't match")
                )
                return@launch
            }

            signUpState = signUpState.copy(
                isLoading = true
            )

            val response = authUseCases.registerUserWithEmailUseCase.execute(
                signUpLocalState.email,
                signUpLocalState.email
            )

            when (response) {

                is Resource.Loading -> {
                    signUpState = signUpState.copy(
                        isLoading = true
                    )

                }

                is Resource.Success -> {
                    val user = User(
                        userId = response.data!!.uid,
                        userName = signUpLocalState.name,
                        userEmail = signUpLocalState.email
                    )
                    authUseCases.insertUserUseCase.execute(user)

                    signUpState = signUpState.copy(
                        isLoading = false
                    )

                    _SignUp_uiEventFlow.emit(
                        SignUpUIEvent.NavigationEvent("")
                    )

                }

                is Resource.Error -> {

                    signUpState = signUpState.copy(
                        isLoading = false,
                        error = response.message
                    )

                }

            }

        }
    }

    fun onSignInEvent(signInScreenEvent: SignInScreenEvent) {

        when (signInScreenEvent) {

            is SignInScreenEvent.EmailEntered -> {

                signInLocalState = signInLocalState.copy(
                    email = signInScreenEvent.email
                )

            }

            is SignInScreenEvent.UsrPasswordEntered -> {
                signInLocalState = signInLocalState.copy(
                    password = signInScreenEvent.password
                )
            }

            is SignInScreenEvent.OnPasswordTogale -> {

                signInLocalState = signInLocalState.copy(
                    isPasswordVisible = !signInLocalState.isPasswordVisible
                )

            }

            is SignInScreenEvent.OnSignInClick -> {
                loginUser()
            }

        }


    }

    private fun loginUser() {

        viewModelScope.launch {

            if (signInLocalState.email.isEmpty()) {
                _signIn_uiEventFlow.emit(
                    SignInUiEvent.ShowMessage("Please input your email")
                )
                return@launch
            }

            if (signInLocalState.password.isEmpty() || signInLocalState.password.length < 5) {

                _signIn_uiEventFlow.emit(
                    SignInUiEvent.ShowMessage("Please input your password")
                )
                return@launch
            }

            signInState = signInState.copy(
                isLoading = true
            )

            val response =
                authUseCases.loginUserWithEmailUseCase.execute(
                    signInLocalState.email,
                    signUpLocalState.password
                )

            when (response) {

                is Resource.Success -> {

                    signInState = signInState.copy(
                        isLoading = false
                    )

                    signIn_uiEventFlow.emit(
                        SignInUiEvent.NavigationEvent("")
                    )

                }

                is Resource.Error -> {
                    signInState = signInState.copy(
                        isLoading = false,
                        error = response.message
                    )
                    signIn_uiEventFlow.emit(
                        SignInUiEvent.ShowMessage(response.message.toString())
                    )

                }

                is Resource.Loading -> {
                    signInState = signInState.copy(
                        isLoading = true
                    )
                }

            }

        }

    }

    fun updateUserLoginState(isLogin: Boolean) {
        viewModelScope.launch {
            userPreferences.writeIntPref(isLogin)
        }
    }


    fun readUserLoginState() {
        viewModelScope.launch {
            userPreferences.readIntPref().collect {
                isUserLogin.emit(it)
            }
        }

    }
}