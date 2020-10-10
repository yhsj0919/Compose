package xyz.yhsj.compose.widget

import androidx.compose.animation.animatedFloat
import androidx.compose.animation.core.ExponentialDecay
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Layout
import androidx.compose.ui.Modifier
import androidx.compose.ui.WithConstraints
import androidx.compose.ui.gesture.scrollorientationlocking.Orientation
import androidx.compose.ui.layout.id
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.util.fastForEach
import androidx.compose.ui.util.fastMaxBy
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

/**
 * 代码来自 https://github.com/Modificator/ComposeDemo
 * 新加了回调
 * 默认当前页
 */
@Composable
fun <T> ViewPager(
    items: List<T>,
    onPageChanged: ((Int) -> Unit)? = null,
    current: Int = 0,
    animateTo: Boolean = true,
    modifier: Modifier = Modifier.fillMaxSize(),
    pageCreator: @Composable (position: Int, item: T) -> Unit
) {
    WithConstraints() {
        val offset = animatedFloat(initVal = 0f)
        val position = mutableStateOf(0)
        val mCurrent = mutableStateOf(current)
        var width = constraints.maxWidth.toFloat()

        val draggable = modifier.draggable(
            orientation = Orientation.Horizontal,
            onDragStarted = {
            },
            onDragStopped = { velocity ->
                val target = ExponentialDecay().getTarget(offset.value, velocity)
                val pageOffset = target + position.value * width

                if (abs(pageOffset) > width / 2.5f) {
                    if (pageOffset > 0) {

                        position.value--
                        mCurrent.value--

                    } else {
                        position.value++
                        mCurrent.value++

                    }

                    if (position.value >= 0 && position.value < items.size) {
                        onPageChanged?.invoke(position.value)
                    }
                }
                position.value = min(position.value, items.size - 1)
                position.value = max(position.value, 0)

                mCurrent.value = position.value
//                    offset.animateTo(target+scrollOffset)
                offset.animateTo(-position.value * width)
            },
            onDrag = { fl ->
                if (position.value == items.size - 1 && fl < 0) {
//                    0f
                } else if (position.value == 0 && fl > 0) {
//                    0f
                } else {
                    val old = offset.value
                    offset.snapTo(offset.value + fl)
                    offset.value - old
                }
            },
            enabled = true
        )

        Box(draggable) {
            Layout(
                children = {

//                    Log.e("绘制控件", ">>>$width>>>>>>")
                    items.forEachIndexed { index, t ->
                        Box(Modifier.layoutId(index)) {
                            pageCreator(index, t)
                        }
                    }
                },
                measureBlock = { list, constraints ->
                    width = constraints.maxWidth.toFloat()
//                    Log.e("宽度重新获取", ">>>$width>>>>>>")

                    //代码切换一下页面
                    if (position.value != mCurrent.value && mCurrent.value < items.size) {
//                        Log.e("自动切换", ">>>${position.value}>>${mCurrent.value}>>>>")
                        position.value = mCurrent.value
                        if (animateTo) {
                            offset.animateTo(-position.value * width)
                        } else {
                            offset.snapTo(-position.value * width)
                        }
                        onPageChanged?.invoke(position.value)
                    }

                    val placeables = list.map { it.measure(constraints) to it.id }
                    val height = placeables.fastMaxBy { it.first.height }?.first?.height ?: 0

                    layout(constraints.maxWidth, height) {
                        placeables.fastForEach { (placeable, tag) ->
                            if (tag is Int) {
                                placeable.place(
                                    x = constraints.maxWidth * tag + offset.value.toInt(),
                                    y = 0
                                )
                            }
                        }
                    }

                })
        }
    }
}