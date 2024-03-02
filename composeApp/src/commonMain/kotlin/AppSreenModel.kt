import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import data.local.AppStateService
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AppSreenModel : ScreenModel, KoinComponent {
    private val appStateService by inject<AppStateService>()

    fun createNewAppStateInstance() = appStateService.createNewAppStateInstance()


    fun logAppState() {
        screenModelScope.launch {
            val appState = appStateService.getAppState().stateIn(screenModelScope)
            println("---------------------------------")
            println(appState)
        }
    }
}