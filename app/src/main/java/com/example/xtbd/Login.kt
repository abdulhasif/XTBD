package com.example.xtbd

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.example.xtbd.Database.DB
import com.google.android.material.snackbar.Snackbar

class Login : AppCompatActivity() {
    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val login: Button = findViewById(R.id.login)
        val register: Button = findViewById(R.id.register)
        val userName: EditText = findViewById(R.id.userName)
        val password: EditText = findViewById(R.id.password)

        login.setOnClickListener {
            val db = DB()
            if(userName.text.toString().isNotEmpty() || password.text.toString().isNotEmpty())
            {

                db.loginUser(userName.text.toString(),password.text.toString(),it,applicationContext,this@Login)
            }
            else
            {
                Snackbar.make(it,"Please enter the user name and the password", Snackbar.LENGTH_LONG).show()
            }
        }

        register.setOnClickListener{
            val registration = Intent(this@Login, Register::class.java)
            startActivity(registration)
            finish()
        }
    }

    fun exit(view: View) {
        exitD()
    }

    override fun onBackPressed() {
        exitD()
    }

    private fun exitD()
    {
        val exitDialog: AlertDialog.Builder = AlertDialog.Builder(this)
        exitDialog.setTitle("Exit")
        exitDialog.setMessage("Are you sure do you want to exit").setCancelable(false)
        exitDialog.setPositiveButton("Yes"){dialogInterface, which -> finish()}
        exitDialog.setNegativeButton("No"){dialog: DialogInterface?, which: Int -> }
        val alertDialog:AlertDialog = exitDialog.create()
        alertDialog.show()
    }

}
