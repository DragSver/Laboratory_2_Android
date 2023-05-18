package com.example.laboratory2

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.laboratory2.databinding.BigImageItemBinding
import com.example.laboratory2.databinding.BigTextItemBinding
import com.example.laboratory2.databinding.MiddleItemBinding
import com.example.laboratory2.databinding.SmallItemBinding
import com.squareup.picasso.Picasso


class Adapter : ListAdapter<Card, RecyclerView.ViewHolder>(ProductComparator()) {

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is Card.BigTextCard -> 0
            is Card.BigImageCard -> 1
            is Card.MiddleCard -> 2
            else -> 3
        }
    }

    class BigImageHolder(val binding: BigImageItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(card: Card.BigImageCard) = with(binding){
            header.text = card.header
            text.text = card.text

            Picasso.with(image.context)
                .load(card.image)
                .into(image);
        }
        companion object{
            fun create(parent: ViewGroup): BigImageHolder{
                return BigImageHolder(BigImageItemBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false))
            }
        }
    }

    class BigTextHolder(val binding: BigTextItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(card: Card.BigTextCard) = with(binding){
            header.text = card.header
            text.text = card.text
            bag.setBackgroundColor(Color.parseColor(card.colorBag))

            Picasso.with(image.context)
                .load(card.image)
                .into(image);
        }
        companion object{
            fun create(parent: ViewGroup): BigTextHolder{
                return BigTextHolder(BigTextItemBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false))
            }
        }
    }

    class SmallHolder(val binding: SmallItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(card: Card.SmallCard) = with(binding){
            header.text = card.header
            subhead.text = card.subhead
        }
        companion object{
            fun create(parent: ViewGroup): SmallHolder{
                return SmallHolder(SmallItemBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false))
            }
        }
    }

    class MiddleHolder(val binding: MiddleItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(card: Card.MiddleCard) = with(binding){
            header.text = card.header
            subhead.text = card.subhead

            Picasso.with(image.context)
                .load(card.image)
                .into(image);
        }
        companion object{
            fun create(parent: ViewGroup): MiddleHolder{
                return MiddleHolder(MiddleItemBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false))
            }
        }
    }

    class ProductComparator : DiffUtil.ItemCallback<Card>(){
        override fun areContentsTheSame(oldCard: Card, newCard: Card): Boolean {
            return oldCard == newCard
        }

        override fun areItemsTheSame(oldCard: Card, newCard: Card): Boolean {
            return oldCard == newCard
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            0 -> {
                BigTextHolder(BigTextItemBinding.inflate(inflater, parent, false))
            }
            1 -> {
                BigImageHolder(BigImageItemBinding.inflate(inflater, parent, false))
            }
            2 -> {
                MiddleHolder(MiddleItemBinding.inflate(inflater, parent, false))
            }
            else -> {
                SmallHolder(SmallItemBinding.inflate(inflater, parent, false))
            }
        }
        return SmallHolder(SmallItemBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = getItem(position)) {
            is Card.BigTextCard -> {
                holder as BigTextHolder
                holder.bind(item)
            }
            is Card.BigImageCard -> {
                holder as BigImageHolder
                holder.bind(item)
            }
            is Card.MiddleCard -> {
                holder as MiddleHolder
                holder.bind(item)
            }
            else -> {
                holder as SmallHolder
                holder.bind(item as Card.SmallCard)
            }
        }
    }
}