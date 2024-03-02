package data.local

import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AppStateRepository {
    private val configuration =
        RealmConfiguration.Builder(schema = setOf(AppStateModel::class)).name("composeapp.realm")
            .build()

    private val realm = Realm.open(configuration)

    /**
     * Create a new app state instance in the database
     * @return Unit
     */
    fun createNewAppStateInstance() {
        realm.writeBlocking {
            this.delete(AppStateModel::class) // delete the previous state if it exists
            this.copyToRealm(AppStateModel(), UpdatePolicy.ALL)
        }
    }

    /**
     * Update the app state in the database
     * @param appStateModel the app state to update
     * @return Unit
     */
    fun updateAppState(appStateModel: AppStateModel) {
        realm.writeBlocking {
            this.copyToRealm(appStateModel, UpdatePolicy.ALL)
        }
    }

    /**
     * Get the app state from the database
     * @return Flow<SingleQueryChange<AppStateModel>> the app state as a flow to observe changes
     */
    fun getAppState(): Flow<AppStateModel?> {
        return realm.query<AppStateModel>().first().asFlow().map { it.obj }
    }
}
