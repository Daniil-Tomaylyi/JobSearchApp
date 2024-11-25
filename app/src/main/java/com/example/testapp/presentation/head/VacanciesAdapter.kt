package com.example.testapp.presentation.head

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.databinding.ListItemVacancyBinding
import com.example.testapp.domain.models.Vacancies


class VacanciesAdapter(
    private val vacancyClickListener: VacanciesListener,
    private val favoriteClickListener: FavoritesListener
) :
    ListAdapter<Vacancies, VacanciesAdapter.ViewHolder>(VacanciesDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: VacanciesAdapter.ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, vacancyClickListener, favoriteClickListener)
    }

    class ViewHolder private constructor(val binding: ListItemVacancyBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: Vacancies,
            vacancyClickListener: VacanciesListener,
            favoriteClickListener: FavoritesListener
        ) {
            binding.vacancy = item
            binding.vacancyListener = vacancyClickListener
            binding.favoriteListener = favoriteClickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemVacancyBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}


class VacanciesDiffCallback : DiffUtil.ItemCallback<Vacancies>() {
    override fun areItemsTheSame(
        oldItem: Vacancies,
        newItem: Vacancies
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: Vacancies,
        newItem: Vacancies
    ): Boolean {
        return oldItem == newItem
    }


}

class VacanciesListener(val clickListener: (id: String) -> Unit) {
    fun onClick(vacancies: Vacancies) = clickListener(vacancies.id)
}

class FavoritesListener(val clickListener: (vacancy: Vacancies) -> Unit) {
    fun onclick(vacancy: Vacancies) {
        clickListener(vacancy)
    }
}
