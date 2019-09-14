package kazanexpress.ru.javahack.utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import kazanexpress.ru.javahack.R
import kazanexpress.ru.javahack.data.model.LoggedInUser

object SharedPreferencesUtils {
    private const val USER_DATA_KEY = "user"
    private val serizalizerInstance = Gson()
    private var sharedPreferences: SharedPreferences? = null

    fun initSharedPreferences(applicationContext: Context) {
        synchronized(SharedPreferencesUtils::class.java) {
            if (sharedPreferences != null) {
                return
            }
            sharedPreferences = applicationContext.getSharedPreferences(
                    applicationContext.getString(R.string.shared_preferences),
                    Context.MODE_PRIVATE)
        }
    }

    /**
     * Retrieves saved user data from shared preferences
     *
     * @return LoggedInUser instance if user data was present and null otherwise
     */
    fun getSavedUser(): LoggedInUser? {
        synchronized(SharedPreferencesUtils::class.java) {
            if (sharedPreferences == null) {
                return null
            }

            val userData = sharedPreferences!!.getString(USER_DATA_KEY, "")
            if (userData.isNullOrBlank()) {
                return null
            } else {
                return serizalizerInstance.fromJson(userData, LoggedInUser::class.java)
            }
        }
    }

    /**
     * Stores logged in user data in private shared preferences
     *
     * @return true if successfully saved and false otherwise
     */

    fun saveUser(loggedInUser: LoggedInUser): Boolean {
        synchronized(SharedPreferencesUtils::class.java) {
            if (sharedPreferences == null) {
                return false
            }

            with(sharedPreferences!!.edit()) {
                putString(USER_DATA_KEY, serizalizerInstance.toJson(loggedInUser))
                apply()
            }

            return true
        }
    }

    /**
     * Removes logged in user data from private shared preferences
     *
     * @return true if successfully removed and false otherwise
     */

    fun removeUser(): Boolean {
        synchronized(SharedPreferencesUtils::class.java) {
            if (sharedPreferences == null) {
                return false
            }

            with(sharedPreferences!!.edit()) {
                remove(USER_DATA_KEY)
                apply()
            }

            return true
        }
    }
}