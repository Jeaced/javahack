package kazanexpress.ru.javahack.ui.info

import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import android.widget.Button
import kazanexpress.ru.javahack.R
import kazanexpress.ru.javahack.ui.login.LoginActivity


class CaptureDocImageActivity : AppCompatActivity() {

//    private lateinit var textView: TextView
//    private lateinit var signOutButton: Button
//    private lateinit var loginRepository: LoginRepository
    private lateinit var captureButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_capture_doc_image)

            //status bar color
            val window = this.window

            // clear FLAG_TRANSLUCENT_STATUS flag:
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

            // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)

            // finally change the color
            window.statusBarColor = ContextCompat.getColor(this, R.color.photoGray)

        captureButton = findViewById(R.id.capture)

        captureButton.setOnClickListener {
            startActivity(Intent(this, FormsActivity::class.java))
        }

//        textView = findViewById(R.id.textView)
//        signOutButton = findViewById(R.id.signOutButton)
//        loginRepository = LoginRepository(LoginDataSource())
//
//        signOutButton.setOnClickListener {
//            loginRepository.logout()
//            goToLoginActivity()
//        }


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
