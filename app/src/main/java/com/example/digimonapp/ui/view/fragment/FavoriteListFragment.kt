package com.example.digimonapp.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.digimonapp.R
import com.example.digimonapp.ui.adapter.FavoriteAdapter
import com.example.digimonapp.domain.models.Digimon
import com.example.digimonapp.domain.models.DigimonTest
import com.google.android.material.snackbar.Snackbar
import java.util.*
import kotlin.collections.ArrayList


class FavoriteListFragment : Fragment() {
    private lateinit var favoriteDigimonList   : ArrayList<Digimon>
    private lateinit var favoriteAdapter    : FavoriteAdapter
    private lateinit var recyclerView       : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favorite_list, container, false)
        setupRecyclerView(view)
        return view
    }

    private fun setupRecyclerView(view: View) {
        val context = requireContext()

        favoriteDigimonList = DigimonTest.favoriteDigimonList as ArrayList<Digimon>
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
            val deletedDigimon: Digimon = favoriteDigimonList[position]

            deleteItem(position)
            updateDigimonList(deletedDigimon, false)

            Snackbar.make(recyclerView, "Deleted", Snackbar.LENGTH_LONG)
                .setAction("UNDO") {
                    undoDelete(position, deletedDigimon)
                    updateDigimonList(deletedDigimon, true)
                }
                .show()
        }
    })

    private fun deleteItem(position: Int) {
        favoriteDigimonList.removeAt(position)
        favoriteAdapter.notifyItemRemoved(position)
        favoriteAdapter.notifyItemRangeChanged(position, favoriteDigimonList.size)
    }

    private fun updateDigimonList(deletedDigimon: Digimon, isFavorite: Boolean) {
        /*val digimonList = DigimonTest.digimonList!!
        val position = digimonList.indexOf(deletedDigimon)
        digimonList[position].isFavorite = isFavorite*/
    }

    private fun undoDelete(position: Int, deletedDigimon: Digimon) {

        favoriteDigimonList.add(position, deletedDigimon)
        favoriteAdapter.notifyItemInserted(position)
        favoriteAdapter.notifyItemRangeChanged(position, favoriteDigimonList.size)
    }


}