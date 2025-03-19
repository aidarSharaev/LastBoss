package com.horizonguard.projectflow.ui.app_comp

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
internal fun AppUi(
    component: AppComponent,
    modifier: Modifier = Modifier,
) {


    Text("appui", color = Color.Yellow)
    Scaffold() {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 128.dp)
        ) {
            items(30) {
                Text("asd")
            }
        }
    }
}

/*private fun*/
