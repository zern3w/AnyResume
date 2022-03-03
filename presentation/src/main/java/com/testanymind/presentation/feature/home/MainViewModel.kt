package com.testanymind.presentation.feature.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.testanymind.domain.common.DataCenter
import com.testanymind.domain.common.Result
import com.testanymind.domain.model.*
import com.testanymind.domain.usecase.*
import com.testanymind.presentation.base.BaseViewModel
import com.testanymind.presentation.lifecycle.LiveEvent
import com.testanymind.presentation.lifecycle.LiveTrigger
import com.testanymind.presentation.lifecycle.MutableLiveEvent
import com.testanymind.presentation.lifecycle.MutableLiveTrigger
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(
    private val getPersonalInfoUseCase: GetPersonalInfoUseCase,
    private val getAllEducationUseCase: GetAllEducationUseCase,
    private val getSkillsUseCase: GetSkillsUseCase,
    private val getAllWorkingExperienceUseCase: GetAllWorkingExperienceUseCase,
    private val getAllProjectUseCase: GetAllProjectUseCase
) : BaseViewModel() {

    private val _personalInfo = MutableLiveData<PersonalInfo>()
    val personalInfo: LiveData<PersonalInfo> = _personalInfo

    private val _educationList = MutableLiveData<List<Education>>()
    val educationList: LiveData<List<Education>> = _educationList

    private val _skillList = MutableLiveData<List<Skill>>()
    val skillList: LiveData<List<Skill>> = _skillList

    private val _workingExpList = MutableLiveData<List<WorkingExperience>>()
    val workingExpList: LiveData<List<WorkingExperience>> = _workingExpList

    private val _projectList = MutableLiveData<List<ProjectDetail>>()
    val projectList: LiveData<List<ProjectDetail>> = _projectList

    private val _showEditPersonalUiEvent = MutableLiveTrigger()
    val showEditPersonalUiEvent: LiveTrigger = _showEditPersonalUiEvent

    private val _showEditEducationUiEvent = MutableLiveTrigger()
    val showEditEducationUiEvent: LiveTrigger = _showEditEducationUiEvent

    private val _showEditSkillBottomSheetEvent = MutableLiveTrigger()
    val showEditSkillBottomSheetEvent: LiveTrigger = _showEditSkillBottomSheetEvent

    private val _showEditExperienceUiEvent = MutableLiveTrigger()
    val showEditExperienceUiEvent: LiveTrigger = _showEditExperienceUiEvent

    private val _showEditProjectUiEvent = MutableLiveTrigger()
    val showEditProjectUiEvent: LiveTrigger = _showEditProjectUiEvent

    private val _toggleEditModeSwitch = MutableLiveEvent<Boolean>()
    val toggleEditModeSwitch: LiveEvent<Boolean> = _toggleEditModeSwitch

    private var isEditModeSwitch = false
    private var isDemoModeSwitch = false

    fun getAllData() {
        getPersonalInfo()
        getEducationList()
        getSkillList()
        getWorkingExpList()
        getProjectList()
    }

    private fun getPersonalInfo() {
        viewModelScope.launch {
            _dataLoading.postValue(true)
            when (val result = getPersonalInfoUseCase.invoke()) {
                is Result.Success -> {
                    _dataLoading.postValue(false)
                    result.data.collect {
                        _personalInfo.value = if (it == null) PersonalInfo.empty() else it.toPersonalInfo()
                    }
                }
                is Result.Error -> {
                    _dataLoading.postValue(false)
                    _error.postValue(result.exception.message.orEmpty())
                }
            }
        }
    }

    private fun getEducationList() {
        viewModelScope.launch {
            _dataLoading.postValue(true)
            when (val result = getAllEducationUseCase.invoke()) {
                is Result.Success -> {
                    _dataLoading.postValue(false)
                    result.data.collect { list ->
                        _educationList.value = list.map { it.toEducation() }
                    }
                }
                is Result.Error -> {
                    _dataLoading.postValue(false)
                    _error.postValue(result.exception.message.orEmpty())
                }
            }
        }
    }

    private fun getSkillList() {
        viewModelScope.launch {
            _dataLoading.postValue(true)
            when (val result = getSkillsUseCase.invoke()) {
                is Result.Success -> {
                    _dataLoading.postValue(false)
                    result.data.collect { list ->
                        _skillList.value = list.map { it.toSkill() }
                    }
                }
                is Result.Error -> {
                    _dataLoading.postValue(false)
                    _error.postValue(result.exception.message.orEmpty())
                }
            }
        }
    }

    private fun getWorkingExpList() {
        viewModelScope.launch {
            _dataLoading.postValue(true)
            when (val result = getAllWorkingExperienceUseCase.invoke()) {
                is Result.Success -> {
                    _dataLoading.postValue(false)
                    result.data.collect { list ->
                        _workingExpList.value = list.map { it.toWorkingExperience() }
                    }
                }
                is Result.Error -> {
                    _dataLoading.postValue(false)
                    _error.postValue(result.exception.message.orEmpty())
                }
            }
        }
    }

    private fun getProjectList() {
        viewModelScope.launch {
            _dataLoading.postValue(true)
            when (val result = getAllProjectUseCase.invoke()) {
                is Result.Success -> {
                    _dataLoading.postValue(false)
                    result.data.collect { list ->
                        _projectList.value = list.map { it.toProject() }
                    }
                }
                is Result.Error -> {
                    _dataLoading.postValue(false)
                    _error.postValue(result.exception.message.orEmpty())
                }
            }
        }
    }

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

    fun toggleDemoModeSwitch() {
        isDemoModeSwitch = !isDemoModeSwitch
        if (isDemoModeSwitch) {
            getDemoData()
        } else {
            getAllData()
        }
    }

    fun toggleEditModeSwitch() {
        isEditModeSwitch = !isEditModeSwitch
        _toggleEditModeSwitch.setEventValue(isEditModeSwitch)
    }

    private fun getDemoData() {
        viewModelScope.launch {
            _personalInfo.value = DataCenter.getPersonalInfo()
            _educationList.value = DataCenter.getDemoEducationList()
            _skillList.value = DataCenter.getDemoSkillList()
            _workingExpList.value = DataCenter.getDemoWorkingExperienceList()
            _projectList.value = DataCenter.getDemoProjectList()
        }
    }
}