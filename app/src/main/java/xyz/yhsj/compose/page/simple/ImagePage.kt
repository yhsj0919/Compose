package xyz.yhsj.compose.page.simple

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.foundation.layout.preferredWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import xyz.yhsj.compose.R
import xyz.yhsj.compose.routes.Navigator

@Composable
fun ImagePage() {

    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "Image") },
            elevation = 8.dp,
            navigationIcon = { IconButton(onClick = { Navigator.pop() }) { Icon(imageVector = Icons.Default.ArrowBack) } }
        )
    }) {
        ScrollableColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(8.dp).fillMaxWidth()
        ) {
            Image(
                bitmap = imageResource(R.drawable.image_1),
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .preferredWidth(100.dp)
                    .preferredHeight(100.dp)
                    .clip(CircleShape)
            )
            Image(

                bitmap = imageResource(R.drawable.image_1),
                modifier = Modifier.clip(RoundedCornerShape(30.dp)).preferredWidth(150.dp)

            )
            Image(
                bitmap = imageResource(R.drawable.image_1),
                modifier = Modifier.clip(RoundedCornerShape(50.dp, 0.dp, 50.dp, 0.dp))
                    .preferredWidth(150.dp)

            )
            Image(
                bitmap = imageResource(R.drawable.image_1),
                modifier = Modifier.clip(CutCornerShape(20.dp)).preferredWidth(150.dp)

            )
            Image(
                bitmap = imageResource(R.drawable.image_1),
                modifier = Modifier
                    .clip(RoundedCornerShape(50.dp))
                    .preferredWidth(150.dp)
            )

            Image(
                imageVector = vectorResource(id = R.drawable.ic_launcher_background),
            )

        }


    }

}