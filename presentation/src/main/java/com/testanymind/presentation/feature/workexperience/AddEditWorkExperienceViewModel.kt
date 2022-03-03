package com.testanymind.presentation.feature.workexperience

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.testanymind.domain.common.DataCenter
import com.testanymind.domain.common.Result
import com.testanymind.domain.model.Education
import com.testanymind.domain.model.WorkingExperience
import com.testanymind.domain.usecase.*
import com.testanymind.presentation.base.BaseViewModel
import com.testanymind.presentation.lifecycle.LiveEvent
import com.testanymind.presentation.lifecycle.LiveTrigger
import com.testanymind.presentation.lifecycle.MutableLiveEvent
import com.testanymind.presentation.lifecycle.MutableLiveTrigger
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

class AddEditWorkExperienceViewModel(
    private val getWorkingExperienceUseCase: GetWorkingExperienceUseCase,
    private val saveWorkingExperienceUseCase: SaveWorkingExperienceUseCase,
    private val updateWorkingExperienceUseCase: UpdateWorkingExperienceUseCase,
    private val deleteWorkingExperienceUseCase: DeleteWorkingExperienceUseCase
) : BaseViewModel() {

    private val _workExp = MutableLiveData<WorkingExperience>()
    val workExp: LiveData<WorkingExperience> = _workExp

    private val _finishActivity = MutableLiveTrigger()
    val finishActivity: LiveTrigger = _finishActivity

    private val _showConfirmationDiscard = MutableLiveTrigger()
    val showConfirmationDiscard: LiveTrigger = _showConfirmationDiscard

    fun getWorkExp(id: Int) {
        viewModelScope.launch {
            _dataLoading.postValue(true)
            when (val result = getWorkingExperienceUseCase.invoke(id)) {
                is Result.Success -> {
                    _dataLoading.postValue(false)
                    result.data.filterNotNull().collect { data ->
                        _workExp.value = data.toWorkingExperience()
                    }
                }
                is Result.Error -> {
                    _dataLoading.postValue(false)
                    _error.postValue(result.exception.message.orEmpty())
                }
            }
        }
    }

    fun save(data: WorkingExperience) {
        viewModelScope.launch {
            _dataLoading.postValue(true)
            when (val result = saveWorkingExperienceUseCase.invoke(data.toEntity())) {
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

    fun update(data: WorkingExperience) {
        viewModelScope.launch {
            _dataLoading.postValue(true)
            when (val result = updateWorkingExperienceUseCase.invoke(data.toEntity().apply { id = data._id })) {
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
            when (val result = deleteWorkingExperienceUseCase.invoke(id)) {
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
}