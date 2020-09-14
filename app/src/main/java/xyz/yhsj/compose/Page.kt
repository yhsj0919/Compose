//package xyz.yhsj.compose
//
//import androidx.compose.runtime.Composable
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.MainScope
//import kotlinx.coroutines.cancel
//import xyz.yhsj.compose.utils.Navigation
//
//abstract class Page : CoroutineScope by MainScope() {
//
//
//    @Composable
//    abstract fun viewContent()
//
//
//    open fun push(page: Page): Boolean {
//        return Navigation.push(page)
//    }
//
//    open fun pop(): Boolean {
//        return Navigation.pop()
//    }
//
//    open fun setRootPage(page: Page) {
//        Navigation.setRootPage(page)
//    }
//
//    open fun destory() {
//        cancel()
//    }
//
//    open fun onBackPressed(fromTop: Boolean): Boolean {
//        return false
//    }
//
//}