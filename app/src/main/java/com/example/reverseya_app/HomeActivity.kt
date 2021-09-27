package com.example.reverseya_app

import android.icu.text.CaseMap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.OnBackPressedCallback
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

enum class ProviderType
{
    BASIC
}

class HomeActivity : AppCompatActivity() {

    val emailtext : EditText = findViewById(R.id.text1)
    val proveedor : EditText = findViewById(R.id.proveedor)
    val logout : Button = findViewById(R.id.cerrarsesion)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val bundle : Bundle? = intent.extras
        bundle?.getString("email")
        bundle?.getString("provider")
        setup((emailtext?:"") as String, (proveedor?: "") as String)
    }
    private fun setup (email:String, provider: String)
    {

        logout.setOnClickListener()
        {
            FirebaseAuth.getInstance().signOut()
        }
    }

}