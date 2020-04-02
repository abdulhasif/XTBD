package com.example.xtbd

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast

class Preview : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview)
        val xray: ImageView = findViewById(R.id.xray)
        val intent : Intent = intent
        if(intent.getStringExtra("uri") != null)
        {
            var photoUri : Uri? = Uri.parse(intent.getStringExtra("uri"))
            xray.setImageURI(photoUri)
        }
        else
        {
            Toast.makeText(applicationContext,"Photo not found", Toast.LENGTH_LONG).show()
        }



    }
}
