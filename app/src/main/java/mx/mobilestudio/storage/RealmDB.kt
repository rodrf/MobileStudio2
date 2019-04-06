package mx.mobilestudio.storage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.room.PrimaryKey
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmObject

class RealmDB : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_realm)

        Realm.init(this@RealmDB)

        val realmConfiguration = RealmConfiguration.Builder().apply {
            name("MyRealmDB")
            schemaVersion(1)
        }.build()

        val realm = Realm.getInstance(realmConfiguration)
        val alumno1 = Alumno(nombre = "Juan", grupo = "KotlinAndroid")
        val alumno2 = Alumno(nombre = "Pedro", grupo = "KotlinAndroid")
        val alumno3 = Alumno(nombre = "Paco", grupo = "KotlinAndroid")

        realm.beginTransaction()
        realm.copyToRealm(alumno1)
        realm.copyToRealm(alumno2)
        realm.copyToRealm(alumno3)
        realm.commitTransaction()

        val alumnoFromRealm = realm.where(Alumno::class.java)
            .findAll()

        val alumnoList = alumnoFromRealm?.toList()
        alumnoList?.map { alumnoReal ->
            Log.d("Alumno From Realm", "${alumnoReal?.toString()}")
        }


        realm?.executeTransaction { realObj ->
            alumnoFromRealm?.deleteFirstFromRealm()
        }

        val alumnListAfterDelete = alumnoFromRealm.toList()
        alumnListAfterDelete.map {
            Log.d("Alumno from realm", "${it?.toString()}")
        }

    }
}
//Usar var porque realm debe tener la capacidad de poder leer y escribir datos

open class Alumno(
    @PrimaryKey var id: Int = 0,
    var nombre: String? = "",
    var grupo: String? = ""
) :
    RealmObject()