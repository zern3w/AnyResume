package com.testanymind.presentation.feature.project

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.testanymind.domain.common.DataCenter
import com.testanymind.domain.common.Result
import com.testanymind.domain.model.ProjectDetail
import com.testanymind.domain.usecase.*
import com.testanymind.presentation.base.BaseViewModel
import com.testanymind.presentation.lifecycle.LiveEvent
import com.testanymind.presentation.lifecycle.LiveTrigger
import com.testanymind.presentation.lifecycle.MutableLiveEvent
import com.testanymind.presentation.lifecycle.MutableLiveTrigger
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ProjectViewModel(
    private val getAllProjectUseCase: GetAllProjectUseCase,
    private val saveAllProjectUseCase: SaveAllProjectUseCase,
    private val deleteAllProjectUseCase: DeleteAllProjectUseCase,
) : BaseViewModel() {

    private val _projectList = MutableLiveData<List<ProjectDetail>>(arrayListOf())
    val projectList: LiveData<List<ProjectDetail>> = _projectList

    private val _finishActivity = MutableLiveTrigger()
    val finishActivity: LiveTrigger = _finishActivity

    private val _showConfirmationDiscard = MutableLiveTrigger()
    val showConfirmationDiscard: LiveTrigger = _showConfirmationDiscard

    private val _showAddEditUi = MutableLiveTrigger()
    val showAddEditUi: LiveTrigger = _showAddEditUi

    private val _showOrHideEmptyState = MutableLiveEvent<Boolean>()
    val showOrHideEmptyState: LiveEvent<Boolean> = _showOrHideEmptyState

    fun getProjectList() {
        viewModelScope.launch {
            _dataLoading.postValue(true)
            when (val result = getAllProjectUseCase.invoke()) {
                is Result.Success -> {
                    _dataLoading.postValue(false)
                    result.data.collect { list ->
                        val projectList = list.map { it.toProject() }
                        _projectList.value = projectList
                        _showOrHideEmptyState.setEventValue(projectList.isEmpty())
                    }
                }
                is Result.Error -> {
                    _dataLoading.postValue(false)
                    _error.postValue(result.exception.message.orEmpty())
                }
            }
        }
    }

    fun save() {
        viewModelScope.launch {
            _dataLoading.postValue(true)
            deleteAllProjectUseCase.invoke()

            val list = DataCenter.getDemoProjectList().map { it.toEntity() }

            when (val result = saveAllProjectUseCase.invoke(list)) {
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

    fun showAddEditUi() {
        _showAddEditUi.trigger()
    }
}