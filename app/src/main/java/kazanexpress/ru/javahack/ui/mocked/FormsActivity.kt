package kazanexpress.ru.javahack.ui.mocked

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kazanexpress.ru.javahack.R

class FormsActivity : AppCompatActivity() {

    private lateinit var acceptButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forms)

        acceptButton = findViewById(R.id.accept)

        acceptButton.setOnClickListener {
            startActivity(Intent(this, FormsActivity2::class.java))
        }
    }
}
