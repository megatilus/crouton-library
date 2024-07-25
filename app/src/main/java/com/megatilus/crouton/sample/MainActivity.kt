package com.megatilus.crouton.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.megatilus.crouton.ColoredCrouton
import com.megatilus.crouton.sample.ui.theme.CroutonTheme
import com.megatilus.crouton.utils.CroutonType

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CroutonTheme {
                var show by remember {
                    mutableStateOf(false)
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black),
                    contentAlignment = Alignment.Center
                ) {
                    Button(onClick = {}) {
                        Text(text = "Show Crouton")
                    }
                }

                ColoredCrouton(
                    title = "Success",
                    message = "This is a success Crouton",
                    showCrouton = show,
                    onCroutonListener = {
                        show = it
                    },
                    croutonType = CroutonType.Success
                )
            }
        }
    }
}