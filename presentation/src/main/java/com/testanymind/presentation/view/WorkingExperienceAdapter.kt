package com.testanymind.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.testanymind.presentation.databinding.ItemWorkingExperienceBinding

class WorkingExperienceAdapter(private val dataList: List<WorkingExperience>) :
    RecyclerView.Adapter<WorkingExperienceAdapter.WorkingExperienceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkingExperienceViewHolder {
        val binding = ItemWorkingExperienceBinding.inflate(LayoutInflater.from(parent.context))
        return WorkingExperienceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WorkingExperienceViewHolder, position: Int) {
        val data = dataList[position]
        holder.bind(data)
    }

    override fun getItemCount() = dataList.size

    class WorkingExperienceViewHolder(private val binding: ItemWorkingExperienceBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: WorkingExperience) {
            with(binding) {
                this.data = data
                executePendingBindings()
            }
        }
    }
}