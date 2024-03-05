package data.local.database

import kotlinx.coroutines.flow.Flow

interface AppDatabaseRepository {
    fun createNewAppDatabaseInstance()
    fun updateAppDatabase(appDatabase: AppDatabase)
    fun getAppDatabase(): Flow<AppDatabase?>
}