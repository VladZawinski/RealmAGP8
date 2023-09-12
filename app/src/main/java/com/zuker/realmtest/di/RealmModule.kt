package com.zuker.realmtest.di

import android.content.Context
import com.zuker.realmtest.data.entity.ProfileEntity
import com.zuker.realmtest.data.entity.UserEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RealmModule {
    @Provides
    @Singleton
    fun provideRealm(
        @ApplicationContext context: Context,
    ): Realm {
        val realmConfig = RealmConfiguration.create(
            schema = setOf(
                UserEntity::class,
                ProfileEntity::class,
            ),
        )
        return Realm.open(realmConfig)
    }
}
