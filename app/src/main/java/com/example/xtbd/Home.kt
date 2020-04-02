package com.example.xtbd

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.net.URI

class Home : AppCompatActivity() {
    var photoURI: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val addPhoto: TextView = findViewById(R.id.x_ray)
        val preview: TextView = findViewById(R.id.predict)
        addPhoto.setOnClickListener()
        {
            val gallery: Intent = Intent(Intent.ACTION_PICK)
            gallery.type = "image/*"
            startActivityForResult(gallery,1000)
        }
        preview.setOnClickListener()
        {
            val preview: Intent = Intent(this@Home,Preview::class.java)
            preview.putExtra("uri",photoURI.toString())
            startActivity(preview)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(data?.data != null)
        {
            photoURI = data?.data
            Toast.makeText(applicationContext,"Photo Selected"+photoURI, Toast.LENGTH_LONG).show()
        }
        else
        {
            Toast.makeText(applicationContext,"Image not selected",Toast.LENGTH_LONG).show()
        }
        super.onActivityResult(requestCode, resultCode, data)
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
        exitDialog.setNegativeButton("No"){ dialog: DialogInterface?, which: Int -> }
        val alertDialog: AlertDialog = exitDialog.create()
        alertDialog.show()
    }
}
