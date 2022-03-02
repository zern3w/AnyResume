package com.testanymind.presentation.feature.project

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.testanymind.domain.common.Result
import com.testanymind.domain.model.ProjectDetail
import com.testanymind.domain.usecase.*
import com.testanymind.presentation.DataCenter
import com.testanymind.presentation.base.BaseViewModel
import com.testanymind.presentation.lifecycle.LiveTrigger
import com.testanymind.presentation.lifecycle.MutableLiveTrigger
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ProjectViewModel(
    private val getProjectUseCase: GetProjectUseCase,
    private val saveProjectUseCase: SaveProjectUseCase,
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

    fun getProject() {
        viewModelScope.launch {
            _dataLoading.postValue(true)
            when (val result = getProjectUseCase.invoke()) {
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

    fun save(context: Context) {
        viewModelScope.launch {
            _dataLoading.postValue(true)
            deleteAllProjectUseCase.invoke()

            val list = DataCenter.getDemoProjectList(context).map { it.toEntity() }

            when (val result = saveProjectUseCase.invoke(list)) {
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