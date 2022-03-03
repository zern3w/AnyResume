package com.testanymind.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.testanymind.domain.model.Education
import com.testanymind.domain.model.WorkingExperience
import com.testanymind.presentation.databinding.ItemWorkingExperienceBinding

class WorkingExperienceAdapter(private var dataList: List<WorkingExperience>,
                               private var onItemClick: (WorkingExperience) -> Unit = {}
) :
    RecyclerView.Adapter<WorkingExperienceAdapter.WorkingExperienceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkingExperienceViewHolder {
        val binding = ItemWorkingExperienceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WorkingExperienceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WorkingExperienceViewHolder, position: Int) {
        val data = dataList[position]
        holder.bind(data)
        holder.itemView.setOnClickListener { onItemClick.invoke(data) }
    }

    override fun getItemCount() = dataList.size

    fun submitData(list: List<WorkingExperience>) {
        this.dataList = list
        notifyDataSetChanged()
    }

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