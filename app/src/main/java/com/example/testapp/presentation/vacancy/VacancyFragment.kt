package com.example.testapp.presentation.vacancy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.testapp.R
import com.example.testapp.databinding.FragmentVacancyBinding

class VacancyFragment : Fragment() {

    private lateinit var binding: FragmentVacancyBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_vacancy, container, false)
        return binding.root
    }
}