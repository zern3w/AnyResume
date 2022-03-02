package com.testanymind.presentation.feature.education

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.testanymind.domain.common.DataCenter
import com.testanymind.domain.common.Result
import com.testanymind.domain.model.Education
import com.testanymind.domain.usecase.DeleteAllEducationUseCase
import com.testanymind.domain.usecase.GetEducationUseCase
import com.testanymind.domain.usecase.SaveEducationUseCase
import com.testanymind.presentation.base.BaseViewModel
import com.testanymind.presentation.lifecycle.LiveTrigger
import com.testanymind.presentation.lifecycle.MutableLiveTrigger
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class EducationViewModel(
    private val getEducationUseCase: GetEducationUseCase,
    private val saveEducationUseCase: SaveEducationUseCase,
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

    fun getEducations() {
        viewModelScope.launch {
            _dataLoading.postValue(true)
            when (val result = getEducationUseCase.invoke()) {
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

    fun save() {
        viewModelScope.launch {
            _dataLoading.postValue(true)
            deleteAllEducationUseCase.invoke()

            val list = DataCenter.getDemoEducationList().map { it.toEntity() }

            when (val result = saveEducationUseCase.invoke(list)) {
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