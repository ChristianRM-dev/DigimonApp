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
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class FavoriteListFragment : Fragment(), DigimonFavoriteListListener {
    private lateinit var favoriteDigimonList: List<Digimon>
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

    private fun setupRecyclerView(view: View, favoriteList: List<Digimon>) {
        val context = requireContext()
        this.favoriteDigimonList = favoriteList;
        favoriteAdapter = FavoriteAdapter(context, this.favoriteDigimonList)

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
            val deletedDigimon: Digimon = favoriteDigimonList[position]

            deleteItem(position)
            /*    updateDigimonList(deletedDigimon, false)

                Snackbar.make(recyclerView, "Deleted", Snackbar.LENGTH_LONG)
                    .setAction("UNDO") {
                        undoDelete(position, deletedDigimon)
                        updateDigimonList(deletedDigimon, true)
                    }
                    .show()*/
        }
    })

    private fun deleteItem(position: Int) {
        digimonViewModel.removeFavorite(favoriteDigimonList[position]).run {

        }
        favoriteAdapter.notifyItemRemoved(position)
        favoriteAdapter.notifyItemRangeChanged(position, favoriteDigimonList.size)
    }

    private fun updateDigimonList(deletedDigimon: Digimon, isFavorite: Boolean) {
        /*val digimonList = DigimonTest.digimonList!!
        val position = digimonList.indexOf(deletedDigimon)
        digimonList[position].isFavorite = isFavorite*/
    }

    private fun undoDelete(position: Int, deletedDigimon: Digimon) {

        /*  favoriteDigimonList.add(position, deletedDigimon)
          favoriteAdapter.notifyItemInserted(position)
          favoriteAdapter.notifyItemRangeChanged(position, favoriteDigimonList.size)*/
    }

    override fun onRemoveFavorite(digimon: Digimon) {
        TODO("Not yet implemented")
    }


}