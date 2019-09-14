package kazanexpress.ru.javahack

import android.app.Application
import kazanexpress.ru.javahack.utils.SharedPreferencesUtils

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        SharedPreferencesUtils.initSharedPreferences(applicationContext)
    }
}