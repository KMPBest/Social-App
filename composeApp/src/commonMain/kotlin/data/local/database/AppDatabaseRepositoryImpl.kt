package data.local.database

import domain.models.AppDatabase
import domain.repositories.AppDatabaseRepository
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AppDatabaseRepositoryImpl : AppDatabaseRepository {
    private val configuration =
        RealmConfiguration.Builder(schema = setOf(AppDatabase::class)).name("composeapp.realm")
            .build()

    private val realm = Realm.open(configuration)

    override fun createNewAppDatabaseInstance() {
        realm.writeBlocking {
            this.delete(AppDatabase::class) // delete the previous state if it exists
            this.copyToRealm(AppDatabase(), UpdatePolicy.ALL)
        }
    }

    override fun updateAppDatabase(appDatabase: AppDatabase) {
        realm.writeBlocking {
            this.copyToRealm(appDatabase, UpdatePolicy.ALL)
        }
    }

    override fun getAppDatabase(): Flow<AppDatabase?> {
        return realm.query<AppDatabase>().first().asFlow().map { it.obj }
    }
}
