package com.megatilus.crouton

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.megatilus.crouton.utils.CroutonDuration
import com.megatilus.crouton.utils.CroutonGravity
import com.megatilus.crouton.utils.CroutonPosition
import kotlinx.coroutines.delay

/**
 * Use this composable to customize your Crouton.
 *
 * @param title The title of the Crouton.
 * @param showCrouton A Boolean to control the visibility of the Crouton.
 * @param onCroutonListener A listener triggered after the [duration] has elapsed. Use it to perform actions after the Crouton is no longer visible. If [autoDismiss] is **false**, this listener will not be called.
 * @param message The message of the Crouton (optional).
 * @param titleStyle The style to be applied to the title (optional).
 * @param messageStyle The style to be applied to the message (optional).
 * @param croutonGravity The position of the Crouton on the screen. Use values from [CroutonGravity].
 * @param duration The display duration of the Crouton. Refer to [CroutonDuration] for pre-configured durations or use a custom duration.
 * @param autoDismiss If **true**, the Crouton will automatically dismiss after the duration. If **false**, the Crouton will remain on the screen until manually dismissed.
 * @param fillMaxWidth If **true**, the Crouton will occupy the full width of the screen.
 * @param sticky If **true**, the Crouton will be fixed at the top or bottom of the screen.
 * @param border The border stroke of the Crouton (optional).
 * @param shape The shape of the Crouton. Defaults to a rounded corner shape with a radius of 14.dp.
 * @param backgroundColor The background color of the Crouton. Defaults to White.
 * @param leadingContent Optional composable content to display on the left side of the Crouton.
 * @param leadingSpace The space between the leading content and the text content.
 * @param trailingContent Optional composable content to display on the right side of the Crouton.
 * @param trailingSpace The space between the trailing content and the text content.
 * @param enterTransition The enter animation for the Crouton. By default, it uses an animation based on the [croutonGravity].
 * @param exitTransition The exit animation for the Crouton. By default, it uses an animation based on the [croutonGravity].
 */
@Composable
fun CustomCrouton(
    title: String,
    showCrouton: Boolean,
    onCroutonListener: (Boolean) -> Unit,
    message: String? = null,
    titleStyle: TextStyle? = null,
    messageStyle: TextStyle? = null,
    croutonGravity: CroutonGravity = CroutonGravity.BOTTOM,
    duration: Long = CroutonDuration.SHORT,
    autoDismiss: Boolean = true,
    fillMaxWidth: Boolean = false,
    sticky: Boolean = false,
    border: BorderStroke? = null,
    shape: RoundedCornerShape = RoundedCornerShape(14.dp),
    backgroundColor: Color = Color.White,
    leadingContent: (@Composable () -> Unit)? = null,
    leadingSpace: Dp = 0.dp,
    trailingContent: (@Composable () -> Unit)? = null,
    trailingSpace: Dp = 0.dp,
    enterTransition: EnterTransition = CroutonPosition.getEnterTransition(croutonGravity),
    exitTransition: ExitTransition = CroutonPosition.getExitTransition(croutonGravity)
) {
    LaunchedEffect(key1 = showCrouton) {
        if (showCrouton) {
            if (!autoDismiss.not()) {
                delay(duration)
                onCroutonListener(false)
            }
        }
    }

    val defaultTitleStyle = TextStyle(
        fontSize = 15.sp,
        fontWeight = FontWeight.SemiBold,
        color = Color(0xFF0C0C0C)
    )

    val defaultMessageStyle = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        color = Color(0xFF0C0C0C),
    )

    AnimatedVisibility(
        visible = showCrouton,
        enter = enterTransition,
        exit = exitTransition
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .safeContentPadding(),
            contentAlignment = CroutonPosition.getAlignmentOfCrouton(croutonGravity)
        ) {
            Row(
                modifier = Modifier
                    .then(
                        if (fillMaxWidth) Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 0.dp)
                        else Modifier.padding(horizontal = 12.dp)
                    )
                    .offset(y = if (sticky) 0.dp else CroutonPosition.getOffsetY(croutonGravity))
                    .background(backgroundColor, shape)
                    .then(border?.let {
                        Modifier.border(it, shape)
                    } ?: Modifier)
                    .then(
                        if (!sticky) Modifier.padding(
                            horizontal = 12.dp,
                            vertical = 8.dp
                        ) else Modifier.padding(horizontal = 10.dp, vertical = 8.dp)
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                leadingContent?.let {
                    it()
                    Spacer(Modifier.width(leadingSpace))
                }

                Column(
                    modifier = if (fillMaxWidth || leadingContent != null && trailingContent != null) Modifier.weight(1f) else Modifier.wrapContentWidth(),
                    verticalArrangement = Arrangement.spacedBy(if (message.isNullOrEmpty()) 0.dp else 2.dp)
                ) {
                    Text(text = title,
                        maxLines = 1,
                        style = titleStyle ?: defaultTitleStyle)
                    if (!message.isNullOrEmpty()) {
                        Text(text = message,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            style = messageStyle ?: defaultMessageStyle)
                    }
                }

                trailingContent?.let {
                    Spacer(Modifier.width(trailingSpace))
                    it()
                }
            }
        }
    }
}
