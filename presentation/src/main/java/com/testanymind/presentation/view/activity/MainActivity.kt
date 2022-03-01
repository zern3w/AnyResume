package com.testanymind.presentation.view.activity

import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.chip.Chip
import com.google.android.material.divider.MaterialDividerItemDecoration
import com.testanymind.presentation.R
import com.testanymind.presentation.addChips
import com.testanymind.presentation.base.DataBindingActivity
import com.testanymind.presentation.databinding.ActivityMainBinding
import com.testanymind.presentation.view.*

class MainActivity : DataBindingActivity<ActivityMainBinding>() {

    override fun layoutId() = R.layout.activity_main

    override fun getToolBar() = viewBinding.toolbar

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(androidx.core.R.menu.example_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

        }

        return super.onOptionsItemSelected(item)
    }

    override fun start() {
        title = ""

        viewBinding.apply {
            val divider = MaterialDividerItemDecoration(
                this@MainActivity,
                LinearLayoutManager.VERTICAL
            ).apply {
                setDividerColorResource(this@MainActivity, R.color.grey_divider)
                dividerInsetStart = resources.getDimensionPixelOffset(R.dimen.spacing_xxxlarge)
                isLastItemDecorated = false
            }

            rvEducation.apply {
                addItemDecoration(divider)
                adapter = EducationAdapter(
                    listOf(
                        Education(
                            schoolName = "Chiang Mai University",
                            _class = "Bachelor in Software Engineering",
                            passingYear = "2012-2017",
                            gpa = 2.76
                        ),
                        Education(
                            schoolName = "Yupparaj Wittayarai School",
                            _class = "High School",
                            passingYear = "2006-2012",
                            gpa = 3.45
                        ),
                        Education(
                            schoolName = "Wachirawit School",
                            _class = "Primary School",
                            passingYear = "2000-2006",
                            gpa = 3.75
                        )
                    )
                )
            }

            rvExperience.apply {
                addItemDecoration(divider)
                adapter = WorkingExperienceAdapter(
                    listOf(
                        WorkingExperience(
                            companyName = "Ookbee U Co.,Ltd",
                            role = "Android Developer",
                            startDate = "",
                            endDate = ""
                        ),
                        WorkingExperience(
                            companyName = "CLBS Co.,Ltd",
                            role = "Mobile Developer",
                            startDate = "",
                            endDate = ""
                        ),
                        WorkingExperience(
                            companyName = "Iglu Co.,Ltd",
                            role = "Intern",
                            startDate = "",
                            endDate = ""
                        )
                    )
                )
            }

            rvProject.apply {
                addItemDecoration(divider)
                adapter = ProjectAdapter(
                    listOf(
                        ProjectDetail(
                            projectName = "Joylada",
                            teamSize = 28,
                            projectSummary = getString(R.string.lorem),
                            technologyUsed = listOf(
                                "Android",
                                "iOS",
                                "Kotlin",
                                "Swift",
                                "AWS",
                                "Firebase",
                                "SignalR",
                                "Jira",
                                "Jenkins",
                                ".Net"
                            ),
                            role = "Android Developer"
                        ),
                        ProjectDetail(
                            projectName = "Powerbuy",
                            teamSize = 12,
                            projectSummary = getString(R.string.lorem),
                            technologyUsed = listOf(
                                "Android",
                                "iOS",
                                "Kotlin",
                                "Swift",
                                "Firebase",
                                "Algoria",
                                "Jira",
                                "JavaScript"
                            ),
                            role = "Android Developer"
                        ),
                        ProjectDetail(
                            projectName = "Beeber",
                            teamSize = 16,
                            projectSummary = getString(R.string.lorem),
                            technologyUsed = listOf(
                                "Android",
                                "iOS",
                                "Kotlin",
                                "Swift",
                                "AWS",
                                "Firebase",
                                "SignalR",
                                "Jira",
                                "Jenkins",
                                ".Net"
                            ),
                            role = "Android Developer"
                        ),
                        ProjectDetail(
                            projectName = "Ebuero",
                            teamSize = 24,
                            projectSummary = getString(R.string.lorem),
                            technologyUsed = listOf(
                                "Android",
                                "iOS",
                                "Kotlin",
                                "Swift",
                                "Firebase",
                                "SprintBoot",
                                "Jira",
                                "Java"
                            ),
                            role = "Android Developer"
                        )
                    )
                )
            }

            chipGroupSkill.addChips(
                listOf(
                    "Android",
                    "Kotlin",
                    "Firebase",
                    "Java",
                    "Golang",
                    "Retrofit",
                    "Koin",
                    "Jetpack"
                )
            )
        }
    }
}