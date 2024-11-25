package com.example.testapp.presentation.profile

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.testapp.R
import com.example.testapp.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    private lateinit var navHostFragment: NavHostFragment

    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        navHostFragment = requireActivity().supportFragmentManager.findFragmentById(R.id.NavHostFragment) as NavHostFragment
        navController = navHostFragment.navController
        NavigationUI.setupWithNavController(binding.bottomNav, navController)
        return binding.root
    }

    override fun onStart() {
        binding.bottomNav.selectedItemId = navController.currentDestination?.id ?: R.id.profileFragment
        super.onStart()
    }
}