package com.testanymind.presentation.feature.personalinfo

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.testanymind.presentation.R
import com.testanymind.presentation.base.DataBindingFragment
import com.testanymind.presentation.databinding.FragmentEducationBinding
import com.testanymind.presentation.databinding.FragmentPersonalInfoBinding

class PersonalInfoFragment : DataBindingFragment<FragmentPersonalInfoBinding>() {

    override fun layoutId() = R.layout.fragment_personal_info

    private lateinit var viewModel: PersonalInfoViewModel

    companion object {
        fun newInstance() = PersonalInfoFragment()
    }

    override fun start() {
        TODO("Not yet implemented")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PersonalInfoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}