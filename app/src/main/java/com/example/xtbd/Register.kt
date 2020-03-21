package com.example.xtbd

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.example.xtbd.Adapter.UserAdapter
import com.example.xtbd.Database.DB
import com.google.android.material.snackbar.Snackbar

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val register: Button  = findViewById(R.id.register)
        val userName: EditText = findViewById(R.id.userName)
        val name: EditText = findViewById(R.id.name)
        val mobile: EditText = findViewById(R.id.mobile)
        val email: EditText = findViewById(R.id.email)
        val weight: EditText = findViewById(R.id.weight)
        val height: EditText = findViewById(R.id.height)
        val password: EditText = findViewById(R.id.password)
        val confirmPassword : EditText = findViewById(R.id.confirmPassword)
        val age: EditText = findViewById(R.id.age)

        register.setOnClickListener {
            val db = DB()
            var userAdapter = UserAdapter()
            if(email.text.toString().isNotEmpty() || age.text.toString().isNotEmpty() || height.text.toString().isNotEmpty() || weight.text.toString().isNotEmpty() || mobile.text.toString().isNotEmpty() ||  name.text.toString().isNotEmpty() || userName.text.toString().isNotEmpty() || password.text.toString().isNotEmpty() || confirmPassword.text.toString().isNotEmpty())
            {

                if(confirmPassword.text.toString() == password.text.toString())
                {
                    userAdapter.email= email.text.toString()
                    userAdapter.age = age.text.toString().toInt()
                    userAdapter.height = height.text.toString().toInt()
                    userAdapter.weight = weight.text.toString().toInt()
                    userAdapter.mobile= mobile.text.toString()
                    userAdapter.password =  password.text.toString()
                    userAdapter.userName= userName.text.toString()
                    userAdapter.name = name.text.toString()
                    val processDialog: ProgressDialog = ProgressDialog(this@Register)
                    processDialog.setCancelable(false)
                    processDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
                    processDialog.setTitle("Registration")
                    processDialog.setMessage("Please wait registration on process")
                    processDialog.show()
                    db.registerUser(userAdapter,it,this@Register,applicationContext)
                }
                else
                {
                    Snackbar.make(it,"Please enter same password",Snackbar.LENGTH_LONG).show()
                    return@setOnClickListener
                }

            }
            else
            {
                Snackbar.make(it,"Please fill all the fields",Snackbar.LENGTH_LONG).show()
                return@setOnClickListener
            }

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
        exitDialog.setTitle("Exit Registration")
        exitDialog.setMessage("Are you sure do you want to exit registration").setCancelable(false)
        exitDialog.setPositiveButton("Yes"){dialogInterface, which ->
            run {
                startActivity(Intent(this@Register, Login::class.java))
                finish()
            }
        }
        exitDialog.setNegativeButton("No"){ dialog: DialogInterface?, which: Int -> }
        val alertDialog: AlertDialog = exitDialog.create()
        alertDialog.show()
    }
}
