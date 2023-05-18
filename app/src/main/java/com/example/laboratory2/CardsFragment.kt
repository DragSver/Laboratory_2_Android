package com.example.laboratory2

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.laboratory2.databinding.CardsFragmentBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class CardsFragment : Fragment() {
    var navController: NavController?=null

    lateinit var binding: CardsFragmentBinding
    lateinit var adapter: ListAdapter<Card, RecyclerView.ViewHolder>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        adapter = Adapter()
        binding = CardsFragmentBinding.inflate(layoutInflater)
        binding.rcView.layoutManager = LinearLayoutManager(context)
        binding.rcView.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        lifecycleScope.launch(Dispatchers.Main) {
            val response = ApiService.instance().getCardsInfo()
            val cardsInfo = response.body()!!
            val cards = cardsInfo.map { cardInfo -> Card.CardFactory.create(cardInfo) }
            println(cards)
            adapter.submitList(cards)
        }
    }
}