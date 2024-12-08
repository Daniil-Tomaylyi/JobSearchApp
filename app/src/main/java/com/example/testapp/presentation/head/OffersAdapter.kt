    package com.example.testapp.presentation.head

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.databinding.ListItemOfferBinding
import com.example.testapp.domain.models.Offers

class OffersAdapter(private val clickListener: OffersListener) :
    ListAdapter<Offers, OffersAdapter.ViewHolder>(OffersDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OffersAdapter.ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: OffersAdapter.ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    class ViewHolder private constructor(val binding: ListItemOfferBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Offers, clickListener: OffersListener) {
            binding.offers = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemOfferBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class OffersDiffCallback : DiffUtil.ItemCallback<Offers>() {
    override fun areItemsTheSame(oldItem: Offers, newItem: Offers): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Offers, newItem: Offers): Boolean {
        return oldItem == newItem
    }
}

class OffersListener(val clickListener: (link: String) -> Unit) {
    fun onClick(offers: Offers) = clickListener(offers.link)
}
