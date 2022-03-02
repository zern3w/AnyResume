package com.testanymind.presentation.view.activity

import android.content.Context
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import com.testanymind.presentation.*
import com.testanymind.presentation.base.DataBindingActivity
import com.testanymind.presentation.databinding.ActivityEducationBinding
import com.testanymind.presentation.databinding.ActivityProjectBinding
import com.testanymind.presentation.databinding.ActivityWorkExperienceBinding

class WorkingExperienceActivity : DataBindingActivity<ActivityWorkExperienceBinding>() {

    override fun layoutId() = R.layout.activity_work_experience
    override fun getToolBar() = viewBinding.toolbar

//    private val viewModel: MainViewModel by viewModel()

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, WorkingExperienceActivity::class.java)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_personal_info, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
//            R.id.menu_save -> viewModel.saveData()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun start() {
        initView()
        initListener()
        initObserver()
    }

    private fun initView() {
        title = getString(R.string.experiences)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewBinding.apply {

        }
    }

    private fun initObserver() {
//        viewModel.apply {
//            observeEvent(toggleEditModeSwitch, ::isEditModeSwitch)
//            observeTrigger(showEditPersonalUiEvent) { this@PersonalInfoActivity.showEditPersonalUi() }
//            observeTrigger(showEditEducationUiEvent) { this@PersonalInfoActivity.showEditEducationUi() }
//            observeTrigger(showEditSkillBottomSheetEvent) { this@PersonalInfoActivity.showEditSkillBottomSheet() }
//            observeTrigger(showEditExperienceUiEvent) { this@PersonalInfoActivity.showEditExperienceUi() }
//            observeTrigger(showEditProjectUiEvent) { this@PersonalInfoActivity.showEditProjectUi() }
//        }
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
}