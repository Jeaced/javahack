package kazanexpress.ru.javahack.ui.mocked

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kazanexpress.ru.javahack.R

class FormsActivity2 : AppCompatActivity() {

    private lateinit var nextButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forms2)

        nextButton = findViewById(R.id.next)

        nextButton.setOnClickListener {
            startActivity(Intent(this, FormsActivity3::class.java))
        }
    }
}
