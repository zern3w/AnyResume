package com.testanymind.presentation.view.activity

import androidx.lifecycle.ViewModel
import com.testanymind.presentation.lifecycle.LiveEvent
import com.testanymind.presentation.lifecycle.LiveTrigger
import com.testanymind.presentation.lifecycle.MutableLiveEvent
import com.testanymind.presentation.lifecycle.MutableLiveTrigger

class MainViewModel : ViewModel() {

    private val _showEditPersonalUiEvent =  MutableLiveTrigger()
    val showEditPersonalUiEvent: LiveTrigger = _showEditPersonalUiEvent

    private val _showEditEducationUiEvent =  MutableLiveTrigger()
    val showEditEducationUiEvent: LiveTrigger = _showEditEducationUiEvent

    private val _showEditSkillBottomSheetEvent =  MutableLiveTrigger()
    val showEditSkillBottomSheetEvent: LiveTrigger = _showEditSkillBottomSheetEvent

    private val _showEditExperienceUiEvent =  MutableLiveTrigger()
    val showEditExperienceUiEvent: LiveTrigger = _showEditExperienceUiEvent

    private val _showEditProjectUiEvent = MutableLiveTrigger()
    val showEditProjectUiEvent: LiveTrigger = _showEditProjectUiEvent

    private val _toggleEditModeSwitch = MutableLiveEvent<Boolean>()
    val toggleEditModeSwitch: LiveEvent<Boolean> = _toggleEditModeSwitch

    private var isEditModeSwitch = false

    fun showEditPersonalUi() {
        _showEditPersonalUiEvent.trigger()
    }

    fun showEditEducationUi() {
        _showEditEducationUiEvent.trigger()
    }

    fun showEditSkillBottomSheet() {
        _showEditSkillBottomSheetEvent.trigger()
    }

    fun showEditExperienceUi() {
        _showEditExperienceUiEvent.trigger()
    }

    fun showEditProjectUi() {
        _showEditProjectUiEvent.trigger()
    }

    fun toggleEditModeSwitch() {
        isEditModeSwitch = !isEditModeSwitch
        _toggleEditModeSwitch.setEventValue(isEditModeSwitch)
    }
}