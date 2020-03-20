package com.example.xtbd.Database

import android.util.Log
import com.example.xtbd.Adapter.UserAdapter
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

    fun registerUser(user: UserAdapter){
        ref.child(user.userName).setValue(user)
       /* ref.addValueEventListener(object: ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                val value = p0.getValue<String>()
                Log.e("Users",value.toString())
            }
        })*/
    }
}