package kazanexpress.ru.javahack.ui.mocked

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kazanexpress.ru.javahack.R
import kazanexpress.ru.javahack.ui.profile.ProfileActivity

class FormsActivity3 : AppCompatActivity() {

    private lateinit var finishButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forms3)

        finishButton = findViewById(R.id.finish)

        finishButton.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
    }
}
