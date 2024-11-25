package com.example.testapp.presentation.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.databinding.ListItemFavoriteVacancyBinding
import com.example.testapp.domain.models.FavoriteVacancies


class FavoritesVacanciesAdapter(
    private val vacancyFavoriteClickListener: VacancyFavoriteListener,
    private val favoriteChangeStatClickListener: VacancyFavoriteChangeStatListener
) :
    ListAdapter<FavoriteVacancies, FavoritesVacanciesAdapter.ViewHolder>(
        FavoriteVacanciesDiffCallback()
    ) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoritesVacanciesAdapter.ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: FavoritesVacanciesAdapter.ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, vacancyFavoriteClickListener, favoriteChangeStatClickListener)
    }

    class ViewHolder private constructor(val binding: ListItemFavoriteVacancyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            item: FavoriteVacancies,
            vacancyFavoriteClickListener: VacancyFavoriteListener,
            vacancyFavoriteChangeStatClickListener: VacancyFavoriteChangeStatListener
        ) {
            binding.favoriteVacancy = item
            binding.vacancyFavoriteListener = vacancyFavoriteClickListener
            binding.favoriteChangeStatListener = vacancyFavoriteChangeStatClickListener
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemFavoriteVacancyBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class FavoriteVacanciesDiffCallback : DiffUtil.ItemCallback<FavoriteVacancies>() {
    override fun areItemsTheSame(oldItem: FavoriteVacancies, newItem: FavoriteVacancies): Boolean {
        return oldItem.vacancyId == newItem.vacancyId
    }

    override fun areContentsTheSame(
        oldItem: FavoriteVacancies,
        newItem: FavoriteVacancies
    ): Boolean {
        return oldItem == newItem
    }
}

class VacancyFavoriteListener(val clickListener: (id: String) -> Unit) {
    fun onClick(vacancies: FavoriteVacancies) = clickListener(vacancies.vacancyId)
}

class VacancyFavoriteChangeStatListener(val clickListener: (vacancy: FavoriteVacancies) -> Unit) {
    fun onclick(vacancy: FavoriteVacancies) {
        clickListener(vacancy)
    }
}
