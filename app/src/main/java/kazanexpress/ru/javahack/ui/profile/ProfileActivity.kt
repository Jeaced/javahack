package kazanexpress.ru.javahack.ui.profile

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kazanexpress.ru.javahack.R
import kazanexpress.ru.javahack.data.LoginDataSource
import kazanexpress.ru.javahack.data.LoginRepository
import kazanexpress.ru.javahack.ui.login.LoginActivity

class ProfileActivity : AppCompatActivity() {

    private lateinit var textView: TextView
    private lateinit var signOutButton: Button
    private lateinit var loginRepository: LoginRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        textView = findViewById(R.id.textView)
        signOutButton = findViewById(R.id.signOutButton)
        loginRepository = LoginRepository(LoginDataSource())

        signOutButton.setOnClickListener {
            loginRepository.logout()
            goToLoginActivity()
        }
    }

    private fun goToLoginActivity() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}
