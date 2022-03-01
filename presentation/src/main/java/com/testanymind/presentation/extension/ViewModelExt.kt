package com.testanymind.presentation.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.testanymind.presentation.lifecycle.EventObserver
import com.testanymind.presentation.lifecycle.LiveEvent
import com.testanymind.presentation.lifecycle.LiveTrigger
import com.testanymind.presentation.lifecycle.TriggerObserver

fun <T> LifecycleOwner.observe(liveData: LiveData<T>, action: (t: T) -> Unit) {
    liveData.observe(this, Observer { it?.let { t -> action(t) } })
}

fun <T> LifecycleOwner.observe(liveData: MutableLiveData<T>, action: (t: T) -> Unit) {
    liveData.observe(this, Observer { it?.let { t -> action(t) } })
}

fun <T> LifecycleOwner.observe(liveEventData: LiveEvent<T>, action: (t: T) -> Unit) {
    liveEventData.observe(this, EventObserver { it?.let { t -> action(t) } })
}

fun <T> LifecycleOwner.observeEvent(liveEventData: LiveEvent<T>, action: (t: T) -> Unit) {
    liveEventData.observe(this, EventObserver { it.let { t -> action(t) } })
}

fun LifecycleOwner.observeTrigger(liveEventData: LiveTrigger, action: () -> Unit) {
    liveEventData.observe(this, TriggerObserver { action() })
}