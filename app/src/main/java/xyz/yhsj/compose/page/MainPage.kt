package xyz.yhsj.compose.page

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.VectorAsset
import androidx.compose.ui.unit.dp
import xyz.yhsj.compose.widget.topBar

@Composable
fun MainPage() {
    val screenType = mutableStateOf(NavType.Home)
    Scaffold(
        topBar = { topBar(title = screenType.value.title) },
        bottomBar = { bottomNav(screenType) }
    ) {
        homeContent(screenType = screenType.value)
    }

}

@Composable
fun homeContent(screenType: NavType) {
    Crossfade(current = screenType, modifier = Modifier.fillMaxSize().padding(bottom = 50.dp)) {
        when (screenType) {
            NavType.Home -> HomePage()
            NavType.Notifications -> {
                Text(text = screenType.title)
            }
            NavType.Mine -> {
                Text(text = screenType.title)
            }
        }

    }
}

@Composable
fun bottomNav(homeState: MutableState<NavType>) {
    BottomNavigation {
        NavType.values().forEach {
            BottomNavigationItem(
                icon = { Icon(asset = it.icon) },
                selected = it == homeState.value,
                onClick = { homeState.value = it }
            )
        }


    }
}


enum class NavType(val title: String, val icon: VectorAsset) {
    Home("首页", Icons.Default.Home),
    Notifications("消息", Icons.Default.Notifications),
    Mine("我的", Icons.Default.Person),

}