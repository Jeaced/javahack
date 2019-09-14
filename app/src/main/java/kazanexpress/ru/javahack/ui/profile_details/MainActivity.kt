package kazanexpress.ru.javahack.ui.profile_details

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import kazanexpress.ru.javahack.R
import kazanexpress.ru.javahack.data.LoginDataSource
import kazanexpress.ru.javahack.data.LoginRepository
import kazanexpress.ru.javahack.ui.login.LoginActivity


class MainActivity : AppCompatActivity() {

    private lateinit var textView: TextView
    private lateinit var signOutButton: Button
    private lateinit var loginRepository: LoginRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)
        signOutButton = findViewById(R.id.signOutButton)
        loginRepository = LoginRepository(LoginDataSource())

        signOutButton.setOnClickListener {
            loginRepository.logout()
            goToLoginActivity()
        }


//        val restApiService = RestApiService.getInstance()!!
//
//
//        CoroutineScope(Dispatchers.IO).launch {
//            val contextResult = restApiService.getGoogle()
//            withContext(Dispatchers.Main) {
//                textView.text = contextResult.code().toString()
//            }
//            val deferredResult = async {
//                restApiService.getGoogle()
//            }
//            val result = deferredResult.await()
//            Log.e("await", result.code().toString())
//
//        }
    }

    override fun onBackPressed() {
        moveTaskToBack(true)
    }

    private fun goToLoginActivity() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}
