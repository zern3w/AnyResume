package com.testanymind.presentation.lifecycle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer

open class Event<out T>(private val content: T) {
    var hasBeenHandled = false
        private set

    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) null
        else {
            hasBeenHandled = true
            content
        }
    }

    fun peekContent(): T = content
}

class EventObserver<T>(private val onEventUnhandledContent: (T) -> Unit) : Observer<Event<T>> {
    override fun onChanged(event: Event<T>?) {
        event?.getContentIfNotHandled()?.let {
            onEventUnhandledContent(it)
        }
    }
}

abstract class LiveEvent<T> : LiveData<Event<T>> {
    companion object {
        val NOT_SET = Any()
    }

    constructor() : super()
    constructor(value: T) : super(Event(value))

    val eventValue: T?
        get() = value?.peekContent()
}

class MutableLiveEvent<T> : LiveEvent<T> {
    constructor() : super()
    constructor(value: T) : super(value)

    fun setEventValue(value: T) {
        setValue(Event(value))
    }

    fun postEventValue(value: T) {
        postValue(Event(value))
    }
}

class MediatorLiveEvent<T> : MediatorLiveData<Event<T>>() {
    fun setEventValue(value: T) {
        setValue(Event(value))
    }

    fun postEventValue(value: T) {
        postValue(Event(value))
    }
}

class Trigger {

}

class TriggerObserver(private val onEventUnhandledContent: () -> Unit) : Observer<Trigger> {
    override fun onChanged(event: Trigger?) {
        onEventUnhandledContent()
    }
}

abstract class LiveTrigger : LiveData<Nothing>()

class MutableLiveTrigger : LiveTrigger() {
    fun trigger() {
        value = null
    }

    fun postTrigger() {
        postValue(null)
    }
}


