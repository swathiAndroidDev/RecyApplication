package com.example.recyapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyapplication.data.Albums

class AlbumsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var albumsList = mutableListOf<Albums>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return AlbumsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_albums, parent, false))
    }

    override fun getItemCount(): Int {
        return albumsList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is AlbumsViewHolder){
            holder.bindData(albumsList[position])
        }
    }

    fun swapList(list: List<Albums>){
        albumsList.clear()
        albumsList.addAll(list)
        notifyDataSetChanged()
    }

    internal class AlbumsViewHolder(view : View) : RecyclerView.ViewHolder(view){

        private var albumIdText : TextView
        private var albumTitleText : TextView

        init {
            albumIdText = view.findViewById(R.id.album_id)
            albumTitleText = view.findViewById(R.id.album_title)
        }

        fun bindData(albums: Albums){

            albumIdText.text = "${albums.id}"
            albumTitleText.text = albums.title

        }

    }
}