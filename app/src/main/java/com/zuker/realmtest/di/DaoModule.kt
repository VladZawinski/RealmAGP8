package com.zuker.realmtest.di

import com.zuker.realmtest.data.dao.UserDao
import com.zuker.realmtest.data.dao.UserDaoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DaoModule {
    @Binds
    abstract fun bindUserDao(impl: UserDaoImpl): UserDao
}