package xyz.yhsj.compose.routes

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigator(
    navController: NavHostController? = null,
    startDestination: String,
    routeNotFoundPage: @Composable (NavBackStackEntry) -> Unit = { DefView() },
    pages: List<RoutePage>
) {
    val tmpNav = navController ?: rememberNavController()

    NavHost(navController = tmpNav, startDestination = startDestination) {
        Navigator.init(navController = tmpNav)
        pages.forEach { page ->
            composable(page.route, content = page.page)
        }

        composable(Navigator.defPage, content = routeNotFoundPage)
    }
}

object Navigator {
    const val defPage = "page_404"

    private lateinit var navController: NavHostController

    fun init(navController: NavHostController) {
        Navigator.navController = navController
    }

    fun pop(): Boolean {
        return navController.popBackStack()

    }

    fun push(pageName: String): Boolean {
        return try {
            navController.navigate(pageName)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            navController.navigate(defPage)
            false
        }
    }


//
//    fun onBackPressed(): Boolean {
//        navController.
//        return false
//    }
}

