package com.testanymind.presentation.feature.project

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.testanymind.domain.common.DataCenter
import com.testanymind.domain.common.Result
import com.testanymind.domain.model.Education
import com.testanymind.domain.model.ProjectDetail
import com.testanymind.domain.model.Skill
import com.testanymind.domain.usecase.*
import com.testanymind.presentation.base.BaseViewModel
import com.testanymind.presentation.lifecycle.LiveEvent
import com.testanymind.presentation.lifecycle.LiveTrigger
import com.testanymind.presentation.lifecycle.MutableLiveEvent
import com.testanymind.presentation.lifecycle.MutableLiveTrigger
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

class AddEditProjectViewModel(
    private val getProjectUseCase: GetProjectUseCase,
    private val saveProjectUseCase: SaveProjectUseCase,
    private val updateProjectUseCase: UpdateProjectUseCase,
    private val deleteProjectUseCase: DeleteProjectUseCase
) : BaseViewModel() {

    private val _project = MutableLiveData<ProjectDetail>()
    val project: LiveData<ProjectDetail> = _project

    private val _addTechEvent = MutableLiveEvent<String>()
    val addTechEvent: LiveEvent<String> = _addTechEvent

    private val _techList = MutableLiveData<MutableList<String>>(arrayListOf())
    val techList: LiveData<MutableList<String>> = _techList

    private val _hasTech = MutableLiveData<Boolean>()
    val hasTech: LiveData<Boolean> = _hasTech

    private val _finishActivity = MutableLiveTrigger()
    val finishActivity: LiveTrigger = _finishActivity

    private val _showConfirmationDiscard = MutableLiveTrigger()
    val showConfirmationDiscard: LiveTrigger = _showConfirmationDiscard

    fun getProject(id: Int) {
        viewModelScope.launch {
            _dataLoading.postValue(true)
            when (val result = getProjectUseCase.invoke(id)) {
                is Result.Success -> {
                    _dataLoading.postValue(false)
                    result.data.filterNotNull().collect { data ->
                        _project.value = data.toProject()
                        _techList.value = data.toProject().technologyUsed.toMutableList()
                    }
                }
                is Result.Error -> {
                    _dataLoading.postValue(false)
                    _error.postValue(result.exception.message.orEmpty())
                }
            }
        }
    }

    fun save(data: ProjectDetail) {
        viewModelScope.launch {
            _dataLoading.postValue(true)
            when (val result = saveProjectUseCase.invoke(data.toEntity())) {
                is Result.Success -> {
                    _dataLoading.postValue(false)
                    _finishActivity.trigger()
                }
                is Result.Error -> {
                    _dataLoading.postValue(false)
                    _error.postValue(result.exception.message.orEmpty())
                }
            }
        }
    }

    fun update(data: ProjectDetail) {
        viewModelScope.launch {
            _dataLoading.postValue(true)
            when (val result = updateProjectUseCase.invoke(data.toEntity().apply { id = data._id })) {
                is Result.Success -> {
                    _dataLoading.postValue(false)
                    _finishActivity.trigger()
                }
                is Result.Error -> {
                    _dataLoading.postValue(false)
                    _error.postValue(result.exception.message.orEmpty())
                }
            }
        }
    }

    fun delete(id: Int) {
        viewModelScope.launch {
            _dataLoading.postValue(true)
            when (val result = deleteProjectUseCase.invoke(id)) {
                is Result.Success -> {
                    _dataLoading.postValue(false)
                    _finishActivity.trigger()
                }
                is Result.Error -> {
                    _dataLoading.postValue(false)
                    _error.postValue(result.exception.message.orEmpty())
                }
            }
        }
    }

    fun isContainTech(tech: String): Boolean {
        return if (!isTechEmpty()) {
            _techList.value?.find {
                it.equals(tech, ignoreCase = true)
            } != null
        } else {
            false
        }
    }

    fun removeLastTech() {
        if (!isTechEmpty()) {
            _techList.value = _techList.value?.apply {
                removeAt(this.size - 1)
            }
        }
    }

    fun removeTech(tech: String) {
        if (!isTechEmpty()) {
            _techList.value = _techList.value?.apply {
                remove(tech)
            }
        }
    }

    fun addTech(tech: String) {
        _techList.value = _techList.value?.apply {
            add(tech)
        }
        _addTechEvent.setEventValue(tech)
    }

    fun isTechEmpty(): Boolean {
        return _techList.value.isNullOrEmpty()
    }

    fun showConfirmationDiscard() {
        val isDataChanged = true
//        val isDataChanged = initialData != getChangedPersonalInfoEntity()
        if (isDataChanged) {
            _showConfirmationDiscard.trigger()
        } else {
            finishActivity()
        }
    }

    fun finishActivity() {
        _finishActivity.trigger()
    }
}