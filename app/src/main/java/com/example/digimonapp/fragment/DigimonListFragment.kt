package com.example.digimonapp.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.digimonapp.R
import com.example.digimonapp.data.adapter.DigimonAdapter
import com.example.digimonapp.data.viewmodel.DigimonViewModel
import com.example.digimonapp.models.Digimon
import com.example.digimonapp.models.DigimonTest
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DigimonListFragment : Fragment() {
    private val mainViewModel: DigimonViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_digimon_list, container, false)
        //setUpObservers()
        mainViewModel.getCountry().observe(this, { countryList ->
            countryList?.let {
                setupRecyclerView(view,countryList)
            }
        })

        return view
    }

    private fun setupRecyclerView(view: View?,digimonList:ArrayList<Digimon>) {
        val context = requireContext()
        val cityAdapter = DigimonAdapter(context, digimonList)
        val recyclerView = view?.findViewById<RecyclerView>(R.id.city_recycler_view)
        recyclerView?.adapter = cityAdapter
        recyclerView?.setHasFixedSize(true)

        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = RecyclerView.VERTICAL
        recyclerView?.layoutManager = layoutManager
    }

    private fun setUpObservers() {
        mainViewModel.getCountry().observe(this, { countryList ->
            countryList?.let {

             /*   binding.countryRecyclerview.apply {
                    with(adapter as CountryAdapter) {
                        countries = it
                        notifyDataSetChanged()
                    }
                }*/
            }
        })
    }
}