package kazanexpress.ru.javahack.data

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
            val fakeUser = LoggedInUser(UUID.randomUUID().toString(), "Eugene Levin", "", "")
            val response = restApiService.signIn(username, password).execute()
            if (true) {//response.isSuccessful) {
                Thread.sleep(250)
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

