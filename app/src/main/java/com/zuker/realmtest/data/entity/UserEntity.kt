package com.zuker.realmtest.data.entity

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

open class UserEntity() : RealmObject {
    @PrimaryKey
    var id: String = ""
    var name: String = ""
    var profile: ProfileEntity? = null

    constructor(id: String, name: String, profile: ProfileEntity?): this(){
        this.id = id
        this.name = name
        this.profile = profile
    }
}
