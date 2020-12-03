package xyz.yhsj.compose.page

import android.widget.TextView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus
import androidx.compose.ui.focus.ExperimentalFocus
import androidx.compose.ui.focusObserver
import androidx.compose.ui.input.key.ExperimentalKeyInput
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import xyz.yhsj.compose.R

@OptIn(ExperimentalKeyInput::class, ExperimentalFocus::class)
@Composable
fun MinePage() {

    Column {


        AndroidTextView("测试文本")
        AndroidTextView("测试文本")
        Button(
            modifier = Modifier.focus().focusObserver { println(it.name) },
            onClick = { println("按键") }) {
            Text(text = "按钮")
        }
    }


}

@Composable
fun AndroidTextView(title: String) {

    val context = AmbientContext.current

    val androidTextView = remember {
        TextView(context).apply {
            isFocusable = true
            isFocusableInTouchMode = true
            text = title
            setTextColor(ContextCompat.getColor(context, R.color.black))

        }
    }

    AndroidView({ androidTextView }, modifier = Modifier.padding(8.dp)) {
        it.setOnFocusChangeListener { v, hasFocus ->

            println(">>>>>>>>>>>>>>>>>>>")

            if (hasFocus) {
                (v as TextView).setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.teal_200
                    )
                )
            } else {
                (v as TextView).setTextColor(ContextCompat.getColor(context, R.color.black))
            }
        }

        it.setOnClickListener { println(">?????????????????????") }
    }
}