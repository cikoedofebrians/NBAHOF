package com.relio.nbahof

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import coil.load
import coil.transform.CircleCropTransformation

class AboutActivity : AppCompatActivity() {

    private lateinit var profileImageView: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        title = "Profile"
        profileImageView = findViewById(R.id.profile_image_view)
        profileImageView.load("https://avatars.githubusercontent.com/u/94286488?v=4") {
            transformations(CircleCropTransformation())
        }
    }
}