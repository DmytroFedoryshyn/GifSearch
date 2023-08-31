package com.example.myapplication

data class Gif(
    val id: String,
    val title: String,
    val images: GifImages
)

data class GifImages(
    val original: GifImage,
)

data class GifImage(
    val url: String
)