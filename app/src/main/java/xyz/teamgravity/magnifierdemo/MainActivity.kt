package xyz.teamgravity.magnifierdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.MagnifierStyle
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.magnifier
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import xyz.teamgravity.magnifierdemo.ui.theme.MagnifierDemoTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MagnifierDemoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var offset by remember { mutableStateOf(Offset.Unspecified) }

                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxSize()
                            .pointerInput(Unit) {
                                detectDragGestures { change, _ ->
                                    offset = change.position
                                }
                            }
                            .magnifier(
                                sourceCenter = {
                                    offset
                                },
                                magnifierCenter = {
                                    offset - Offset(0F, 200F)
                                },
                                style = MagnifierStyle(
                                    size = DpSize(
                                        width = 100.dp,
                                        height = 100.dp
                                    ),
                                    cornerRadius = 100.dp
                                )
                            )
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.tupac),
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
}