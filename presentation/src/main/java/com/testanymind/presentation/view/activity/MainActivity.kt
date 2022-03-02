package com.testanymind.presentation.view.activity

import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.divider.MaterialDividerItemDecoration
import com.testanymind.presentation.*
import com.testanymind.presentation.base.DataBindingActivity
import com.testanymind.presentation.databinding.ActivityMainBinding
import com.testanymind.presentation.extension.observeEvent
import com.testanymind.presentation.extension.observeTrigger
import com.testanymind.presentation.view.EducationAdapter
import com.testanymind.presentation.view.ProjectAdapter
import com.testanymind.presentation.view.SkillBottomSheetFragment
import com.testanymind.presentation.view.WorkingExperienceAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : DataBindingActivity<ActivityMainBinding>() {

    override fun layoutId() = R.layout.activity_main
    override fun getToolBar() = viewBinding.toolbar

    private val viewModel: MainViewModel by viewModel()

    private val educationAdapter by lazy { EducationAdapter(listOf()) }
    private val workingExperienceAdapter by lazy { WorkingExperienceAdapter(listOf()) }
    private val projectAdapter by lazy { ProjectAdapter(listOf()) }

    private val recyclerViewDivider by lazy {
        MaterialDividerItemDecoration(
            this@MainActivity,
            LinearLayoutManager.VERTICAL
        ).apply {
            setDividerColorResource(this@MainActivity, R.color.grey_divider)
            dividerInsetStart = resources.getDimensionPixelOffset(R.dimen.spacing_xxxlarge)
            isLastItemDecorated = false
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_toggle_edit_mode -> viewModel.toggleEditModeSwitch()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun start() {
        initView()
        initListener()
        initDemoData()
        initObserver()

        viewModel.toggleEditModeSwitch()
    }

    private fun initView() {
        title = ""

        viewBinding.apply {
            viewEducation.rvEducation.apply {
                addItemDecoration(recyclerViewDivider)
                adapter = educationAdapter
            }

            viewExperience.rvExperience.apply {
                addItemDecoration(recyclerViewDivider)
                adapter = workingExperienceAdapter
            }

            viewProject.rvProject.apply {
                addItemDecoration(recyclerViewDivider)
                adapter = projectAdapter
            }
        }
    }

    private fun initDemoData() {
        viewBinding.apply {
            ivAvatar.load(DataCenter.getDemoAvatar())
            tvName.text = DataCenter.getDemoName()
            tvRole.text = DataCenter.getDemoRole()

            viewPersonal.apply {
                etMobile.setText(DataCenter.getDemoMobile())
                etEmail.setText(DataCenter.getDemoEmail())
                etAddress.setText(DataCenter.getDemoAddress())
            }

            educationAdapter.submitData(DataCenter.getDemoEducationList())
            viewSkill.chipGroupSkill.addChips(DataCenter.getDemoSkillList())
            workingExperienceAdapter.submitData(DataCenter.getDemoWorkingExperienceList())
            projectAdapter.submitData(DataCenter.getDemoProjectList(this@MainActivity))
        }
    }

    private fun initObserver() {
        viewModel.apply {
            observeEvent(toggleEditModeSwitch, ::isEditModeSwitch)
            observeTrigger(showEditPersonalUiEvent) { this@MainActivity.showEditPersonalUi() }
            observeTrigger(showEditEducationUiEvent) { this@MainActivity.showEditEducationUi() }
            observeTrigger(showEditSkillBottomSheetEvent) { this@MainActivity.showEditSkillBottomSheet() }
            observeTrigger(showEditExperienceUiEvent) { this@MainActivity.showEditExperienceUi() }
            observeTrigger(showEditProjectUiEvent) { this@MainActivity.showEditProjectUi() }
        }
    }

    private fun initListener() {
        viewBinding.apply {
            ivEdit.setOnClickListener { viewModel.showEditPersonalUi() }
            viewPersonal.ivEdit.setOnClickListener { viewModel.showEditPersonalUi() }
            viewEducation.ivEdit.setOnClickListener { viewModel.showEditEducationUi() }
            viewSkill.ivEdit.setOnClickListener { viewModel.showEditSkillBottomSheet() }
            viewExperience.ivEdit.setOnClickListener { viewModel.showEditExperienceUi() }
            viewProject.ivEdit.setOnClickListener { viewModel.showEditProjectUi() }
        }
    }

    private fun isEditModeSwitch(edited: Boolean) {
        viewBinding.apply {
            ivEdit.isVisible = edited

            viewPersonal.ivEdit.isVisible = edited
            viewPersonal.ivIcon.isVisible = !edited

            viewEducation.ivEdit.isVisible = edited
            viewEducation.ivIcon.isVisible = !edited

            viewSkill.ivEdit.isVisible = edited
            viewSkill.ivIcon.isVisible = !edited

            viewExperience.ivEdit.isVisible = edited
            viewExperience.ivIcon.isVisible = !edited

            viewProject.ivEdit.isVisible = edited
            viewProject.ivIcon.isVisible = !edited
        }
    }

    private fun showEditPersonalUi() {

    }

    private fun showEditEducationUi() {

    }

    private fun showEditSkillBottomSheet() {
        SkillBottomSheetFragment.newInstance().show(supportFragmentManager, "SkillBottomSheetFragment")
    }

    private fun showEditExperienceUi() {

    }

    private fun showEditProjectUi() {

    }
}