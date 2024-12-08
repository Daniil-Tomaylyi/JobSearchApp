package com.example.testapp.presentation.head


import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.testapp.R
import com.example.testapp.presentation.TestApplication
import com.example.testapp.databinding.FragmentHeadBinding
import timber.log.Timber
import javax.inject.Inject


class HeadFragment : Fragment() {
    @Inject
    lateinit var vmFactory: HeadViewModelFactory

    private lateinit var viewModel: HeadViewModel

    private lateinit var binding: FragmentHeadBinding

    private lateinit var navHostFragment: NavHostFragment

    private lateinit var navController: NavController

    private lateinit var vacanciesAdapter: VacanciesAdapter

    private lateinit var offersAdapter: OffersAdapter

    private lateinit var intent: Intent

    override fun onAttach(context: Context) {
        (requireActivity().application as TestApplication).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_head, container, false)
        navHostFragment =
            requireActivity().supportFragmentManager.findFragmentById(R.id.NavHostFragment) as NavHostFragment
        navController = navHostFragment.navController
        NavigationUI.setupWithNavController(binding.bottomNav, navController)
        viewModel = ViewModelProvider(this, vmFactory)[HeadViewModel::class.java]
        binding.lifecycleOwner = this
        binding.vacanciesViewModel = viewModel
        viewModel.statusOffers.observe(viewLifecycleOwner) { status ->
            Timber.d("apiStatusOffers в Fragment: $status")
            // Логика обновления UI
        }
        vacanciesAdapter = VacanciesAdapter(VacanciesListener {
            viewModel.onVacancyClicked(it)
        }, FavoritesListener {
            viewModel.isFavoriteClicked(it)
        })
        offersAdapter = OffersAdapter(OffersListener {
            viewModel.onOffersClicked(it)
        })
        binding.vacanciesList.adapter = vacanciesAdapter
        binding.offersList.adapter = offersAdapter
        viewModel.vacancies.observe(viewLifecycleOwner) {
            it?.let {
                vacanciesAdapter.submitList(it.take(3))
            }
        }
        viewModel.offers.observe(viewLifecycleOwner) {
            it?.let {
                offersAdapter.submitList(it)
            }
        }

        viewModel.navigateToLink.observe(viewLifecycleOwner) {
            it?.let {
                intent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
                startActivity(intent)
                viewModel.onLinkNavigated()
            }
        }

        viewModel.navigateToVacancy.observe(viewLifecycleOwner) {
            it?.let {
                this.findNavController().navigate(  R.id.action_headFragment_to_vacancyFragment)
                viewModel.onVacancyNavigated()
            }
        }
        binding.buttonMore.setOnClickListener {
            it.findNavController()
                .navigate(R.id.action_headFragment_to_allVacanciesFragment)
        }
        return binding.root
    }

    override fun onStart() {
        viewModel.refreshOffers()
        viewModel.refreshVacancies()
        binding.bottomNav.selectedItemId = navController.currentDestination?.id ?: R.id.headFragment
        super.onStart()
    }

}