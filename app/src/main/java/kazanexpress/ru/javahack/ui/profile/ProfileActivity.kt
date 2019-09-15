package kazanexpress.ru.javahack.ui.profile

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.widget.Button
import android.widget.TextView
import kazanexpress.ru.javahack.R
import kazanexpress.ru.javahack.data.LoginDataSource
import kazanexpress.ru.javahack.data.LoginRepository
import kazanexpress.ru.javahack.data.model.ClientInfoResponse
import kazanexpress.ru.javahack.ui.login.LoginActivity
import kazanexpress.ru.javahack.ui.login.LoginViewModel
import kazanexpress.ru.javahack.ui.login.LoginViewModelFactory

class ProfileActivity : AppCompatActivity() {

    private lateinit var signOutButton: Button
    private lateinit var loginRepository: LoginRepository
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var amountAvailableView: TextView
    private lateinit var amountOnHoldView: TextView
    private lateinit var profileViewModel: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        signOutButton = findViewById(R.id.signOutButton)
        loginRepository = LoginRepository(LoginDataSource())

        swipeRefreshLayout = SwipeRefreshLayout(this)
        amountAvailableView = findViewById(R.id.amountAvailable)
        amountOnHoldView = findViewById(R.id.amountOnHold)
        amountAvailableView.typeface = Typeface.createFromAsset(assets, "fonts/FuturaNewDemi.otf")
        amountOnHoldView.typeface = Typeface.createFromAsset(assets, "fonts/FTR45.otf")

        profileViewModel = ViewModelProviders.of(this, ProfileViewModelFactory())
                .get(ProfileViewModel::class.java)
        profileViewModel.getClientData().observe(this, Observer { userData ->
            updateUI(userData!!)
        })

        swipeRefreshLayout.setOnRefreshListener {
            profileViewModel.update()
            swipeRefreshLayout.isRefreshing = false
        }

        signOutButton.setOnClickListener {
            loginRepository.logout()
            goToLoginActivity()
        }
    }

    private fun goToLoginActivity() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    private fun updateUI(userData: ClientInfoResponse) {
        amountOnHoldView.text = "${userData.lockedAmount} ₽"
        amountAvailableView.text = "${userData.availableAmount} ₽"
    }
}
