package kazanexpress.ru.javahack.data

import kazanexpress.ru.javahack.data.model.LoggedInUser
import java.io.IOException
import java.util.*

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    fun login(username: String, password: String): Result<LoggedInUser> {
        try {
            // TODO: handle loggedInUser authentication
            val fakeUser = LoggedInUser(UUID.randomUUID().toString(), "Jane Doe", "", "")
            if (username != "fail") {
                return Result.Success(fakeUser)
            } else {
                return Result.Error(IOException("Incorrect input data"))
            }
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}

