package com.kiko.knfc.data.common.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.kiko.knfc.ui.screens.destinations.AccountScreenDestination
import com.kiko.knfc.ui.screens.destinations.MainScreenDestination
import com.kiko.knfc.ui.screens.destinations.SettingsScreenDestination
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec

enum class BottomBarDestination(
    val direction: DirectionDestinationSpec,
    val icon: ImageVector
) {
    MainScreen(MainScreenDestination, Icons.Rounded.Home),
    Settings(SettingsScreenDestination, Icons.Rounded.Settings),
    Account(AccountScreenDestination, Icons.Rounded.Person),
}