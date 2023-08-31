package com.example.myapplication

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.ComponentActivity
import com.bumptech.glide.Glide

class FullScreenGifActivity : ComponentActivity() {
    private lateinit var fullScreenImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.full_screen_gif)

        fullScreenImageView = findViewById(R.id.fullscreenImageView)

        val gifUrl = intent.getStringExtra("gifUrl")

        Glide.with(this)
            .load(gifUrl)
            .into(fullScreenImageView)
    }
}