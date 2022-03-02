package com.testanymind.presentation.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.testanymind.domain.common.Result
import com.testanymind.domain.usecase.DeleteAllSkillsUseCase
import com.testanymind.domain.usecase.GetSkillsUseCase
import com.testanymind.domain.usecase.SaveSkillsUseCase
import com.testanymind.presentation.base.BaseViewModel
import com.testanymind.presentation.lifecycle.LiveEvent
import com.testanymind.presentation.lifecycle.MutableLiveEvent
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ProjectViewModel(
    private val getSkillsUseCase: GetSkillsUseCase,
    private val saveSkillsUserCase: SaveSkillsUseCase,
    private val deleteAllSkillsUseCase: DeleteAllSkillsUseCase,
) : BaseViewModel() {

    private val _initSkillsEvent = MutableLiveEvent<MutableList<String>>()
    val initSkillsEvent: LiveEvent<MutableList<String>> = _initSkillsEvent

    private val _skillList = MutableLiveData<MutableList<String>>(arrayListOf())
    val skillList: LiveData<MutableList<String>> = _skillList

    fun initSkills() {
        viewModelScope.launch {
            _dataLoading.postValue(true)
            when (val result = getSkillsUseCase.invoke()) {
                is Result.Success -> {
                    _dataLoading.postValue(false)
                    result.data.collect { list ->
                        val skills = list.map { it.skill }.toMutableList()
                        _skillList.value = skills
                        _initSkillsEvent.setEventValue(skills)
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
            skillList.value?.let { list ->
                deleteAllSkillsUseCase.invoke()

                when (val result = saveSkillsUserCase.invoke(list)) {
                    is Result.Success -> {
                        _dataLoading.postValue(false)
                    }
                    is Result.Error -> {
                        _dataLoading.postValue(false)
                        _error.postValue(result.exception.message.orEmpty())
                    }
                }
            }
        }
    }
}