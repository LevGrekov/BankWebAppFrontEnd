package ru.levgrekov.bank.ui

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


abstract class ViewModel<State: Any, Event>(initState: State) {

    private val _viewStates = MutableStateFlow(initState)

    protected var viewState: State
        get() = _viewStates.value
        set(value) {
            _viewStates.value = value
        }

    fun viewStates() = _viewStates.asStateFlow()

    abstract fun obtainEvent(viewEvent: Event)

    private val viewModelJob = Job()
    protected val viewModelScope = CoroutineScope(Dispatchers.Default + viewModelJob)

    fun onCleared()  = viewModelJob.cancel()



    protected fun debugPrint(viewEvent: Event) = println("Error  ${viewState::class.simpleName} ${viewEvent!!::class.simpleName}")

}