package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class GifAdapter(private val context: Context) : RecyclerView.Adapter<GifAdapter.GifViewHolder>() {

    private val gifList: MutableList<Gif> = mutableListOf()
    fun updateData(newGifs: List<Gif>) {
        val previousSize = gifList.size
        gifList.clear()
        gifList.addAll(newGifs)

        if (previousSize == 0) {
            notifyDataSetChanged()
        } else {
            notifyItemRangeRemoved(0, previousSize)
            notifyItemRangeInserted(0, newGifs.size)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_gif, parent, false)
        return GifViewHolder(view)
    }


    override fun onBindViewHolder(holder: GifViewHolder, position: Int) {
        val gif = gifList[position]
        val originalGifUrl:String = gif.images.original.url

        Glide.with(context)
            .load(originalGifUrl)
            .into(holder.gifImageView)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, FullScreenGifActivity::class.java)
            intent.putExtra("gifUrl", originalGifUrl)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return gifList.size
    }

    inner class GifViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val gifImageView: ImageView = itemView.findViewById(R.id.gifImageView)
    }
}
