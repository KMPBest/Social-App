package org.edward.app.presentations.screens.main.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.core.component.KoinComponent

class SettingsScreen : Screen, KoinComponent {

    @Preview
    @Composable
    override fun Content() {

        val screenModel = koinScreenModel<SettingsScreenModel>()
        val state by screenModel.uiState.collectAsState()

        LaunchedEffect(Unit) {
            screenModel.loadData()
        }

        Column(Modifier.fillMaxWidth()) {
            Header()
            SettingsList(state, screenModel)

        }

    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Preview
    @Composable
    fun Header() {
        val navigator = LocalNavigator.currentOrThrow

        CenterAlignedTopAppBar(title = {
            Text("Settings",
                fontWeight = FontWeight.W600,
                fontSize = TextUnit(18.0.toFloat(), TextUnitType.Sp),
            ) },
            navigationIcon = {
                IconButton(
                    modifier = Modifier.size(40.dp).padding(start = 16.dp),
                    onClick = {
                        navigator.pop()
                    }) {
                    Icon(
                        imageVector = Icons.Default.ChevronLeft,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            },
        )
    }

    @Preview
    @Composable
    fun SettingsList(state: SettingsScreenModel.SettingsState, screenModel: SettingsScreenModel) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { screenModel.changeTheme()  }
                .padding(vertical = 14.dp, horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Change theme",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.weight(1f)
            )
            Switch(
                checked = state.isDarkTheme,
                onCheckedChange = {},
                enabled = false
            )
        }
    }
}

