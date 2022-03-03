package com.testanymind.presentation.feature.education

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.testanymind.domain.common.DataCenter
import com.testanymind.domain.common.Result
import com.testanymind.domain.model.Education
import com.testanymind.domain.usecase.*
import com.testanymind.presentation.base.BaseViewModel
import com.testanymind.presentation.lifecycle.LiveEvent
import com.testanymind.presentation.lifecycle.LiveTrigger
import com.testanymind.presentation.lifecycle.MutableLiveEvent
import com.testanymind.presentation.lifecycle.MutableLiveTrigger
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

class AddEditEducationViewModel(
    private val getEducationUseCase: GetEducationUseCase,
    private val saveEducationUseCase: SaveEducationUseCase,
    private val updateEducationUseCase: UpdateEducationUseCase,
    private val deleteEducationUseCase: DeleteEducationUseCase
) : BaseViewModel() {

    private val _education = MutableLiveData<Education>()
    val education: LiveData<Education> = _education

    private val _finishActivity = MutableLiveTrigger()
    val finishActivity: LiveTrigger = _finishActivity

    private val _showConfirmationDiscard = MutableLiveTrigger()
    val showConfirmationDiscard: LiveTrigger = _showConfirmationDiscard

    fun getEducation(id: Int) {
        viewModelScope.launch {
            _dataLoading.postValue(true)
            when (val result = getEducationUseCase.invoke(id)) {
                is Result.Success -> {
                    _dataLoading.postValue(false)
                    result.data.filterNotNull().collect { data ->
                        _education.value = data.toEducation()
                    }
                }
                is Result.Error -> {
                    _dataLoading.postValue(false)
                    _error.postValue(result.exception.message.orEmpty())
                }
            }
        }
    }

    fun save(education: Education) {
        viewModelScope.launch {
            _dataLoading.postValue(true)
            when (val result = saveEducationUseCase.invoke(education.toEntity())) {
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

    fun update(data: Education) {
        viewModelScope.launch {
            _dataLoading.postValue(true)
            when (val result = updateEducationUseCase.invoke(data.toEntity().apply { id = data._id })) {
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

    fun delete(educationId: Int) {
        viewModelScope.launch {
            _dataLoading.postValue(true)
            when (val result = deleteEducationUseCase.invoke(educationId)) {
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