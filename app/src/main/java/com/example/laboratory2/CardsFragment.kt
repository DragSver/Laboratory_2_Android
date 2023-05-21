package com.example.laboratory2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.laboratory2.databinding.CardsFragmentBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class CardsFragment : Fragment() {
    val cardsLiveData = MutableLiveData<List<Card>>()

    var navController: NavController?=null

    lateinit var response: Response<List<CardInfo>>
//    lateinit var cards: List<Card>
    lateinit var binding: CardsFragmentBinding
    lateinit var adapter: ListAdapter<Card, RecyclerView.ViewHolder>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        adapter = Adapter()
        binding = CardsFragmentBinding.inflate(layoutInflater)
        binding.rcView.layoutManager = LinearLayoutManager(context)
        binding.rcView.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        cardsLiveData.observe(viewLifecycleOwner, Observer { cards -> adapter.submitList(cards) })
        lifecycleScope.launch(Dispatchers.Main) {
            response = ApiService.instance().getCardsInfo()
            updateCards()
//            println(cards)
//            adapter.submitList(cards)
        }
    }

    private fun updateCards() {
        val cardsInfo = response.body()!!
        cardsLiveData.value = cardsInfo.map { cardInfo -> Card.CardFactory.create(cardInfo) }
    }
}