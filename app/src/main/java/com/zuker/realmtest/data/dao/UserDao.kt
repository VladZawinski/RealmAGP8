package com.zuker.realmtest.data.dao

import com.zuker.realmtest.data.entity.UserEntity
import io.realm.kotlin.Realm
import javax.inject.Inject
import kotlin.reflect.KClass

interface UserDao : RealmDao<UserEntity>

class UserDaoImpl @Inject constructor(
    r: Realm,
) : UserDao {
    override val realm: Realm = r
    override val clazz: KClass<UserEntity> = UserEntity::class
}
