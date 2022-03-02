package com.testanymind.presentation.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.testanymind.domain.common.Result
import com.testanymind.domain.entity.PersonalInfoEntity
import com.testanymind.domain.usecase.DeleteAllPersonalInfoUseCase
import com.testanymind.domain.usecase.GetPersonalInfoUseCase
import com.testanymind.domain.usecase.SavePersonalInfoUseCase
import com.testanymind.presentation.base.BaseViewModel
import com.testanymind.presentation.extension.isEmailValid
import com.testanymind.presentation.lifecycle.LiveEvent
import com.testanymind.presentation.lifecycle.LiveTrigger
import com.testanymind.presentation.lifecycle.MutableLiveEvent
import com.testanymind.presentation.lifecycle.MutableLiveTrigger
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

class PersonalInfoViewModel(
    private val getPersonalInfoUseCase: GetPersonalInfoUseCase,
    private val savePersonalInfoUseCase: SavePersonalInfoUseCase,
    private val deleteAllPersonalInfoUseCase: DeleteAllPersonalInfoUseCase,
) : BaseViewModel() {

    val name: MutableLiveData<String> = MutableLiveData("")
    val role: MutableLiveData<String> = MutableLiveData("")
    val mobile: MutableLiveData<String> = MutableLiveData("")
    val address: MutableLiveData<String> = MutableLiveData("")
    val email: MutableLiveData<String> = MutableLiveData("")

    private var initialData = PersonalInfoEntity(
        name = "",
        role = "",
        avatar = "",
        mobile = "",
        email = "",
        address = ""
    )

    private val _showErrorInvalidEmailFormat = MutableLiveEvent<Boolean>()
    val showErrorInvalidEmailFormat: LiveEvent<Boolean> = _showErrorInvalidEmailFormat

    private val _showConfirmationDiscard = MutableLiveTrigger()
    val showConfirmationDiscard: LiveTrigger = _showConfirmationDiscard

    private val _finishActivity = MutableLiveTrigger()
    val finishActivity: LiveTrigger = _finishActivity

    fun getPersonalInfo() {
        viewModelScope.launch {
            _dataLoading.postValue(true)
            when (val result = getPersonalInfoUseCase.invoke()) {
                is Result.Success -> {
                    _dataLoading.postValue(false)
                    result.data.filterNotNull().collect {
                        initialData = it
                        name.value = it.name
                        role.value = it.role
                        mobile.value = it.mobile
                        address.value = it.address
                        email.value = it.email
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

            if (email.value?.isEmailValid() == true || email.value.isNullOrEmpty()) {
                _showErrorInvalidEmailFormat.setEventValue(false)
                deleteAllPersonalInfoUseCase.invoke()

                when (val result = savePersonalInfoUseCase.invoke(getChangedPersonalInfoEntity())) {
                    is Result.Success -> {
                        _dataLoading.postValue(false)
                        _finishActivity.trigger()
                    }
                    is Result.Error -> {
                        _dataLoading.postValue(false)
                        _error.postValue(result.exception.message.orEmpty())
                    }
                }

            } else {
                _showErrorInvalidEmailFormat.setEventValue(true)
            }
        }
    }

    fun showConfirmationDiscard() {
        val isDataChanged = initialData != getChangedPersonalInfoEntity()
        if (isDataChanged) {
            _showConfirmationDiscard.trigger()
        } else {
            finishActivity()
        }
    }

    fun finishActivity() {
        _finishActivity.trigger()
    }

    private fun getChangedPersonalInfoEntity() = PersonalInfoEntity(
        name = name.value.orEmpty(),
        role = role.value.orEmpty(),
        avatar = "",
        mobile = mobile.value.orEmpty(),
        email = email.value.orEmpty(),
        address = address.value.orEmpty()
    )
}