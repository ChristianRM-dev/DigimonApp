package com.example.digimonapp.ui.view.fragment

import android.os.Bundle
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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DigimonListFragment : Fragment() {
    private val digimonViewModel: DigimonViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_digimon_list, container, false)

        digimonViewModel.onCreate();

        digimonViewModel.digimonModel.observe(viewLifecycleOwner, Observer {
          /*  binding.tvQuote.text = it.
            binding.tvAuthor.text = it.author*/
        })
        return view
    }

    private fun setupRecyclerView(view: View?,digimonList:ArrayList<Digimon>) {
        val context = requireContext()
        val digimonAdapter = DigimonAdapter(context)
        val recyclerView = view?.findViewById<RecyclerView>(R.id.digimon_recycler_view)
        recyclerView?.adapter = digimonAdapter
        recyclerView?.setHasFixedSize(true)

        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = RecyclerView.VERTICAL
        recyclerView?.layoutManager = layoutManager
    }


}