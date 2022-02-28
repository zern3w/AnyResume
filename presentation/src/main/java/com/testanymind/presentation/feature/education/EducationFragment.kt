package com.testanymind.presentation.feature.education

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.testanymind.presentation.R
import com.testanymind.presentation.base.DataBindingFragment
import com.testanymind.presentation.databinding.FragmentEducationBinding

class EducationFragment : DataBindingFragment<FragmentEducationBinding>() {

    override fun layoutId() = R.layout.fragment_education

    private lateinit var viewModel: EducationViewModel

    companion object {
        fun newInstance() = EducationFragment()
    }

    override fun start() {
        TODO("Not yet implemented")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EducationViewModel::class.java)
        // TODO: Use the ViewModel
    }

}