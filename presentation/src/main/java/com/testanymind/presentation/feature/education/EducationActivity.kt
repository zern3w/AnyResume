package com.testanymind.presentation.feature.education

import android.content.Context
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.divider.MaterialDividerItemDecoration
import com.testanymind.domain.model.Education
import com.testanymind.presentation.R
import com.testanymind.presentation.base.DataBindingActivity
import com.testanymind.presentation.databinding.ActivityEducationBinding
import com.testanymind.presentation.extension.observe
import com.testanymind.presentation.extension.observeEvent
import com.testanymind.presentation.extension.observeTrigger
import com.testanymind.presentation.view.adapter.EducationAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class EducationActivity : DataBindingActivity<ActivityEducationBinding>() {

    override fun layoutId() = R.layout.activity_education
    override fun getToolBar() = viewBinding.toolbar

    private val viewModel: EducationViewModel by viewModel()

    private val educationAdapter by lazy {
        EducationAdapter(listOf(), ::onItemClick)
    }

    private val recyclerViewDivider by lazy {
        MaterialDividerItemDecoration(
            this,
            LinearLayoutManager.VERTICAL
        ).apply {
            setDividerColorResource(this@EducationActivity, R.color.grey_divider)
            dividerInsetStart = resources.getDimensionPixelOffset(R.dimen.spacing_xxxlarge)
            isLastItemDecorated = false
        }
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, EducationActivity::class.java)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_add -> viewModel.showAddEditUi()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun start() {
        initView()
        initObserver()
        viewModel.getEducationList()
    }

    private fun initView() {
        title = getString(R.string.education)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewBinding.apply {
            rvEducation.adapter = educationAdapter
            rvEducation.addItemDecoration(recyclerViewDivider)
        }
    }

    private fun initObserver() {
        viewModel.apply {
            observe(educationList) {
                educationAdapter.submitData(it)
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
                startActivity(AddEditEducationActivity.newIntent(this@EducationActivity))
            }
        }
    }

    private fun onItemClick(data: Education) {
        startActivity(AddEditEducationActivity.newIntent(this@EducationActivity, data._id))
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