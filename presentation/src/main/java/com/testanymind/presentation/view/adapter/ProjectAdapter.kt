package com.testanymind.presentation.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.testanymind.domain.model.Education
import com.testanymind.domain.model.ProjectDetail
import com.testanymind.presentation.databinding.ItemProjectBinding
import com.testanymind.presentation.extension.getCollapseAnimation
import com.testanymind.presentation.extension.getExpandAnimation

private const val ARROW_ROTATION_DURATION = 200L

class ProjectAdapter(
    private var dataList: List<ProjectDetail>,
    private val isEditMode: Boolean = false,
    private var onItemClick: (ProjectDetail) -> Unit = {}
) :
    RecyclerView.Adapter<ProjectAdapter.ProjectDetailViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectDetailViewHolder {
        val binding = ItemProjectBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProjectDetailViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProjectDetailViewHolder, position: Int) {
        val data = dataList[position]
        holder.bind(data, isEditMode)
        holder.itemView.setOnClickListener {
            if (isEditMode) {
                onItemClick.invoke(data)
            } else {
                holder.expandOrCollapseContent()
            }
        }
    }

    override fun getItemCount() = dataList.size

    fun submitData(list: List<ProjectDetail>) {
        this.dataList = list
        notifyDataSetChanged()
    }

    class ProjectDetailViewHolder(private val binding: ItemProjectBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private var isContentExpanded = false

        fun bind(data: ProjectDetail, isEditMode: Boolean) {
            with(binding) {
                this.data = data
                flExpand.setOnClickListener { expandOrCollapseContent() }
                executePendingBindings()

                flExpand.isVisible = !isEditMode
                binding.llContent.isVisible = isEditMode
            }
        }

        fun expandOrCollapseContent() {
            if (isContentExpanded) {
                closeArrow(binding.ivExpand)

                val collapseAnimation = binding.llContent.getCollapseAnimation()
                binding.llContent.startAnimation(collapseAnimation)
            } else {
                openArrow(binding.ivExpand)

                val expandAnimation = binding.llContent.getExpandAnimation()
                binding.llContent.startAnimation(expandAnimation)
            }

            isContentExpanded = !isContentExpanded
        }

        private fun openArrow(v: View) {
            v.animate().setDuration(ARROW_ROTATION_DURATION).rotation(180F)
        }

        private fun closeArrow(v: View) {
            v.animate().setDuration(ARROW_ROTATION_DURATION).rotation(0F)
        }
    }
}