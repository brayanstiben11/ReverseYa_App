package com.example.reverseya_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    val buttonsignup : Button = findViewById(R.id.signupbutton)
    val emailtext : EditText = findViewById(R.id.email)
    val passwordtext : EditText = findViewById(R.id.password)
    val buttonlogin : Button = findViewById(R.id.loginbutton)

    override fun onCreate(savedInstanceState: Bundle?) {

        Thread.sleep(200)
        setTheme(R.style.EmptyTheme)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val analytics : FirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString("message","integracion firebase completa")
        analytics.logEvent("InitScreen",bundle)

        setup()

    }

    private fun setup()
    {
        title = "Autenticacion"
        buttonsignup.setOnClickListener()
        {
            if( emailtext.text.isNotEmpty() && passwordtext.text.isNotEmpty())
            {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(emailtext.text.toString(),passwordtext.text.toString()).addOnCompleteListener {
                    if (it.isSuccessful)
                    {
                        showhome(it.result?.user?.email?:"", ProviderType.BASIC)
                    }
                    else {
                    showAlert()
                    }
                }
            }

        }

        buttonlogin.setOnClickListener()
        {
            if( emailtext.text.isNotEmpty() && passwordtext.text.isNotEmpty())
            {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(emailtext.text.toString(),passwordtext.text.toString()).addOnCompleteListener {
                    if (it.isSuccessful)
                    {
                        it.result?.user?.email?.let { it1 -> showhome(it1,ProviderType.BASIC) }
                    }
                    else {
                        showAlert()
                    }
                }
            }

        }
    }

    private fun showAlert ()
    {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog : AlertDialog = builder.create()
        dialog.show()
    }

    private fun showhome (email:String, provider : ProviderType)
    {
        val homeIntent : Intent = Intent ( this, HomeActivity::class.java).apply {
            putExtra("email",email)
            putExtra("provider",provider.name)
        }
        startActivity(homeIntent)
    }
}