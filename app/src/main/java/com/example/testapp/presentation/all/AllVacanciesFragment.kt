package com.example.testapp.presentation.all


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.testapp.R
import com.example.testapp.databinding.FragmentAllVacanciesBinding
import com.example.testapp.presentation.TestApplication
import com.example.testapp.presentation.head.FavoritesListener
import com.example.testapp.presentation.head.VacanciesAdapter
import com.example.testapp.presentation.head.VacanciesListener
import timber.log.Timber
import javax.inject.Inject

class AllVacanciesFragment : Fragment() {

    @Inject
    lateinit var vmFactory: AllVacanciesViewModelFactory

    private lateinit var viewModel: AllVacanciesViewModel

    private lateinit var binding: FragmentAllVacanciesBinding

    private lateinit var vacanciesAdapter: VacanciesAdapter

    private lateinit var navController: NavController

    private lateinit var navHostFragment: NavHostFragment

    override fun onAttach(context: Context) {
        (requireActivity().application as TestApplication).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_all_vacancies, container, false)

        navHostFragment =
            requireActivity().supportFragmentManager.findFragmentById(R.id.NavHostFragment) as NavHostFragment
        navController = navHostFragment.navController
        NavigationUI.setupWithNavController(binding.bottomNav, navController)
        viewModel = ViewModelProvider(this, vmFactory)[AllVacanciesViewModel::class.java]
        binding.lifecycleOwner = this
        binding.allVacanciesViewModel = viewModel
        vacanciesAdapter = VacanciesAdapter(VacanciesListener {
            viewModel.onVacancyClicked(it)
        }, FavoritesListener {
            viewModel.isFavoriteClicked(it)
        })
        binding.vacanciesList.adapter = vacanciesAdapter
        viewModel.vacancies.observe(viewLifecycleOwner) {
            it?.let {
                vacanciesAdapter.submitList(it)
            }
        }
        viewModel.favoriteVacancy.observe(viewLifecycleOwner) {
            it?.let {
                if (it.isFavorite) {
                    viewModel.setFavoriteVacancy(it)
                }
                else {
                    viewModel.removeVacancyFavorite(it)
                }
            }
        }
        viewModel.navigateToVacancy.observe(viewLifecycleOwner) {
            it?.let {
                this.findNavController()
                    .navigate(R.id.action_allVacanciesFragment_to_vacancyFragment)
                viewModel.onVacancyNavigated()
            }
        }
        return binding.root
    }
    override fun onStart() {
        binding.bottomNav.selectedItemId = navController.currentDestination?.id ?: R.id.allVacanciesFragment
        super.onStart()
    }

}