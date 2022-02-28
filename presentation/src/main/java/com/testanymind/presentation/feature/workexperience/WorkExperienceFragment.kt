package com.testanymind.presentation.feature.workexperience

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.testanymind.presentation.R
import com.testanymind.presentation.base.DataBindingFragment
import com.testanymind.presentation.databinding.FragmentWorkExperienceBinding

class WorkExperienceFragment : DataBindingFragment<FragmentWorkExperienceBinding>() {

    override fun layoutId() = R.layout.fragment_work_experience

    private lateinit var viewModel: WorkExperienceViewModel


    companion object {
        fun newInstance() = WorkExperienceFragment()
    }

    override fun start() {
        TODO("Not yet implemented")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(WorkExperienceViewModel::class.java)
        // TODO: Use the ViewModel
    }

}