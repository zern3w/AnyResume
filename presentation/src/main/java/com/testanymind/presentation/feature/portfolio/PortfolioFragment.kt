package com.testanymind.presentation.feature.portfolio

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.testanymind.presentation.R
import com.testanymind.presentation.base.DataBindingFragment
import com.testanymind.presentation.databinding.FragmentPortfolioBinding

class PortfolioFragment : DataBindingFragment<FragmentPortfolioBinding>() {

    override fun layoutId() = R.layout.fragment_portfolio

    private lateinit var viewModel: PortfolioViewModel

    companion object {
        fun newInstance() = PortfolioFragment()
    }

    override fun start() {
        TODO("Not yet implemented")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PortfolioViewModel::class.java)
        // TODO: Use the ViewModel
    }

}