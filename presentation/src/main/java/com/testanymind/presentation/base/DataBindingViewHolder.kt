package com.testanymind.presentation.base

import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class DataBindingViewHolder<VH : ViewBinding>(
    val viewBinding: VH,
    val lifecycleOwner: LifecycleOwner
) : RecyclerView.ViewHolder(viewBinding.root)