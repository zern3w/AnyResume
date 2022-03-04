package com.testanymind.presentation.feature.workexperience

import android.content.Context
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.testanymind.domain.Constants
import com.testanymind.domain.model.WorkingExperience
import com.testanymind.presentation.R
import com.testanymind.presentation.base.DataBindingActivity
import com.testanymind.presentation.databinding.ActivityAddEditWorkingExperienceBinding
import com.testanymind.presentation.extension.observe
import com.testanymind.presentation.extension.observeTrigger
import com.testanymind.presentation.extension.toReadableMonthYear
import com.whiteelephant.monthpicker.MonthPickerDialog
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

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

    private val today by lazy { Date() }

    private val calendar by lazy {
        Calendar.getInstance().apply {
            time = today
        }
    }

    private var startDate = Date()
    private var endDate = Date()

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
                if (areRequireWasFilled()) {
                    if (isEndDateAfterStartDate()) {
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
            }
            R.id.menu_delete -> {
                viewModel.delete(workingExpId)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun start() {
        initView()
        initListener()
        initObserver()

        if (workingExpId != CREATE_MODE) viewModel.getWorkExp(workingExpId)
    }

    private fun initListener() {
        viewBinding.etStartDate.setOnClickListener {
            showMonthYearPickerDialog(true)
        }

        viewBinding.etEndDate.setOnClickListener {
            showMonthYearPickerDialog(false)
        }
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
            val result = !etCompanyName.text.isNullOrEmpty() && !etRole.text.isNullOrEmpty() && !etStartDate.text.isNullOrEmpty() && !etEndDate.text.isNullOrEmpty()
            showErrorRequired(!result)
            return result
        }
    }

    private fun isEndDateAfterStartDate(): Boolean {
      val result = endDate.after(startDate)
        showErrorEndDateBeforeStart(!result)
        return result
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

    private fun showErrorEndDateBeforeStart(isVisible: Boolean) {
        viewBinding.apply {
            textLayoutEndDate.error =
                if (isVisible) getString(R.string.error_end_date_before_start) else null
        }
    }

    private fun showMonthYearPickerDialog(isStartDate: Boolean) {
        val currentMonth = calendar.get(Calendar.MONTH)
        val currentYear = calendar.get(Calendar.YEAR)

        val builder = MonthPickerDialog.Builder(this, { selectedMonth, selectedYear ->
            val cal = Calendar.getInstance().apply {
                set(Calendar.YEAR, selectedYear)
                set(Calendar.MONTH, selectedMonth)
            }
            if (isStartDate) {
                startDate = cal.time
                viewBinding.etStartDate.setText(startDate.toReadableMonthYear(Constants.FORMAT_MONTH_YEAR))
            } else {
                endDate = cal.time
                viewBinding.etEndDate.setText(endDate.toReadableMonthYear(Constants.FORMAT_MONTH_YEAR))
            }
        }, currentYear, currentMonth)

        builder.setMaxYear(currentYear)
            .setMaxMonth(currentMonth)
            .setTitle(getString(if (isStartDate) R.string.hint_start_date else R.string.hint_end_date))
            .build()
            .show()
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