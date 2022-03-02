package com.testanymind.presentation.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.testanymind.domain.common.Result
import com.testanymind.domain.usecase.GetSkillsUseCase
import com.testanymind.domain.usecase.SaveSkillsUseCase
import com.testanymind.presentation.base.BaseViewModel
import com.testanymind.presentation.lifecycle.LiveEvent
import com.testanymind.presentation.lifecycle.MutableLiveEvent
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SkillViewModel(
    private val getSkillsUseCase: GetSkillsUseCase,
    private val saveSkillsUserCase: SaveSkillsUseCase,
) : BaseViewModel() {

    private val _initSkillsEvent = MutableLiveEvent<MutableList<String>>()
    val initSkillsEvent: LiveEvent<MutableList<String>> = _initSkillsEvent

    private val _addSkillEvent = MutableLiveEvent<String>()
    val addSkillEvent: LiveEvent<String> = _addSkillEvent

    private val _skillList = MutableLiveData<MutableList<String>>(arrayListOf())
    val skillList: LiveData<MutableList<String>> = _skillList

    private val _hasSkill = MutableLiveData<Boolean>()
    val hasSkill: LiveData<Boolean> = _hasSkill

    fun isContainSkill(skill: String): Boolean {
        return if (!isSkillsEmpty()) {
            _skillList.value?.find {
                it.equals(skill, ignoreCase = true)
            } != null
        } else {
            false
        }
    }

    fun removeLastSkill() {
        if (!isSkillsEmpty()) {
            _skillList.value = _skillList.value?.apply {
                removeAt(this.size - 1)
            }
        }
    }

    fun removeSkill(skill: String) {
        if (!isSkillsEmpty()) {
            _skillList.value = _skillList.value?.apply {
                remove(skill)
            }
        }
    }

    fun addSkill(skill: String) {
        _skillList.value = _skillList.value?.apply {
            add(skill)
        }
        _addSkillEvent.setEventValue(skill)
    }

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

    fun getSkillsSize(): Int {
        return _skillList.value?.size ?: 0
    }

    fun isSkillsEmpty(): Boolean {
        return _skillList.value.isNullOrEmpty()
    }

    fun save() {
        viewModelScope.launch {
            _dataLoading.postValue(true)
            skillList.value?.let { list ->
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