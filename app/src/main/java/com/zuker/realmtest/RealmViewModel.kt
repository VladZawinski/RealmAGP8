package com.zuker.realmtest

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zuker.realmtest.data.dao.UserDao
import com.zuker.realmtest.data.entity.UserEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import io.realm.kotlin.notifications.InitialResults
import io.realm.kotlin.notifications.UpdatedResults
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class RealmViewModel @Inject constructor(
    private val dao: UserDao,
) : ViewModel() {
    private val _state = MutableStateFlow(RealmState())
    val state: StateFlow<RealmState> = _state
    init {
        viewModelScope.launch {
            dao.stream().collectLatest {
                when (it) {
                    is UpdatedResults -> {
                        Log.d("RealmViewModel", "Changed list: ${it.list}")
                    }
                    else -> {}
                }
            }
        }
        getAllUser()
    }
    fun createUser() = viewModelScope.launch {
        val user = UserEntity().apply {
            val uuid = UUID.randomUUID().toString()
            name = "ZUKER $uuid"
            id = uuid
        }
        dao.insert(user)
        getAllUser()
    }
    fun getAllUser() = viewModelScope.launch {
        dao.findAll().let {
            Log.d("RealmViewModel", "getAllUser: $it")
            _state.value = state.value.copy(persons = it)
        }
    }
}

data class RealmState(
    val persons: List<UserEntity> = emptyList(),
)
