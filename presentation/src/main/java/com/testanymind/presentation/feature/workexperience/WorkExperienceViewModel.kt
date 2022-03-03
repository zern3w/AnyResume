package com.testanymind.presentation.feature.workexperience

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.testanymind.domain.common.DataCenter
import com.testanymind.domain.common.Result
import com.testanymind.domain.model.WorkingExperience
import com.testanymind.domain.usecase.*
import com.testanymind.presentation.base.BaseViewModel
import com.testanymind.presentation.lifecycle.LiveTrigger
import com.testanymind.presentation.lifecycle.MutableLiveTrigger
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class WorkExperienceViewModel(
    private val getAllWorkingExperienceUseCase: GetAllWorkingExperienceUseCase,
    private val saveWorkingExperienceUseCase: SaveWorkingExperienceUseCase,
    private val deleteAllWorkingExperienceUseCase: DeleteAllWorkingExperienceUseCase,
) : BaseViewModel() {

    private val _workingExpList = MutableLiveData<List<WorkingExperience>>(arrayListOf())
    val workingExpList: LiveData<List<WorkingExperience>> = _workingExpList

    private val _finishActivity = MutableLiveTrigger()
    val finishActivity: LiveTrigger = _finishActivity

    private val _showConfirmationDiscard = MutableLiveTrigger()
    val showConfirmationDiscard: LiveTrigger = _showConfirmationDiscard

    private val _showAddEditUi = MutableLiveTrigger()
    val showAddEditUi: LiveTrigger = _showAddEditUi

    fun getWorkingExpList() {
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

//    fun save() {
//        viewModelScope.launch {
//            _dataLoading.postValue(true)
//            deleteAllWorkingExperienceUseCase.invoke()
//
//            val list = DataCenter.getDemoWorkingExperienceList().map { it.toEntity() }
//
//            when (val result = saveWorkingExperienceUseCase.invoke(list)) {
//                is Result.Success -> {
//                    _dataLoading.postValue(false)
//                    _finishActivity.trigger()
//                }
//                is Result.Error -> {
//                    _dataLoading.postValue(false)
//                    _error.postValue(result.exception.message.orEmpty())
//                }
//            }
//        }
//    }

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