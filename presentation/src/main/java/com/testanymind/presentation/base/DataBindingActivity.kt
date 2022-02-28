package com.testanymind.presentation.base

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 *  com.testanymind.presentation.base.BaseActivity for keeping an instance of [ViewDataBinding]
 */
abstract class DataBindingActivity<VB : ViewDataBinding> : BaseActivity() {

    lateinit var viewBinding: VB

    override fun setContentView(layoutResID: Int) {
        viewBinding = DataBindingUtil.inflate(layoutInflater, layoutResID, null, false)
        super.setContentView(viewBinding.root)
    }
}