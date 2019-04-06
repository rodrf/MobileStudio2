package mx.mobilestudio.storage

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_list_todo.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import mx.mobilestudio.storage.roomdatabase.AppDatabase
import mx.mobilestudio.storage.roomdatabase.EntityTODO
import java.io.LineNumberReader

class ListTODOActivity : AppCompatActivity() {

    lateinit var database: AppDatabase
    val adapter = AdapterClass()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_todo)




        toolbarListTodo?.let { toolbar ->
            val preferences = getSharedPreferences(preferencesName, Context.MODE_PRIVATE)
            val userName = preferences?.getString("name", "default")
            toolbar.title = "TODO List de $userName"
            setSupportActionBar(toolbar)
        }

        rcTodoList?.layoutManager = LinearLayoutManager(this@ListTODOActivity, RecyclerView.VERTICAL, false)
        //val adapter = AdapterClass()
        rcTodoList?.adapter = adapter

        adapter.setListener { position, entityTODO ->
            Log.e("Position Item", "$position")
            val intent = Intent(this@ListTODOActivity, EditTaskActivity::class.java)
            intent.putExtra("IdEntity", entityTODO.id)
            startActivity(intent)
        }



        database = Room.databaseBuilder(
            this@ListTODOActivity,
            AppDatabase::class.java,
            "MyRoomDatabase"
        ).build()

        fabAdd?.setOnClickListener {
            //TODO: No recuerdo si el destino era esa clase
            val intent = Intent(this@ListTODOActivity, EditTaskActivity::class.java)
            startActivity(intent)
        }




        GlobalScope.launch {
            database.todoDAO().insertAll(
                EntityTODO(title = "Task 1", descriptionTask = "Description 1"),
                EntityTODO(title = "Task 1", descriptionTask = "Description 1"),
                EntityTODO(title = "Task 1", descriptionTask = "Description 1")
            )
        }




    }

    override fun onResume() {
        super.onResume()
        GlobalScope.launch {
            database.todoDAO().insertAll(
                EntityTODO(title = "Task 1", descriptionTask = "Description 1"),
                EntityTODO(title = "Task 1", descriptionTask = "Description 1"),
                EntityTODO(title = "Task 1", descriptionTask = "Description 1")
            )
        }
        val taskList = database.todoDAO().getAll()
        taskList.map {
            Log.d("Entity", "$it")
        }
        runOnUiThread {
            adapter.dataList = taskList
        }

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_list, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.actionLogout) {
            val preferences = getSharedPreferences(preferencesName, Context.MODE_PRIVATE)
            preferences?.edit()?.clear()?.apply()
            val intent = Intent(this@ListTODOActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
