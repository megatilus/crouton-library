package com.megatilus.crouton

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.megatilus.crouton.utils.CroutonPosition
import com.megatilus.crouton.utils.CroutonType
import com.megatilus.crouton.utils.CroutonDuration
import com.megatilus.crouton.utils.CroutonGravity
import com.megatilus.crouton.utils.CroutonResources
import kotlinx.coroutines.delay

/**
 * Displays a Light Crouton based on the specified type. See [croutonType].
 * For Dark Crouton, use [DarkCrouton].
 *
 * @param title The title of the Crouton.
 * @param showCrouton A Boolean to control the visibility of the Crouton.
 * @param onCroutonListener A listener triggered after the [duration] has elapsed. Use it to perform actions after the Crouton is no longer visible.
 * @param message The message of the Crouton (optional).
 * @param croutonType The type of Crouton, which may influence its appearance. See [CroutonType] for more information.
 * @param croutonGravity The position of the Crouton on the screen. Use values from [CroutonGravity].
 * @param duration The display duration of the Crouton. Refer to [CroutonDuration] for pre-configured durations or use a custom duration.
 * @param showIcon A Boolean indicating whether to display an icon within the Crouton. Defaults to **true**.
 * @param showBorder A Boolean indicating whether to display a border around the Crouton. Defaults to **true**.
 * @param enterTransition The enter animation for the Crouton. By default, it uses an animation based on the [croutonGravity].
 * @param exitTransition The exit animation for the Crouton. By default, it uses an animation based on the [croutonGravity].
 */
@Composable
fun LightCrouton(
    title: String,
    showCrouton: Boolean,
    onCroutonListener: (Boolean) -> Unit,
    message: String? = null,
    croutonType: CroutonType,
    croutonGravity: CroutonGravity = CroutonGravity.BOTTOM,
    duration: Long = CroutonDuration.SHORT,
    showIcon: Boolean = true,
    showBorder: Boolean = true,
    enterTransition: EnterTransition = CroutonPosition.getEnterTransition(croutonGravity),
    exitTransition: ExitTransition = CroutonPosition.getExitTransition(croutonGravity)
) {
    LaunchedEffect(key1 = showCrouton) {
        if (showCrouton) {
            delay(duration)
            onCroutonListener(false)
        }
    }

    AnimatedVisibility(
        visible = showCrouton,
        enter = enterTransition,
        exit = exitTransition,
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .safeContentPadding(),
            contentAlignment = CroutonPosition.getAlignmentOfCrouton(croutonGravity)
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .offset(y = CroutonPosition.getOffsetY(croutonGravity))
                    .background(CroutonResources.getLightColor(croutonType), RoundedCornerShape(14.dp))
                    .then(
                        if (showBorder) Modifier.border(
                            width = 1.dp,
                            color = CroutonResources.getPrimaryColor(croutonType),
                            shape = RoundedCornerShape(14.dp))
                        else Modifier)
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (showIcon) {
                    Icon(
                        painter = painterResource(CroutonResources.getIcon(croutonType)),
                        contentDescription = null,
                        tint = CroutonResources.getPrimaryColor(croutonType),
                        modifier = Modifier.size(24.dp)
                    )
                }

                Column(verticalArrangement = Arrangement.spacedBy(if (message.isNullOrEmpty()) 0.dp else 2.dp)) {
                    Text(text = title,
                        maxLines = 1,
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF0C0C0C)
                        )
                    )
                    if (!message.isNullOrEmpty()) {
                        Text(text = message,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color(0xFF0C0C0C),
                            )
                        )
                    }
                }
            }
        }
    }
}