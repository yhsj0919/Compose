package xyz.yhsj.compose

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.setContent
import xyz.yhsj.compose.page.MainPage
import xyz.yhsj.compose.theme.ComposeTheme
import xyz.yhsj.compose.utils.Navigation

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(Navigation)
        Navigation.setRootPage { MainPage() }
        setContent {
            ComposeTheme {
                Navigation.viewContent()
            }
        }
    }

    override fun onBackPressed() {
        if (!Navigation.onBackPressed()) {
            super.onBackPressed()
        }
    }
}


