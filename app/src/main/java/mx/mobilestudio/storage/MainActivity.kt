package mx.mobilestudio.storage

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

const val preferencesName = "myLogingPreferences"

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val preference = getSharedPreferences("mySharedPreferences", Context.MODE_PRIVATE)


//        val editPreferences = preference?.edit()
//        editPreferences?.putBoolean("isLogged", true)
//        editPreferences?.putInt("Age", 20)
//        editPreferences?.putString("Name", "Rodrigo")
//
//        //editPreferences?.commit()
//        editPreferences?.apply()
//        val name = preference.getString("Name","default")
//        val isLogged =preference?.getBoolean("isLogged", false)
//
//        Log.d("Values --------->", "$name")
//        Log.d("Values --------->", "$isLogged")
//
//
//        preference?.edit()?.remove("Age")?.commit()
//
//        if (preference?.contains("Age")==true){
//            Log.e("Age contains", "${preference.getInt("Age",0)}")
//        }else{
//            Log.e("Age", "Not exist")
//        }
        val preferences = getSharedPreferences(preferencesName, Context.MODE_PRIVATE)

        if (preferences?.getBoolean("isLogged", false) == true) {
            val intent = Intent(this@MainActivity, ListTODOActivity::class.java)
            startActivity(intent)
        } else {
            val intent = Intent(this@MainActivity, LoginActivity::class.java)
            startActivity(intent)
        }
        finish()
    }
}
