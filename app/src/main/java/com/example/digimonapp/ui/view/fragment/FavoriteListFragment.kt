package com.example.digimonapp.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.digimonapp.R
import com.example.digimonapp.ui.adapter.FavoriteAdapter
import com.example.digimonapp.domain.models.Digimon
import com.example.digimonapp.ui.listeners.DigimonFavoriteListListener
import com.example.digimonapp.ui.viewmodel.DigimonViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class FavoriteListFragment : Fragment() {
    private lateinit var favoriteDigimonList: MutableList<Digimon>
    private lateinit var favoriteAdapter: FavoriteAdapter
    private lateinit var recyclerView: RecyclerView
    private val digimonViewModel: DigimonViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favorite_list, container, false)
        digimonViewModel.loadFavoriteDigimons()
        digimonViewModel.favoriteDigimons.observe(viewLifecycleOwner) {
            setupRecyclerView(view, it)
        }


        return view
    }

    private fun setupRecyclerView(view: View, digimons: List<Digimon>) {
        val context = requireContext()
        favoriteDigimonList = digimons as MutableList<Digimon>
        favoriteAdapter = FavoriteAdapter(context, favoriteDigimonList)
        recyclerView = view.findViewById(R.id.favorite_recycler_view)
        recyclerView.adapter = favoriteAdapter
        recyclerView.setHasFixedSize(true)

        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = RecyclerView.VERTICAL
        recyclerView.layoutManager = layoutManager

        itemTouchHelper.attachToRecyclerView(recyclerView)
    }


    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
        ItemTouchHelper.UP or ItemTouchHelper.DOWN, ItemTouchHelper.RIGHT
    ) {

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            targetViewHolder: RecyclerView.ViewHolder
        ): Boolean {
            // Called when the item is dragged.
            val fromPosition = viewHolder.adapterPosition
            val toPosition = targetViewHolder.adapterPosition

            Collections.swap(favoriteDigimonList, fromPosition, toPosition)

            recyclerView.adapter?.notifyItemMoved(fromPosition, toPosition)

            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            // Called when the item is swiped.
            val position = viewHolder.adapterPosition
            val digimon = favoriteDigimonList[position]
            removeFavorite(position)
            updateDigimonDB(digimon, false)

            Snackbar.make(recyclerView, "${digimon.name} deleted", Snackbar.LENGTH_LONG)
                .setAction("UNDO") {
                    undoDelete(position, digimon)
                    updateDigimonDB(digimon, true)
                }
                .show()
        }
    })

    private fun removeFavorite(position: Int) {
        favoriteDigimonList.removeAt(position)
        favoriteAdapter.notifyItemRemoved(position)
        favoriteAdapter.notifyItemRangeChanged(position, favoriteDigimonList.size)
    }

    private fun updateDigimonDB(digimon: Digimon, isFavorite: Boolean) {
        digimon.isFavorite = isFavorite
        digimonViewModel.updateDigimon(digimon).run {

        }
    }

    private fun undoDelete(position: Int, digimon: Digimon) {
        favoriteDigimonList.add(position, digimon)
        favoriteAdapter.notifyItemInserted(position)
        favoriteAdapter.notifyItemRangeChanged(position, favoriteDigimonList.size)
    }
}