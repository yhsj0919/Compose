package xyz.yhsj.compose

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.setContent
import xyz.yhsj.compose.routes.DefView
import xyz.yhsj.compose.routes.Navigator
import xyz.yhsj.compose.routes.Routes
import xyz.yhsj.compose.theme.ComposeTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//
        setContent {
            ComposeTheme {
                Navigator(
                    startDestination = Routes.MAIN,
                    pages = Routes.routes,
                    routeNotFoundPage = { DefView() }
                )
            }
        }
    }

//    override fun onBackPressed() {
//        if (!Navigation.onBackPressed()) {
//            super.onBackPressed()
//        }
//    }
}


