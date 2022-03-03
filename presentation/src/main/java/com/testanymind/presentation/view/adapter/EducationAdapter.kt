package com.testanymind.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.testanymind.domain.model.Education
import com.testanymind.presentation.databinding.ItemEducationBinding

class EducationAdapter(
    private var dataList: List<Education>,
    private var onItemClick: (Education) -> Unit = {}
) :
    RecyclerView.Adapter<EducationAdapter.EducationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EducationViewHolder {
        val binding =
            ItemEducationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EducationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EducationViewHolder, position: Int) {
        val data = dataList[position]
        holder.bind(data)
        holder.itemView.setOnClickListener { onItemClick.invoke(data) }
    }

    override fun getItemCount() = dataList.size

    fun submitData(list: List<Education>) {
        this.dataList = list
        notifyDataSetChanged()
    }

    class EducationViewHolder(private val binding: ItemEducationBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Education) {
            with(binding) {
                this.data = data
                executePendingBindings()
                tvGpa.isVisible = data.gpa != -1.0
                tvSeparator.isVisible = data.gpa != -1.0
            }
        }
    }
}