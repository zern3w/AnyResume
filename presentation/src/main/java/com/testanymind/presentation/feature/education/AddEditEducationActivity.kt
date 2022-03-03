package com.testanymind.presentation.feature.education

import android.content.Context
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.testanymind.domain.model.Education
import com.testanymind.presentation.R
import com.testanymind.presentation.base.DataBindingActivity
import com.testanymind.presentation.databinding.ActivityAddEditEducationBinding
import com.testanymind.presentation.extension.observe
import com.testanymind.presentation.extension.observeTrigger
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddEditEducationActivity : DataBindingActivity<ActivityAddEditEducationBinding>(),
    TextWatcher {

    override fun layoutId() = R.layout.activity_add_edit_education
    override fun getToolBar() = viewBinding.toolbar

    private val viewModel: AddEditEducationViewModel by viewModel()

    private val educationId by lazy {
        intent.getIntExtra(EXTRA_ITEM_ID, CREATE_MODE)
    }

    private var etGpaLengthBeforeChanged = 0

    companion object {
        private const val CREATE_MODE = -1
        private const val EXTRA_ITEM_ID = "AddEditEducationActivity.EXTRA_ITEM_ID"

        fun newIntent(context: Context, itemId: Int = -1): Intent {
            return Intent(context, AddEditEducationActivity::class.java).apply {
                putExtra(EXTRA_ITEM_ID, itemId)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(
            if (educationId == CREATE_MODE) R.menu.menu_save else R.menu.menu_save_delete,
            menu
        )
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_save -> {
                showErrorRequired(!areRequireWasFilled())
                if (areRequireWasFilled()) {
                    if (educationId == CREATE_MODE) {
                        viewModel.save(
                            Education(
                                schoolName = viewBinding.etSchoolName.text.toString(),
                                logo = "",
                                _class = viewBinding.etClassName.text.toString(),
                                passingYear = viewBinding.etPassingYear.text.toString(),
                                gpa = viewBinding.etGpa.text.toString().toDoubleOrNull() ?: -1.0
                            )
                        )
                    } else {
                        viewModel.update(
                            Education(
                                _id = educationId,
                                schoolName = viewBinding.etSchoolName.text.toString(),
                                logo = "",
                                _class = viewBinding.etClassName.text.toString(),
                                passingYear = viewBinding.etPassingYear.text.toString(),
                                gpa = viewBinding.etGpa.text.toString().toDoubleOrNull() ?: -1.0
                            )
                        )
                    }
                }
            }
            R.id.menu_delete -> {
                viewModel.delete(educationId)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun start() {
        initView()
        initListener()
        initObserver()

        if (educationId != CREATE_MODE) viewModel.getEducation(educationId)
    }

    private fun initView() {
        title = "${getString(if (educationId == CREATE_MODE) R.string.add else R.string.edit)} ${
            getString(R.string.education)
        }"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewBinding.apply {
            lifecycleOwner = this@AddEditEducationActivity
            viewModel = this@AddEditEducationActivity.viewModel
        }
    }

    private fun initObserver() {
        viewModel.apply {
            observe(education) {
                viewBinding.apply {
                    etSchoolName.setText(it.schoolName)
                    etClassName.setText(it._class)
                    etPassingYear.setText(it.passingYear)
                    if (it.gpa != -1.0) etGpa.setText(it.gpa.toString())
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

    private fun initListener() {
        viewBinding.apply {
            etGpa.addTextChangedListener(this@AddEditEducationActivity)
        }
    }

    private fun areRequireWasFilled(): Boolean {
        viewBinding.apply {
            return !etSchoolName.text.isNullOrEmpty() && !etClassName.text.isNullOrEmpty() && !etPassingYear.text.isNullOrEmpty()
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

    private fun showErrorRequired(isVisible: Boolean) {
        viewBinding.apply {
            textLayoutSchoolName.error =
                if (isVisible && etSchoolName.text.isNullOrEmpty()) getString(R.string.error_required) else null
            textLayoutClass.error =
                if (isVisible && etClassName.text.isNullOrEmpty()) getString(R.string.error_required) else null
            textLayoutPassingYear.error =
                if (isVisible && etPassingYear.text.isNullOrEmpty()) getString(R.string.error_required) else null
        }
    }

    override fun onBackPressed() {
        if ((!viewBinding.etSchoolName.text.isNullOrEmpty() ||
            !viewBinding.etClassName.text.isNullOrEmpty() ||
            !viewBinding.etPassingYear.text.isNullOrEmpty() ||
            !viewBinding.etGpa.text.isNullOrEmpty()) &&
                    educationId == CREATE_MODE
        ) {
            viewModel.showConfirmationDiscard()
        } else {
            super.onBackPressed()
        }
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        etGpaLengthBeforeChanged = viewBinding.etGpa.length()
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }

    override fun afterTextChanged(s: Editable?) {
        viewBinding.apply {
            if (etGpa.length() == 2 && etGpaLengthBeforeChanged == 1) {
                val firstPositionText = etGpa.text.toString()[0]
                val secondPositionText = etGpa.text.toString()[1]
                etGpa.setText("$firstPositionText.$secondPositionText")
                etGpa.setSelection(etGpa.length())
            }
            if (etGpa.length() == 1 && etGpaLengthBeforeChanged == 0) {
                etGpa.removeTextChangedListener(this@AddEditEducationActivity)
                etGpa.setText("${etGpa.text.toString()}.")
                etGpa.setSelection(etGpa.length())
                etGpa.addTextChangedListener(this@AddEditEducationActivity)
            }
        }
    }
}