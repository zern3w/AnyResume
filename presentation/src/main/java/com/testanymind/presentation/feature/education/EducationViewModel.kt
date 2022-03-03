package com.testanymind.presentation.feature.education

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.testanymind.domain.common.DataCenter
import com.testanymind.domain.common.Result
import com.testanymind.domain.model.Education
import com.testanymind.domain.usecase.DeleteAllEducationUseCase
import com.testanymind.domain.usecase.GetAllEducationUseCase
import com.testanymind.domain.usecase.SaveAllEducationUseCase
import com.testanymind.presentation.base.BaseViewModel
import com.testanymind.presentation.lifecycle.LiveEvent
import com.testanymind.presentation.lifecycle.LiveTrigger
import com.testanymind.presentation.lifecycle.MutableLiveEvent
import com.testanymind.presentation.lifecycle.MutableLiveTrigger
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

class EducationViewModel(
    private val getAllEducationUseCase: GetAllEducationUseCase,
    private val saveAllEducationUseCase: SaveAllEducationUseCase,
    private val deleteAllEducationUseCase: DeleteAllEducationUseCase
) : BaseViewModel() {

    private val _educationList = MutableLiveData<List<Education>>(arrayListOf())
    val educationList: LiveData<List<Education>> = _educationList

    private val _finishActivity = MutableLiveTrigger()
    val finishActivity: LiveTrigger = _finishActivity

    private val _showConfirmationDiscard = MutableLiveTrigger()
    val showConfirmationDiscard: LiveTrigger = _showConfirmationDiscard

    private val _showAddEditUi = MutableLiveTrigger()
    val showAddEditUi: LiveTrigger = _showAddEditUi

    private val _showOrHideEmptyState = MutableLiveEvent<Boolean>()
    val showOrHideEmptyState: LiveEvent<Boolean> = _showOrHideEmptyState

    fun getEducationList() {
        viewModelScope.launch {
            _dataLoading.postValue(true)
            when (val result = getAllEducationUseCase.invoke()) {
                is Result.Success -> {
                    _dataLoading.postValue(false)
                    result.data.collect { list ->
                        val educationList = list.map { it.toEducation() }
                        _educationList.value = educationList
                        _showOrHideEmptyState.setEventValue(educationList.isEmpty())
                    }
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