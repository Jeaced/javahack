package kazanexpress.ru.javahack.services

import android.content.Context
import kazanexpress.ru.javahack.R

object AuthService {

    fun isAuthenticated(applicationContext: Context): Boolean {
        val sharedPreferences = applicationContext.getSharedPreferences(
                applicationContext.getString(R.string.shared_preferences),
                Context.MODE_PRIVATE
        )

        return sharedPreferences.getBoolean("isSignedIn", false)

    }

    fun getBearerToken(): String {
        return ""
    }

    fun refreshToken() {

    }

    fun signIn() {

    }

    fun signUp() {

    }

    fun signOut() {

    }
}