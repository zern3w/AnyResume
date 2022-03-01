package com.testanymind.presentation.view.activity

import android.view.Menu
import android.view.MenuItem
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.divider.MaterialDividerItemDecoration
import com.testanymind.presentation.R
import com.testanymind.presentation.addChips
import com.testanymind.presentation.base.DataBindingActivity
import com.testanymind.presentation.databinding.ActivityMainBinding
import com.testanymind.presentation.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : DataBindingActivity<ActivityMainBinding>() {

    override fun layoutId() = R.layout.activity_main
    override fun getToolBar() = viewBinding.toolbar

    private val viewModel: MainViewModel by viewModel()

    private var isEditModeSwitch = false

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

            viewPersonal.ivEdit.setOnClickListener {
                isPersonalEditingMode(true)
            }
            viewPersonal.ivSave.setOnClickListener {
                isPersonalEditingMode(false)
            }
            viewPersonal.ivCancel.setOnClickListener {
                isPersonalEditingMode(false)
            }

            ivAvatar.setOnLongClickListener {
                isEditModeSwitch(isEditModeSwitch)
                true
            }

            viewPersonal.etMobile.setText("099-4799456")
            viewPersonal.etEmail.setText("puttipong@gmail.com")
            viewPersonal.etAddress.setText("50/7 Village No.1 Padat Sub-district, Muang District, Chiang Mai, 50100")

            viewEducation.rvEducation.apply {
                addItemDecoration(divider)
                adapter = EducationAdapter(
                    listOf(
                        Education(
                            schoolName = "Chiang Mai University",
                            logo = "https://bit.ly/3vJryQX",
                            _class = "Bachelor in Software Engineering",
                            passingYear = "2012-2017",
                            gpa = 2.76
                        ),
                        Education(
                            schoolName = "Yupparaj Wittayarai School",
                            logo = "https://scontent.fcnx1-1.fna.fbcdn.net/v/t39.30808-6/273322075_4957866777628246_3901931508692881460_n.jpg?_nc_cat=111&ccb=1-5&_nc_sid=09cbfe&_nc_eui2=AeF1n571rKLC9lUs2p6bxLJvywTWq5YuFILLBNarli4UgoKxki1c8MyeNUMVsVz_i7Y&_nc_ohc=0EdKOHkRztgAX-_BOX-&_nc_ht=scontent.fcnx1-1.fna&oh=00_AT9_IvRSfjKcmv0LpStZmXgxHMTlJ9fggHjZOf1X6uSOKQ&oe=62234409",
                            _class = "High School",
                            passingYear = "2006-2012",
                            gpa = 3.45
                        )
                    )
                )
            }

            viewExperience.rvExperience.apply {
                addItemDecoration(divider)
                adapter = WorkingExperienceAdapter(
                    listOf(
                        WorkingExperience(
                            companyName = "Ookbee U Co.,Ltd",
                            logo = "https://bit.ly/3M8fIWh",
                            role = "Android Developer",
                            startDate = "",
                            endDate = ""
                        ),
                        WorkingExperience(
                            companyName = "CLBS Co.,Ltd",
                            logo = "https://scontent.fcnx1-1.fna.fbcdn.net/v/t39.30808-6/250373640_10159539672512954_7098023189049123787_n.jpg?_nc_cat=101&ccb=1-5&_nc_sid=09cbfe&_nc_eui2=AeE38yROhFvh_iYq7TBmyFaI9gtGfw0t_t32C0Z_DS3-3UpAJj6-o0yumjZEpwRgEVk&_nc_ohc=OeSNUPYhJ1cAX8EqLDt&_nc_ht=scontent.fcnx1-1.fna&oh=00_AT8SoW7jMvVBOeUurTlIWgD_PdZR3WKmkizD2XbyxWQAEw&oe=62234580",
                            role = "Mobile Developer",
                            startDate = "",
                            endDate = ""
                        ),
                        WorkingExperience(
                            companyName = "Iglu Co.,Ltd",
                            logo = "https://media-exp1.licdn.com/dms/image/C4D0BAQG4MPNpiYhykQ/company-logo_100_100/0/1560923328660?e=1654128000&v=beta&t=K05Pq-qnH6rYK8rvwwfhyEAqeaz2Ywy8u2Uiycv13Lk",
                            role = "Intern",
                            startDate = "",
                            endDate = ""
                        )
                    )
                )
            }

            viewProject.rvProject.apply {
                addItemDecoration(divider)
                adapter = ProjectAdapter(
                    listOf(
                        ProjectDetail(
                            projectName = "Joylada",
                            logo = "https://www.ais.th/joylada/images/logo_app.jpg",
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
                            logo = "https://scontent.fcnx1-1.fna.fbcdn.net/v/t39.30808-6/227943469_10159559423489697_2674711483060557812_n.jpg?_nc_cat=1&ccb=1-5&_nc_sid=09cbfe&_nc_eui2=AeGijAaiJIxkuvFu94vZMxjXLx_7VY8iiVAvH_tVjyKJUM4rhZGbmZKGMvu1X76i7PA&_nc_ohc=GpkBjQos-fYAX9QLlYf&_nc_ht=scontent.fcnx1-1.fna&oh=00_AT-DVydw9OWBa28fyu1PkpeUDWAJQyPQFHbNVm5BvB_IXA&oe=62230351",
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
                            logo = "https://play-lh.googleusercontent.com/LKZQSflMawlUoOxHjb_lFgjYvHUCijfDDXsAAvUK_04Sq3zgePTYjp7AjcHOae5Fdg",
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
                            logo = "https://play-lh.googleusercontent.com/M2l5N1nM6JjNCTa9hI7N1iBFKoVsNU0oT4sgY6rHyY7-aljCtQCXkX2lRO9s88kXO8k",
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

            viewSkill.chipGroupSkill.addChips(
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

    private fun isEditModeSwitch(edited: Boolean) {
        isEditModeSwitch = !edited

        viewBinding.apply {
            viewPersonal.llEditMode.isVisible = isEditModeSwitch
            viewPersonal.ivIcon.isVisible = !isEditModeSwitch

            viewEducation.ivEdit.isVisible = isEditModeSwitch
            viewEducation.ivIcon.isVisible = !isEditModeSwitch

            viewSkill.ivEdit.isVisible = isEditModeSwitch
            viewSkill.ivIcon.isVisible = !isEditModeSwitch

            viewExperience.ivEdit.isVisible = isEditModeSwitch
            viewExperience.ivIcon.isVisible = !isEditModeSwitch

            viewProject.ivEdit.isVisible = isEditModeSwitch
            viewProject.ivIcon.isVisible = !isEditModeSwitch
        }
    }

    private fun isPersonalEditingMode(isEditing: Boolean) {
        viewBinding.apply {
            viewPersonal.ivEdit.isVisible = !isEditing
            viewPersonal.ivSave.isVisible = isEditing
            viewPersonal.ivCancel.isVisible = isEditing
        }
    }
}