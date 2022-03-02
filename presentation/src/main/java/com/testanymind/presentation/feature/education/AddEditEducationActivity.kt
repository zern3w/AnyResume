package com.testanymind.presentation.view.activity

import android.content.Context
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.testanymind.presentation.R
import com.testanymind.presentation.base.DataBindingActivity
import com.testanymind.presentation.databinding.ActivityAddEditEducationBinding
import com.testanymind.presentation.databinding.ActivityAddEditWorkingExperienceBinding
import com.testanymind.presentation.extension.observeTrigger
import com.testanymind.presentation.view.EducationViewModel
import com.testanymind.presentation.view.WorkExperienceViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddEditEducationActivity : DataBindingActivity<ActivityAddEditEducationBinding>() {

    override fun layoutId() = R.layout.activity_add_edit_education
    override fun getToolBar() = viewBinding.toolbar

    private val viewModel: EducationViewModel by viewModel()

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, AddEditEducationActivity::class.java)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_save, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_save -> viewModel.save()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun start() {
        initView()
        initListener()
        initObserver()
    }

    private fun initView() {
        title = "${getString(R.string.add)} ${getString(R.string.education)}"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    private fun initObserver() {
        viewModel.apply {
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
}