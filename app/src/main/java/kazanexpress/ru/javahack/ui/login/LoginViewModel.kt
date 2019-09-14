package kazanexpress.ru.javahack.ui.login

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Patterns
import kazanexpress.ru.javahack.R
import kazanexpress.ru.javahack.data.LoginRepository
import kazanexpress.ru.javahack.data.Result
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    init {
        val user = loginRepository.user
        if (user != null) {
            _loginResult.value = LoginResult(success = LoggedInUserView(displayName = user.displayName))
        }
    }

    fun login(username: String, password: String) {

        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            // can be launched in a separate asynchronous job
            doAsync {
                val result = loginRepository.login(username, password)
                uiThread {
                    if (result is Result.Success) {
                        _loginResult.value = LoginResult(success = LoggedInUserView(displayName = result.data.displayName))
                    } else {
                        _loginResult.value = LoginResult(error = R.string.login_failed)
                    }
                }
            }
        }
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return username.length >= 10
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length >= 8
    }
}
