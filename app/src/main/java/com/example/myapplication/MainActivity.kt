package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {

    private lateinit var gifList: RecyclerView
    private lateinit var gifAdapter: GifAdapter
    private lateinit var gifApiService: GifApiService
    private lateinit var searchQueryText: EditText
    private lateinit var limit: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        gifList = findViewById(R.id.gifRecyclerView)
        searchQueryText = findViewById(R.id.gifSearch)
        limit = findViewById(R.id.limit)
        gifAdapter = GifAdapter(this)
        gifList.layoutManager = LinearLayoutManager(this)
        gifList.adapter = gifAdapter

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.giphy.com/v1/gifs/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

         gifApiService = retrofit.create(GifApiService::class.java)


    }

    fun fillList(view: View) {
        lifecycleScope.launch {
            val response = gifApiService.getGifs(
                "XvE21jA4UU5FT8PdujqEdug9KquUUDtR",
                searchQueryText.text.toString(),
                limit.text.toString().toInt(),
                0,
                "g",
                "en",
                "messaging_non_clips"
            )

            if (response.isSuccessful) {
                val gifResponse = response.body()
                val gifs = gifResponse?.data ?: emptyList()
                gifAdapter.updateData(gifs)
            }
        }
    }
}
