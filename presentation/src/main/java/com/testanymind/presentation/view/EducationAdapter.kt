package com.testanymind.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.testanymind.presentation.databinding.ItemEducationBinding

class EducationAdapter(private val dataList: List<Education>) :
    RecyclerView.Adapter<EducationAdapter.EducationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EducationViewHolder {
        val binding = ItemEducationBinding.inflate(LayoutInflater.from(parent.context))
        return EducationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EducationViewHolder, position: Int) {
        val data = dataList[position]
        holder.bind(data)
    }

    override fun getItemCount() = dataList.size

    class EducationViewHolder(private val binding: ItemEducationBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Education) {
            with(binding) {
                this.data = data
                executePendingBindings()
            }
        }
    }
}

data class Education(
    val schoolName: String,
    val _class: String,
    val passingYear: String,
    val gpa: Double
)

data class WorkingExperience(
    val companyName: String,
    val role: String,
    val startDate: String,
    val endDate: String
) {
    fun getDuration(): String {
        return "Mar 2017 - Dec 2021"
    }
}

data class ProjectDetail(
    val projectName: String,
    val teamSize: Int,
    val projectSummary: String,
    val technologyUsed: List<String>,
    val role: String
)