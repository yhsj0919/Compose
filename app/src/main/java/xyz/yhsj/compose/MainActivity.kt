package xyz.yhsj.compose

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.setContent
import xyz.yhsj.compose.routes.Routes
import xyz.yhsj.compose.routes.defView
import xyz.yhsj.compose.theme.ComposeTheme
import xyz.yhsj.compose.routes.navigator

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//
        setContent {
            ComposeTheme {
                navigator(
                    startDestination = Routes.MAIN,
                    pages = Routes.routes,
                    routeNotFoundPage = { defView() }
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


