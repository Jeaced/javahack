package kazanexpress.ru.javahack.data

import android.util.Log
import kazanexpress.ru.javahack.data.model.CredentialsDto
import kazanexpress.ru.javahack.data.model.LoggedInUser
import kazanexpress.ru.javahack.network.RestApiService
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.io.IOException
import java.util.*

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    fun login(username: String, password: String): Result<LoggedInUser> {
        try {
            val restApiService = RestApiService.getInstance()!!
            val response = restApiService.signIn(CredentialsDto(password, username)).execute()
            if (response.isSuccessful) {
                val fakeUser = LoggedInUser(UUID.randomUUID().toString(), "Eugene Levin", response.body()!!.token, "")
                return Result.Success(fakeUser)
            } else {
                return Result.Error(IOException("Incorrect input data"))
            }
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
    }
}

