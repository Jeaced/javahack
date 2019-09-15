package kazanexpress.ru.javahack.ui.mocked

import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import android.widget.Button
import kazanexpress.ru.javahack.R
import kazanexpress.ru.javahack.ui.login.LoginActivity


class CaptureDocImageActivity : AppCompatActivity() {
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

    }

    override fun onBackPressed() {
        moveTaskToBack(true)
    }
}
