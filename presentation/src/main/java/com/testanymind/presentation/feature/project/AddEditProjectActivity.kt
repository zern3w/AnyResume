package com.testanymind.presentation.feature.project

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.text.InputType
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.chip.Chip
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.testanymind.domain.model.ProjectDetail
import com.testanymind.presentation.R
import com.testanymind.presentation.base.DataBindingActivity
import com.testanymind.presentation.databinding.ActivityAddEditProjectBinding
import com.testanymind.presentation.extension.addChips
import com.testanymind.presentation.extension.observe
import com.testanymind.presentation.extension.observeEvent
import com.testanymind.presentation.extension.observeTrigger
import com.testanymind.presentation.util.KeyboardUtils
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddEditProjectActivity : DataBindingActivity<ActivityAddEditProjectBinding>() {

    override fun layoutId() = R.layout.activity_add_edit_project
    override fun getToolBar() = viewBinding.toolbar

    private val viewModel: AddEditProjectViewModel by viewModel()

    private val projectId by lazy {
        intent.getIntExtra(
            EXTRA_ITEM_ID,
            CREATE_MODE
        )
    }

    companion object {
        private const val CREATE_MODE = -1
        private const val EXTRA_ITEM_ID = "AddEditProjectActivity.EXTRA_ITEM_ID"

        fun newIntent(context: Context, itemId: Int = -1): Intent {
            return Intent(context, AddEditProjectActivity::class.java).apply {
                putExtra(EXTRA_ITEM_ID, itemId)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(
            if (projectId == CREATE_MODE) R.menu.menu_save else R.menu.menu_save_delete,
            menu
        )
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_save -> {
                showErrorRequired(!areRequireWasFilled())
                if (areRequireWasFilled()) {
                    if (projectId == CREATE_MODE) {
                        viewModel.save(
                            ProjectDetail(
                                projectName = viewBinding.etProjectName.text.toString(),
                                teamSize = viewBinding.etTeamSize.text.toString().toIntOrNull()
                                    ?: -1,
                                projectSummary = viewBinding.etProjectSummary.text.toString(),
                                technologyUsed = viewModel.techList.value?.toList().orEmpty(),
                                role = viewBinding.etRole.text.toString(),
                                logo = ""
                            )
                        )
                    } else {
                        viewModel.update(
                            ProjectDetail(
                                _id = projectId,
                                projectName = viewBinding.etProjectName.text.toString(),
                                teamSize = viewBinding.etTeamSize.text.toString().toIntOrNull()
                                    ?: -1,
                                projectSummary = viewBinding.etProjectSummary.text.toString(),
                                technologyUsed = viewModel.techList.value?.toList().orEmpty(),
                                role = viewBinding.etRole.text.toString(),
                                logo = ""
                            )
                        )
                    }
                }
            }
            R.id.menu_delete -> {
                viewModel.delete(projectId)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun start() {
        initView()
        initObserver()

        if (projectId != CREATE_MODE) viewModel.getProject(projectId)
    }

    private fun initView() {
        title = "${getString(if (projectId == CREATE_MODE) R.string.add else R.string.edit)} ${
            getString(R.string.project)
        }"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewBinding.apply {
            etTechUsed.apply {
                setOnEditorActionListener { v, actionId, _ ->
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        onActionDone(v)
                        KeyboardUtils.hideKeyboard(etTechUsed)
                        return@setOnEditorActionListener true
                    }
                    false
                }
            }
        }
    }

    private fun initObserver() {
        viewModel.apply {
            observe(project) {
                viewBinding.apply {
                    etProjectName.setText(it.projectName)
                    etTeamSize.setText(it.teamSize.toString())
                    etProjectSummary.setText(it.projectSummary)
                    etRole.setText(it.role)

                    it.technologyUsed.forEach {
                        addChipToGroup(it)
                    }
                }
            }

            observeTrigger(finishActivity) {
                finish()
            }

            observeTrigger(showConfirmationDiscard) {
                showConfirmationDiscardDialog()
            }

            observeEvent(addTechEvent, ::addChipToGroup)
        }
    }

    private fun areRequireWasFilled(): Boolean {
        viewBinding.apply {
            return !etProjectName.text.isNullOrEmpty() && !etTeamSize.text.isNullOrEmpty() && !etProjectSummary.text.isNullOrEmpty() && !etRole.text.isNullOrEmpty()
        }
    }

    private fun onActionDone(v: TextView) {
        val tech = v.text.trim().toString()
        if (tech.isNotEmpty()) {
            v.text = null
            if (viewModel.isContainTech(tech).not()) viewModel.addTech(tech)
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
            textLayoutProjectName.error =
                if (isVisible && etProjectName.text.isNullOrEmpty()) getString(R.string.error_required) else null
            textLayoutTeamSize.error =
                if (isVisible && etTeamSize.text.isNullOrEmpty()) getString(R.string.error_required) else null
            textLayoutProjectSummary.error =
                if (isVisible && etProjectSummary.text.isNullOrEmpty()) getString(R.string.error_required) else null
            textLayoutRole.error =
                if (isVisible && etRole.text.isNullOrEmpty()) getString(R.string.error_required) else null
        }
    }

    private fun addChipToGroup(_text: String) {
        viewBinding.apply {
            val chip = Chip(this@AddEditProjectActivity).apply {
                text = _text
                isClickable = true
                isCheckable = false
                isCloseIconVisible = true

                setOnCloseIconClickListener {
                    viewModel.removeTech(_text)
                    etTechUsed.apply {
                        visibility = View.VISIBLE
                        inputType = InputType.TYPE_CLASS_TEXT
                        requestFocus()
                    }
                    if (!KeyboardUtils.isShowingKeyboard) {
                        KeyboardUtils.showKeyboard(etTechUsed)
                    }
                    chipGroup.removeView(this)
                }
            }
            chip.apply {
                chipStrokeWidth = 1f
                chipBackgroundColor =
                    ColorStateList.valueOf(Color.WHITE)
                setTextColor(Color.BLACK)
                closeIcon =
                    ContextCompat.getDrawable(this@AddEditProjectActivity, R.drawable.ic_cross)
            }
            chipGroup.addView(chip)
        }
    }

    override fun onBackPressed() {
        if ((!viewBinding.etProjectName.text.isNullOrEmpty() ||
                    !viewBinding.etTeamSize.text.isNullOrEmpty() ||
                    !viewBinding.etProjectSummary.text.isNullOrEmpty() ||
                    !viewBinding.etRole.text.isNullOrEmpty()) &&
            projectId == CREATE_MODE
        ) {
            viewModel.showConfirmationDiscard()
        } else {
            super.onBackPressed()
        }
    }
}