package com.testanymind.presentation.view.activity

import android.view.Menu
import android.view.MenuItem
import com.google.android.material.chip.Chip
import com.testanymind.presentation.R
import com.testanymind.presentation.base.DataBindingActivity
import com.testanymind.presentation.databinding.ActivityMainBinding

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

        }
        addChipToGroup("Android")
        addChipToGroup("Kotlin")
        addChipToGroup("Golang")
        addChipToGroup("Retrofit")
        addChipToGroup("Koin")
        addChipToGroup("Jetpack")
    }

    private fun addChipToGroup(tagText: String) {
        val chip = Chip(this).apply {
            text = tagText
            isClickable = false
            isCheckable = false
            isCloseIconVisible = false
        }
        viewBinding.chipGroupSkill.addView(chip)
    }
}