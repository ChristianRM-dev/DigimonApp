package com.example.digimonapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.digimonapp.R
import com.example.digimonapp.databinding.ListItemDigimonBinding
import com.example.digimonapp.domain.models.Digimon
import com.example.digimonapp.ui.listeners.DigimonListListener
import com.squareup.picasso.Picasso

class DigimonAdapter(
    val context: Context,
    val digimons: List<Digimon>,
    val listener: DigimonListListener
) :
    RecyclerView.Adapter<DigimonAdapter.DigimonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DigimonViewHolder {
        val itemView =
            LayoutInflater.from(context).inflate(R.layout.list_item_digimon, parent, false)
        return DigimonViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DigimonViewHolder, position: Int) {
        val digimon = digimons[position]
        holder.bind(digimon, position)
        holder.setListeners()
    }

    override fun getItemCount(): Int = digimons.size

    inner class DigimonViewHolder(
        view: View
    ) : RecyclerView.ViewHolder(view), View.OnClickListener {
        private val binding = ListItemDigimonBinding.bind(view)
        private var currentPosition: Int = -1
        private val noFavoriteIcon = ResourcesCompat.getDrawable(
            context.resources,
            R.drawable.ic_baseline_favorite_border_white_24, null
        )
        private val favoriteIcon = ResourcesCompat.getDrawable(
            context.resources,
            R.drawable.ic_baseline_favorite_white_24, null
        )

        fun bind(digimon: Digimon, position: Int) {
            binding.txvDigimomName.text = digimon.name
            Picasso.get().load(digimon.img).into(binding.imvDigimon)

            this.currentPosition = position
            switchFavImage()
        }

        fun setListeners() {
            //binding.imvDelete.setOnClickListener(this@DigimonViewHolder)
            binding.imvFavorite.setOnClickListener(this@DigimonViewHolder)
        }

        override fun onClick(view: View?) {
            when (view!!.id) {
                //  R.id.imv_delete -> deleteItem()
                R.id.imv_favorite -> addToFavorite()
            }
        }

        private fun addToFavorite() {
            val digimon = digimons[this.currentPosition];
            digimons[this.currentPosition].isFavorite = !digimon.isFavorite

            listener.onFavoriteClick(digimon)
            switchFavImage();
        }

        private fun switchFavImage() {
            val digimon = digimons[this.currentPosition];
            val favoriteImage = if (digimon.isFavorite) favoriteIcon else noFavoriteIcon
            binding.imvFavorite.setImageDrawable(favoriteImage)
        }
    }
}

