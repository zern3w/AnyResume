package com.testanymind.presentation.view.activity

import android.content.Context
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.testanymind.presentation.R
import com.testanymind.presentation.base.DataBindingActivity
import com.testanymind.presentation.databinding.ActivityPersonalInfoBinding
import com.testanymind.presentation.extension.observeEvent
import com.testanymind.presentation.extension.observeTrigger
import com.testanymind.presentation.view.PersonalInfoViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PersonalInfoActivity : DataBindingActivity<ActivityPersonalInfoBinding>() {

    override fun layoutId() = R.layout.activity_personal_info
    override fun getToolBar() = viewBinding.toolbar

    private val viewModel: PersonalInfoViewModel by viewModel()

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, PersonalInfoActivity::class.java)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_personal_info, menu)
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
        initObserver()

        viewModel.getPersonalInfo()
    }

    override fun onBackPressed() {
        viewModel.showConfirmationDiscard()
    }

    private fun initView() {
        title = getString(R.string.personal)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewBinding.apply {
            viewModel = this@PersonalInfoActivity.viewModel
            lifecycleOwner = this@PersonalInfoActivity
        }
    }

    private fun initObserver() {
        viewModel.apply {
            observeEvent(showErrorInvalidEmailFormat) { isError ->
                viewBinding.textLayoutEmail.error = if (isError) getString(R.string.error_invalid_email_format) else null
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
}