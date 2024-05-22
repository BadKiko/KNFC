package com.kiko.knfc.ui.fragments.navbar

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kiko.knfc.data.common.navigation.BottomBarDestination
import com.kiko.knfc.ui.screens.NavGraphs
import com.kiko.knfc.ui.screens.appCurrentDestinationAsState
import com.kiko.knfc.ui.screens.startAppDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun MainNavBar(
    navController: NavController,
    navigator: DestinationsNavigator
) {
    val currentDestination = navController.appCurrentDestinationAsState().value
        ?: NavGraphs.root.startAppDestination

    NavigationBar(
        modifier = Modifier
            .padding(16.dp)
            .clip(RoundedCornerShape(24.dp))
    ) {
        BottomBarDestination.entries.forEach {
            NavigationBarItem(
                selected = currentDestination == it.direction,
                onClick = { navigator.navigate(it.direction) },
                icon = {
                    Icon(
                        imageVector = it.icon,
                        contentDescription = "Home"
                    )
                })
        }
    }
}