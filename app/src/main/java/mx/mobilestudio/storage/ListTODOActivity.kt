package mx.mobilestudio.storage

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_list_todo.*

class ListTODOActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_todo)


        toolbarListTodo?.let { toolbar ->
            val preferences = getSharedPreferences(preferencesName, Context.MODE_PRIVATE)
            val userName = preferences?.getString("name","default")
            toolbar.title = "TODO List de $userName"
            setSupportActionBar(toolbar)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_list, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.actionLogout){
            val preferences = getSharedPreferences(preferencesName, Context.MODE_PRIVATE)
            preferences?.edit()?.clear()?.apply()
            val intent = Intent(this@ListTODOActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
