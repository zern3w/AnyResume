package com.testanymind.presentation.feature.workexperience

import android.content.Context
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.testanymind.domain.model.Education
import com.testanymind.domain.model.WorkingExperience
import com.testanymind.presentation.R
import com.testanymind.presentation.base.DataBindingActivity
import com.testanymind.presentation.databinding.ActivityAddEditWorkingExperienceBinding
import com.testanymind.presentation.extension.observe
import com.testanymind.presentation.extension.observeTrigger
import com.testanymind.presentation.feature.education.AddEditEducationActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddEditWorkingExperienceActivity : DataBindingActivity<ActivityAddEditWorkingExperienceBinding>() {

    override fun layoutId() = R.layout.activity_add_edit_working_experience
    override fun getToolBar() = viewBinding.toolbar

    private val viewModel: AddEditWorkExperienceViewModel by viewModel()

    private val workingExpId by lazy {
        intent.getIntExtra(
            EXTRA_ITEM_ID,
            CREATE_MODE
        )
    }

    companion object {
        private const val CREATE_MODE = -1
        private const val EXTRA_ITEM_ID = "AddEditWorkingExperienceActivity.EXTRA_ITEM_ID"

        fun newIntent(context: Context, itemId: Int = -1): Intent {
            return Intent(context, AddEditWorkingExperienceActivity::class.java).apply {
                putExtra(EXTRA_ITEM_ID, itemId)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(
            if (workingExpId == CREATE_MODE) R.menu.menu_save else R.menu.menu_save_delete,
            menu
        )
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_save -> {
                showErrorRequired(!areRequireWasFilled())
                if (areRequireWasFilled()) {
                    if (workingExpId == CREATE_MODE) {
                        viewModel.save(
                            WorkingExperience(
                                companyName = viewBinding.etCompanyName.text.toString(),
                                role = viewBinding.etRole.text.toString(),
                                startDate = viewBinding.etStartDate.text.toString(),
                                endDate = viewBinding.etEndDate.text.toString(),
                                logo = ""
                            )
                        )
                    } else {
                        viewModel.update(
                            WorkingExperience(
                                _id = workingExpId,
                                companyName = viewBinding.etCompanyName.text.toString(),
                                role = viewBinding.etRole.text.toString(),
                                startDate = viewBinding.etStartDate.text.toString(),
                                endDate = viewBinding.etEndDate.text.toString(),
                                logo = ""
                            )
                        )
                    }
                }
            }
            R.id.menu_delete -> {
                viewModel.delete(workingExpId)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun start() {
        initView()
        initObserver()

        if (workingExpId != CREATE_MODE) viewModel.getWorkExp(workingExpId)
    }

    private fun initView() {
        title = "${getString(if (workingExpId == CREATE_MODE) R.string.add else R.string.edit)} ${
            getString(R.string.experiences)
        }"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initObserver() {
        viewModel.apply {
            observe(workExp) {
                viewBinding.apply {
                    etCompanyName.setText(it.companyName)
                    etRole.setText(it.role)
                    etStartDate.setText(it.startDate)
                    etEndDate.setText(it.endDate)
                }
            }

            observeTrigger(finishActivity) {
                finish()
            }

            observeTrigger(showConfirmationDiscard) {
                showConfirmationDiscardDialog()
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

    private fun areRequireWasFilled(): Boolean {
        viewBinding.apply {
            return !etCompanyName.text.isNullOrEmpty() && !etRole.text.isNullOrEmpty() && !etStartDate.text.isNullOrEmpty() && !etEndDate.text.isNullOrEmpty()
        }
    }

    private fun showErrorRequired(isVisible: Boolean) {
        viewBinding.apply {
            textLayoutCompanyName.error =
                if (isVisible && etCompanyName.text.isNullOrEmpty()) getString(R.string.error_required) else null
            textLayoutRole.error =
                if (isVisible && etRole.text.isNullOrEmpty()) getString(R.string.error_required) else null
            textLayoutStartDate.error =
                if (isVisible && etStartDate.text.isNullOrEmpty()) getString(R.string.error_required) else null
            textLayoutEndDate.error =
                if (isVisible && etEndDate.text.isNullOrEmpty()) getString(R.string.error_required) else null
        }
    }

    override fun onBackPressed() {
        if ((!viewBinding.etCompanyName.text.isNullOrEmpty() ||
            !viewBinding.etRole.text.isNullOrEmpty() ||
            !viewBinding.etStartDate.text.isNullOrEmpty() ||
            !viewBinding.etEndDate.text.isNullOrEmpty()) &&
            workingExpId == CREATE_MODE
        ) {
            viewModel.showConfirmationDiscard()
        } else {
            super.onBackPressed()
        }
    }
}