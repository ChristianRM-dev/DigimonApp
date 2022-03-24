package com.example.digimonapp.ui.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.digimonapp.R
import com.example.digimonapp.ui.adapter.DigimonAdapter
import com.example.digimonapp.ui.viewmodel.DigimonViewModel
import com.example.digimonapp.domain.models.Digimon
import com.example.digimonapp.ui.adapter.FavoriteAdapter
import com.example.digimonapp.ui.listeners.DigimonListListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DigimonListFragment : Fragment(), DigimonListListener {
    private val digimonViewModel: DigimonViewModel by viewModels()
    private lateinit var digimonAdapter: DigimonAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_digimon_list, container, false)
        digimonViewModel.loadDigimons();
        digimonViewModel.digimons.observe(viewLifecycleOwner, Observer {
            setupRecyclerView(view, it)
        })
        return view
    }

    private fun setupRecyclerView(view: View?, digimonList: List<Digimon>) {
        val context = requireContext()
        digimonAdapter = DigimonAdapter(context, digimonList,this)
        val recyclerView = view?.findViewById<RecyclerView>(R.id.digimon_recycler_view)
        recyclerView?.adapter = digimonAdapter
        recyclerView?.setHasFixedSize(true)

        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = RecyclerView.VERTICAL
        recyclerView?.layoutManager = layoutManager
    }

    override fun onFavoriteClick(digimon: Digimon) {
        digimonViewModel.updateDigimon(digimon).run {
        }
    }

    override fun onPause() {
        super.onPause()
        Log.i("onPause", "wew")
    }

    override fun onResume() {
        super.onResume()
        Log.i("onResume", "wew")
    }
}