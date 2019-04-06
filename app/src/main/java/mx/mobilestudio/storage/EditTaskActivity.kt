package mx.mobilestudio.storage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.view.View
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_edit_task.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import mx.mobilestudio.storage.roomdatabase.AppDatabase
import mx.mobilestudio.storage.roomdatabase.EntityTODO

class EditTaskActivity : AppCompatActivity() {


    lateinit var database: AppDatabase
    var entityTODO: EntityTODO? = null
    var entityTodoTask: EntityTODO? = null
    var idEntity = Int.MIN_VALUE


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_task)
        idEntity = Int.MIN_VALUE
        database = Room.databaseBuilder(
            this@EditTaskActivity,
            AppDatabase::class.java,
            "MyRoomDatabase"
        ).build()

        intent?.extras?.let { bundle ->
            idEntity = bundle.getInt("IdEntity", Int.MIN_VALUE)

        }

        //porque existe en nuestra base de datos
        if (idEntity == Int.MIN_VALUE) {
            btnDeleteTask?.visibility = View.GONE
        } else {
            fillTask(idEntity)
        }
        btnDeleteTask?.setOnClickListener {
            GlobalScope.launch {
                database.todoDAO().deleEntity(entityTodoTask)
            }
            finish()
        }
        btnSaveTask?.setOnClickListener {
            val titleTask = etTitle?.text?.toString() ?: ""
            val descriptionTask = etDEscription?.text?.toString() ?: ""

            if (idEntity == Int.MIN_VALUE) {
                entityTodoTask = EntityTODO(title = titleTask, descriptionTask = descriptionTask)
                GlobalScope.launch {
                    entityTodoTask?.let { it1 ->
                        database.todoDAO().insertAll(it1)
                    }
                }

            } else {
                entityTodoTask?.title = titleTask
                entityTodoTask?.descriptionTask = descriptionTask

                GlobalScope.launch {
                    entityTodoTask?.let { it1 ->
                        database.todoDAO().updateTask(it1)
                    }
                }
            }

            //entityTodoTask = EntityTODO(title = titleTask, descriptionTask = descriptionTask)


            finish()
        }

    }

    private fun fillTask(idEntity: Int) {


        GlobalScope.launch {
            entityTodoTask = database.todoDAO().findTaskById(idEntity)

            Looper.getMainLooper()?.queue?.addIdleHandler {
                etTitle?.setText(entityTodoTask?.title ?: "")
                etDEscription?.setText(entityTodoTask?.descriptionTask ?: "")
                return@addIdleHandler false
            }

        }


    }
}
