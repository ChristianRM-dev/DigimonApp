package com.example.digimonapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.digimonapp.R
import com.example.digimonapp.domain.models.Digimon
import com.squareup.picasso.Picasso

class FavoriteAdapter(val context: Context,val favDigimonList:List<Digimon> ) :
    RecyclerView.Adapter<FavoriteAdapter.FavDigimonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavDigimonViewHolder {

        val itemView =
            LayoutInflater.from(context).inflate(R.layout.list_item_favorite, parent, false)
        return FavDigimonViewHolder(itemView)
    }


    override fun getItemCount(): Int = favDigimonList.size

    override fun onBindViewHolder(itemViewHolder: FavDigimonViewHolder, position: Int) {

        val digimon = favDigimonList[position]
        itemViewHolder.setData(digimon, position)
    }

    inner class FavDigimonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var currentPosition: Int = -1
        private var currentDigimon: Digimon? = null

        private val txvDigimonName = itemView.findViewById<TextView>(R.id.txv_digimon_name)
        private val imvDigimonImage = itemView.findViewById<ImageView>(R.id.imv_digimon)

        fun setData(digimon: Digimon, position: Int) {

            txvDigimonName.text = digimon.name
            Picasso.get().load(digimon.img).into(imvDigimonImage);

            this.currentPosition = position
            this.currentDigimon = digimon
        }
    }
}
