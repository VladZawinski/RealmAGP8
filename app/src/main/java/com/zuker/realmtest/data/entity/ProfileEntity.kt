package com.zuker.realmtest.data.entity

import io.realm.kotlin.types.EmbeddedRealmObject

open class ProfileEntity(): EmbeddedRealmObject {
    var age: Long = 18
    var name: String = ""

    constructor(age: Long, name: String) : this() {
        this.age = age
        this.name = name
    }
}
