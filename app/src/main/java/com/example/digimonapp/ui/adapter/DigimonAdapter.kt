package com.example.digimonapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.digimonapp.R
import com.example.digimonapp.domain.models.Digimon
import com.example.digimonapp.domain.models.DigimonTest
import com.example.digimonapp.domain.models.enums.DigimonLevel
import com.example.digimonapp.ui.listeners.DigimonListListener
import com.squareup.picasso.Picasso

class DigimonAdapter(val context: Context) :
    RecyclerView.Adapter<DigimonAdapter.DigimonViewHolder>() {

    private val digimons = mutableListOf<Digimon>()
    private var digimonListListener: DigimonListListener? = null

    fun setDigimons(albums: List<Digimon>, digimonListListener: DigimonListListener) {
        this.digimonListListener = digimonListListener
        if (this.digimons != albums) {
            this.digimons.clear()
            this.digimons.addAll(albums)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DigimonViewHolder {
        val itemView =
            LayoutInflater.from(context).inflate(R.layout.list_item_digimon, parent, false);
        val digimon = Digimon(
            "Silphymon",
            "https://digimon.shadowsmith.com/img/silphymon.jpg",
            DigimonLevel.Ultimate,
            true
        )
        digimons.add(0, digimon)
        return DigimonViewHolder(itemView)
    }

    override fun onBindViewHolder(digimonHolder: DigimonViewHolder, position: Int) {
        val digimon = digimons[position]
        digimonHolder.setData(digimon, position)
        digimonHolder.setListeners()
    }

    override fun getItemCount(): Int = digimons.size

    inner class DigimonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        private var currentPosition: Int = -1
        private var currentDigimon: Digimon? = null

        private val txvDigimonName = itemView.findViewById<TextView>(R.id.txv_digimom_name)
        private val imvDigimonImage = itemView.findViewById<ImageView>(R.id.imv_digimon)
        private val imvDelete = itemView.findViewById<ImageView>(R.id.imv_delete)
        private val imvFavorite = itemView.findViewById<ImageView>(R.id.imv_favorite)

        private val icFavoriteFilledImage = ResourcesCompat.getDrawable(
            context.resources,
            R.drawable.ic_baseline_favorite_white_24, null
        )
        private val icFavoriteBorderedImage = ResourcesCompat.getDrawable(
            context.resources,
            R.drawable.ic_baseline_favorite_border_white_24, null
        )

        fun setData(digimon: Digimon, position: Int) {
            txvDigimonName.text = digimon.name
            Picasso.get().load(digimon.img).into(imvDigimonImage);

            if (digimon.isFavorite)
                imvFavorite.setImageDrawable(icFavoriteFilledImage)
            else
                imvFavorite.setImageDrawable(icFavoriteBorderedImage)

            this.currentPosition = position
            this.currentDigimon = digimon
        }

        fun setListeners() {
            imvDelete.setOnClickListener(this@DigimonViewHolder)
            imvFavorite.setOnClickListener(this@DigimonViewHolder)
        }

        override fun onClick(view: View?) {
            /* when(view!!.id){
                 R.id.imv_delete -> deleteItem()
                 R.id.imv_favorite -> addToFavorite()
             }*/
        }

        private fun addToFavorite() {
            currentDigimon?.isFavorite = (!currentDigimon?.isFavorite!!)
            if (currentDigimon?.isFavorite!!) {
                imvFavorite.setImageDrawable(icFavoriteFilledImage)
                DigimonTest.favoriteDigimonList.add(currentDigimon!!)
            } else {
                imvFavorite.setImageDrawable(icFavoriteBorderedImage)
                DigimonTest.favoriteDigimonList.remove(currentDigimon!!)
            }
        }

        /*private fun deleteItem() {
            digimonList.removeAt(currentPosition)
            notifyItemRemoved(currentPosition)
            notifyItemRangeChanged(currentPosition,digimonList.size)
            DigimonTest.favoriteDigimonList.remove(currentDigimon!!)
        }*/

    }
}

