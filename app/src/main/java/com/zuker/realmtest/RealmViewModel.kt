package com.zuker.realmtest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zuker.realmtest.data.dao.IPersonDao
import com.zuker.realmtest.data.entity.Person
import io.realm.Realm
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class RealmViewModel() : ViewModel() {
    private val personDao = IPersonDao(
        Realm.getDefaultInstance(),
    )
    init {
        getAllUser()
    }
    val state = MutableStateFlow(RealmState())
    fun createUser() = viewModelScope.launch {
        Person(id = Random.nextInt().toString(), name = "zuker", age = 18L).let {
            personDao.createPerson(it.name, it.age)
        }
        getAllUser()
    }
    fun getAllUser() = viewModelScope.launch {
        personDao.getAllPerson().let {
            state.value = state.value.copy(persons = it)
        }
    }
}

data class RealmState(
    val persons: List<Person> = emptyList(),
)
