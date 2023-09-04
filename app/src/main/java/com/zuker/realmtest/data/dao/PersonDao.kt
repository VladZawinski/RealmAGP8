package com.zuker.realmtest.data.dao

import com.zuker.realmtest.data.entity.Person
import io.realm.Realm

interface PersonDao {
    suspend fun createPerson(name: String, age: Long)
    suspend fun readPerson(id: String)
    suspend fun updatePerson(id: String, name: String, age: Long)
    suspend fun deletePerson(id: String)
    suspend fun deleteAllPerson()
    suspend fun getAllPerson(): List<Person>
}

class IPersonDao(
    private val realm: Realm
) : PersonDao {
    override suspend fun createPerson(name: String, age: Long) {
        realm.executeTransactionAsync { realm ->
            val person = realm.createObject(Person::class.java)
            person.id = System.currentTimeMillis().toString()
            person.name = name
            person.age = age
        }
    }

    override suspend fun readPerson(id: String) {
        realm.executeTransactionAsync { realm ->
            val person = realm.where(Person::class.java).equalTo("id", id).findFirst()
            println(person)
        }
    }

    override suspend fun updatePerson(id: String, name: String, age: Long) {
        realm.executeTransactionAsync { realm ->
            val person = realm.where(Person::class.java).equalTo("id", id).findFirst()
            person?.name = name
            person?.age = age
        }
        realm.close()
    }

    override suspend fun deletePerson(id: String) {
        realm.executeTransactionAsync { realm ->
            val person = realm.where(Person::class.java).equalTo("id", id).findFirst()
            person?.deleteFromRealm()
        }
    }

    override suspend fun deleteAllPerson() {
        realm.executeTransactionAsync { realm ->
            realm.deleteAll()
        }
    }

    override suspend fun getAllPerson(): List<Person> {
        var persons: List<Person>? = null
        realm.executeTransaction { realmInstance ->
            val results = realmInstance.where(Person::class.java).findAll()
            persons = realmInstance.copyFromRealm(results)
        }
        realm.close()
        return persons ?: emptyList()
    }
}