package com.route.data.dataSourceImpl

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.google.gson.Gson
import com.route.data.dataSourcesContract.AuthOfflineDataSource
import com.route.data.dataStore.UserDataStore
import com.route.domain.model.AuthData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthOfflineDataSourceImpl @Inject constructor(
    @UserDataStore private val userDataStore: DataStore<Preferences>,
    private val gson:Gson
):
    AuthOfflineDataSource {

    private val loginPreferencesKey = stringPreferencesKey("user")

    override suspend fun saveUser(response: AuthData) {
        userDataStore.edit {settings->
            settings[loginPreferencesKey] = gson.toJson(response)
        }
    }

    override fun retrieveUser(): Flow<AuthData?> {
        return userDataStore.data.map { data->
            val loginResponseJson = data[loginPreferencesKey]
            gson.fromJson(loginResponseJson,AuthData::class.java)
        }
    }
}