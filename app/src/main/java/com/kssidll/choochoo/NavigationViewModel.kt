package com.kssidll.choochoo

import android.os.Bundle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor(): ViewModel(){
    private val _action = MutableStateFlow<String?>(null)
    val action: StateFlow<String?> = _action
    var extras: Bundle? = Bundle.EMPTY


    fun setAction(action: String?, extras: Bundle?) {
        _action.value = action
        this.extras = extras
    }
}