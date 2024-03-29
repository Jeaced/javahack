package kazanexpress.ru.javahack.data

import kazanexpress.ru.javahack.data.model.LoggedInUser
import kazanexpress.ru.javahack.utils.SharedPreferencesUtils

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository(val dataSource: LoginDataSource) {

    // in-memory cache of the loggedInUser object
    var user: LoggedInUser? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    init {
        user = SharedPreferencesUtils.getSavedUser()
    }

    fun logout() {
        user = null
        SharedPreferencesUtils.removeUser()
        dataSource.logout()
    }

    fun login(username: String, password: String): Result<LoggedInUser> {
        // handle login
        val result = dataSource.login(username, password)
        if (result is Result.Success) {
            setLoggedInUser(result.data)
            SharedPreferencesUtils.saveUser(result.data)
        }

        return result
    }

    private fun setLoggedInUser(loggedInUser: LoggedInUser) {
        this.user = loggedInUser
    }
}
