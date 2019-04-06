package mx.mobilestudio.storage

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.edit
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btnLogin?.setOnClickListener{
            val name = etLogin?.text?.toString()
            if (!name.isNullOrBlank()){
                val preferences = getSharedPreferences(preferencesName, Context.MODE_PRIVATE)
                preferences?.edit {
                    putString("user_name",name)
                    putBoolean("isLogged", true)

                }
                val intent = Intent(this@LoginActivity,
                    ListTODOActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}
