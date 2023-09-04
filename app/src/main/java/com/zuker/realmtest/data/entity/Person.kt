package com.zuker.realmtest.data.entity

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Person(
    @PrimaryKey
    var id: String = "",
    var name: String = "",
    var age: Long = 0L
) : RealmObject()