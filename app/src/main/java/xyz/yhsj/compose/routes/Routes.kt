package xyz.yhsj.compose.routes

import xyz.yhsj.compose.page.MainPage
import xyz.yhsj.compose.page.movie.MoviePage
import xyz.yhsj.compose.page.simple.*

object Routes {
    const val MAIN = "Main"
    const val TEXT = "text"
    const val BUTTON = "button"
    const val IMAGE = "Image"
    const val LISTVIEW = "ListView"
    const val GRIDVIEW = "GridView"
    const val DIALOG = "Dialog"
    const val VIEWPAGER = "ViewPager"
    const val OTHER = "Other"
    const val MOVIE = "Movie"


    val routes = arrayListOf(
        RoutePage(route = MAIN) {
            MainPage()
        },
        RoutePage(route = TEXT) {
            TextPage()
        },
        RoutePage(route = BUTTON) {
            ButtonPage()
        },
        RoutePage(route = IMAGE) {
            ImagePage()
        },
        RoutePage(route = LISTVIEW) {
            ListViewPage()
        },
        RoutePage(route = GRIDVIEW) {
            GridViewPage()
        },
        RoutePage(route = DIALOG) {
            DialogPage()
        },
        RoutePage(route = VIEWPAGER) {
            ViewPagerPage()
        },
        RoutePage(route = OTHER) {
            OtherWidgetPage()
        },
        RoutePage(route = MOVIE) {
            MoviePage()
        }

    )
}