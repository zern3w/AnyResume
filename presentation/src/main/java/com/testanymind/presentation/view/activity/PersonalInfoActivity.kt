package com.testanymind.presentation.view.activity

import android.content.Context
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.divider.MaterialDividerItemDecoration
import com.testanymind.presentation.*
import com.testanymind.presentation.base.DataBindingActivity
import com.testanymind.presentation.databinding.ActivityMainBinding
import com.testanymind.presentation.databinding.ActivityPersonalInfoBinding
import com.testanymind.presentation.extension.observeEvent
import com.testanymind.presentation.extension.observeTrigger
import com.testanymind.presentation.view.EducationAdapter
import com.testanymind.presentation.view.ProjectAdapter
import com.testanymind.presentation.view.SkillBottomSheetFragment
import com.testanymind.presentation.view.WorkingExperienceAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class PersonalInfoActivity : DataBindingActivity<ActivityPersonalInfoBinding>() {

    override fun layoutId() = R.layout.activity_personal_info
    override fun getToolBar() = viewBinding.toolbar

//    private val viewModel: MainViewModel by viewModel()

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
        title = getString(R.string.personal)
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