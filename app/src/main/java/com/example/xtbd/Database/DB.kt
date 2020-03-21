package com.example.xtbd.Database

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Toast
import com.example.xtbd.Adapter.UserAdapter
import com.example.xtbd.Home
import com.example.xtbd.Login
import com.example.xtbd.Register
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class DB
{
    private val database = Firebase.database
    private val ref = database.getReference("Users")

    fun registerUser(
        user: UserAdapter,
        register: View,
        register1: Register,
        applicationContext: Context
    ){
        val processDialog: ProgressDialog = ProgressDialog(register1)
        processDialog.setCancelable(false)
        processDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        processDialog.setTitle("Registering")
        processDialog.setMessage("Please wait registration on process")
        processDialog.show()
        ref.child(user.userName).addValueEventListener(object: ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                val value = p0.getValue<UserAdapter>()
                if (value != null) {
                    Snackbar.make(register,"User name already exists",Snackbar.LENGTH_LONG).show()
                    processDialog.dismiss()
                }
                else
                {
                    ref.child(user.userName).setValue(user)
                    val intent: Intent  = Intent(register1, Login::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    applicationContext.startActivity(intent)
                    Toast.makeText(applicationContext,"Registered Successfully",Toast.LENGTH_LONG).show()
                    register1.finish()

                }
            }
        })
    }

    fun loginUser(
        userName: String,
        password: String,
        login: View,
        applicationContext: Context,
        login1: Login
    ) {
        val processDialog: ProgressDialog = ProgressDialog(login1)
        processDialog.setCancelable(false)
        processDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        processDialog.setTitle("Logging In")
        processDialog.setMessage("Please wait logging in")
        processDialog.show()
        ref.child((userName)).addValueEventListener(object: ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                val value = p0.getValue<UserAdapter>()
                if (value != null) {
                    if(value.password == password)
                    {
                        val intent: Intent  = Intent(login1, Home::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        applicationContext.startActivity(intent)
                        login1.finish()
                    }
                    else
                    {
                        Snackbar.make(login,"Invalid Password",Snackbar.LENGTH_LONG).show()
                        processDialog.dismiss()
                    }
                }
                else
                {
                    Snackbar.make(login,"User name does not exists",Snackbar.LENGTH_LONG).show()
                    processDialog.dismiss()
                }
            }

        })
    }
}