package xyz.yhsj.compose.routes

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry

data class RoutePage(val route: String, val page: @Composable (NavBackStackEntry) -> Unit)