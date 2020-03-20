package com.example.xtbd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.xtbd.Adapter.UserAdapter
import com.example.xtbd.Database.DB

class Register : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val register: Button  = findViewById(R.id.register)

        register.setOnClickListener {
            val db = DB()
            var userAdapter = UserAdapter()
            userAdapter.email="abdulhasif99@gmail.com"
            userAdapter.age = 10
            userAdapter.height = 155
            userAdapter.weight = 54
            userAdapter.mobile="9994668196"
            userAdapter.password="Hasif@123"
            userAdapter.userName="Hasif"
            db.registerUser(userAdapter)

        }
    }
}
