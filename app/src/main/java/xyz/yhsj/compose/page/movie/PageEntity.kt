package xyz.yhsj.compose.page.movie

data class PageEntity<T>(
    var thisPage: Int = 0,
    var totalPage: Int = 0,

    var data: ArrayList<T> = ArrayList()


) {
}