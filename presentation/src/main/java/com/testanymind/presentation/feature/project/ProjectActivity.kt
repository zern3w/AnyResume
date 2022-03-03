package com.testanymind.presentation.feature.project

import android.content.Context
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.divider.MaterialDividerItemDecoration
import com.testanymind.domain.model.Education
import com.testanymind.domain.model.ProjectDetail
import com.testanymind.presentation.*
import com.testanymind.presentation.base.DataBindingActivity
import com.testanymind.presentation.databinding.ActivityProjectBinding
import com.testanymind.presentation.extension.observe
import com.testanymind.presentation.extension.observeEvent
import com.testanymind.presentation.extension.observeTrigger
import com.testanymind.presentation.feature.education.AddEditEducationActivity
import com.testanymind.presentation.view.adapter.ProjectAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProjectActivity : DataBindingActivity<ActivityProjectBinding>() {

    override fun layoutId() = R.layout.activity_project
    override fun getToolBar() = viewBinding.toolbar

    private val viewModel: ProjectViewModel by viewModel()

    private val projectAdapter by lazy {
        ProjectAdapter(listOf(), true, ::onItemClick)
    }

    private val recyclerViewDivider by lazy {
        MaterialDividerItemDecoration(
            this,
            LinearLayoutManager.VERTICAL
        ).apply {
            setDividerColorResource(this@ProjectActivity, R.color.grey_divider)
            dividerInsetStart = resources.getDimensionPixelOffset(R.dimen.spacing_xxxlarge)
            isLastItemDecorated = false
        }
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, ProjectActivity::class.java)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
//            R.id.menu_save -> viewModel.save()
            R.id.menu_add -> viewModel.showAddEditUi()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun start() {
        initView()
        initObserver()
        viewModel.getProjectList()
    }

    private fun initView() {
        title = getString(R.string.project)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewBinding.apply {
            rvProject.adapter = projectAdapter
            rvProject.addItemDecoration(recyclerViewDivider)
        }
    }

    private fun initObserver() {
        viewModel.apply {
            observe(projectList) {
                projectAdapter.submitData(it)
            }

            observeEvent(showOrHideEmptyState) {
                viewBinding.viewEmptyState.root.isVisible = it
            }

            observeTrigger(finishActivity) {
                finish()
            }

            observeTrigger(showConfirmationDiscard) {
                showConfirmationDiscardDialog()
            }

            observeTrigger(showAddEditUi) {
                startActivity(AddEditProjectActivity.newIntent(this@ProjectActivity))
            }
        }
    }

    private fun onItemClick(data: ProjectDetail) {
        startActivity(AddEditProjectActivity.newIntent(this@ProjectActivity, data._id))
    }

    private fun showConfirmationDiscardDialog() {
        MaterialAlertDialogBuilder(this)
            .setTitle(getString(R.string.confirmation))
            .setMessage(getString(R.string.dialog_discard_message))
            .setNegativeButton(getString(R.string.cancel)) { _, _ ->
            }
            .setPositiveButton(getString(R.string.discard)) { _, _ ->
                viewModel.finishActivity()
            }
            .show()
    }
}