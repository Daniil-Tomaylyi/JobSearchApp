package com.example.testapp.presentation.favorites

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.testapp.R
import com.example.testapp.databinding.FragmentFavoritesBinding
import com.example.testapp.presentation.TestApplication
import timber.log.Timber
import javax.inject.Inject

class FavoritesFragment : Fragment() {

    @Inject
    lateinit var vmFactory: FavoritesViewModelFactory

    private lateinit var viewModel: FavoritesViewModel

    private lateinit var binding:FragmentFavoritesBinding

    private lateinit var navHostFragment: NavHostFragment

    private lateinit var navController: NavController

    private lateinit var favoritesVacanciesAdapter: FavoritesVacanciesAdapter

    override fun onAttach(context: Context) {
        (requireActivity().application as TestApplication).appComponent.inject(this)
        super.onAttach(context)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorites, container, false)
        navHostFragment = requireActivity().supportFragmentManager.findFragmentById(R.id.NavHostFragment) as NavHostFragment
        navController = navHostFragment.navController
        NavigationUI.setupWithNavController(binding.bottomNav, navController)
        viewModel = ViewModelProvider(this, vmFactory)[FavoritesViewModel::class.java]
        binding.lifecycleOwner = this
        binding.favoritesViewModel = viewModel
        favoritesVacanciesAdapter = FavoritesVacanciesAdapter(VacancyFavoriteListener {
            viewModel.onVacancyClicked(it)
        }, VacancyFavoriteChangeStatListener {
            viewModel.isFavoriteClicked(it)
        })
        binding.favoritesVacanciesList.adapter = favoritesVacanciesAdapter
        viewModel.favoriteVacancies.observe(viewLifecycleOwner){
            it?.let {
                Timber.d("Избранные вакансии:${it}")
                favoritesVacanciesAdapter.submitList(it)
            }
        }
        viewModel.navigateToVacancy.observe(viewLifecycleOwner) {
            it?.let {
                this.findNavController()
                    .navigate(R.id.action_favoritesFragment_to_vacancyFragment)
                viewModel.onVacancyNavigated()
            }
        }
        return binding.root
    }
    override fun onStart() {
        viewModel.getFavoritesVacancies()
        binding.bottomNav.selectedItemId = navController.currentDestination?.id ?: R.id.favoritesFragment
        super.onStart()
    }
}