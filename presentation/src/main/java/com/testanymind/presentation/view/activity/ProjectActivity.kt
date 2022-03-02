package com.testanymind.presentation.view.activity

import android.content.Context
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.divider.MaterialDividerItemDecoration
import com.testanymind.presentation.*
import com.testanymind.presentation.base.DataBindingActivity
import com.testanymind.presentation.databinding.ActivityProjectBinding
import com.testanymind.presentation.extension.observe
import com.testanymind.presentation.extension.observeTrigger
import com.testanymind.presentation.view.EducationAdapter
import com.testanymind.presentation.view.ProjectAdapter
import com.testanymind.presentation.view.ProjectViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProjectActivity : DataBindingActivity<ActivityProjectBinding>() {

    override fun layoutId() = R.layout.activity_project
    override fun getToolBar() = viewBinding.toolbar

    private val viewModel: ProjectViewModel by viewModel()

    private val projectAdapter by lazy {
        ProjectAdapter(listOf())
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
        menuInflater.inflate(R.menu.menu_personal_info, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_save -> viewModel.save(this)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun start() {
        initView()
        initListener()
        initObserver()
        viewModel.getProject()
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
//            ivEdit.setOnClickListener { viewModel.showEditPersonalUi() }
//            viewPersonal.ivEdit.setOnClickListener { viewModel.showEditPersonalUi() }
//            viewEducation.ivEdit.setOnClickListener { viewModel.showEditEducationUi() }
//            viewSkill.ivEdit.setOnClickListener { viewModel.showEditSkillBottomSheet() }
//            viewExperience.ivEdit.setOnClickListener { viewModel.showEditExperienceUi() }
//            viewProject.ivEdit.setOnClickListener { viewModel.showEditProjectUi() }
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