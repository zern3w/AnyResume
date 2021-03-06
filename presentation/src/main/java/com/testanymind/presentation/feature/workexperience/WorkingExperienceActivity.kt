package com.testanymind.presentation.feature.workexperience

import android.content.Context
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.divider.MaterialDividerItemDecoration
import com.testanymind.domain.model.WorkingExperience
import com.testanymind.presentation.R
import com.testanymind.presentation.base.DataBindingActivity
import com.testanymind.presentation.databinding.ActivityWorkExperienceBinding
import com.testanymind.presentation.extension.observe
import com.testanymind.presentation.extension.observeEvent
import com.testanymind.presentation.extension.observeTrigger
import com.testanymind.presentation.view.adapter.WorkingExperienceAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class WorkingExperienceActivity : DataBindingActivity<ActivityWorkExperienceBinding>() {

    override fun layoutId() = R.layout.activity_work_experience
    override fun getToolBar() = viewBinding.toolbar

    private val viewModel: WorkExperienceViewModel by viewModel()

    private val workingExperienceAdapter by lazy {
        WorkingExperienceAdapter(listOf(), ::onItemClick)
    }

    private val recyclerViewDivider by lazy {
        MaterialDividerItemDecoration(
            this,
            LinearLayoutManager.VERTICAL
        ).apply {
            setDividerColorResource(this@WorkingExperienceActivity, R.color.grey_divider)
            dividerInsetStart = resources.getDimensionPixelOffset(R.dimen.spacing_xxxlarge)
            isLastItemDecorated = false
        }
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, WorkingExperienceActivity::class.java)
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
        viewModel.getWorkingExpList()
    }

    private fun initView() {
        title = getString(R.string.experiences)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewBinding.apply {
            rvExperience.adapter = workingExperienceAdapter
            rvExperience.addItemDecoration(recyclerViewDivider)
        }
    }

    private fun initObserver() {
        viewModel.apply {
            observe(workingExpList) {
                workingExperienceAdapter.submitData(it)
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
                startActivity(AddEditWorkingExperienceActivity.newIntent(this@WorkingExperienceActivity))
            }
        }
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

    private fun onItemClick(data: WorkingExperience) {
        startActivity(AddEditWorkingExperienceActivity.newIntent(this@WorkingExperienceActivity, data._id))
    }
}