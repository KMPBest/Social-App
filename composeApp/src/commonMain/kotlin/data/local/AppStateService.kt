package data.local

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AppStateService : KoinComponent {

    private val appStateRepository by inject<AppStateRepository>()

    fun createNewAppStateInstance() = appStateRepository.createNewAppStateInstance()

    fun updateAppState(appStateModel: AppStateModel) =
        appStateRepository.updateAppState(appStateModel)


    fun getAppState() = appStateRepository.getAppState()
}