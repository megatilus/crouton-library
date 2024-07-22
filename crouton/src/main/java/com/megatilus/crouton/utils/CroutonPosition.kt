package com.megatilus.crouton.utils

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object CroutonPosition {
    fun getOffsetY(croutonGravity: CroutonGravity): Dp {
        return when (croutonGravity) {
            CroutonGravity.TOP -> 48.dp
            CroutonGravity.CENTER -> 0.dp
            CroutonGravity.BOTTOM -> (-48).dp
        }
    }

    fun getAlignmentOfCrouton(croutonGravity: CroutonGravity): Alignment {
        return when (croutonGravity) {
            CroutonGravity.TOP -> Alignment.TopCenter
            CroutonGravity.CENTER -> Alignment.Center
            CroutonGravity.BOTTOM -> Alignment.BottomCenter
        }
    }

    fun getEnterTransition(croutonGravity: CroutonGravity): EnterTransition {
        return when (croutonGravity) {
            CroutonGravity.TOP -> slideInVertically(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessLow
                ), initialOffsetY = { -it })
            CroutonGravity.CENTER -> scaleIn(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
            CroutonGravity.BOTTOM -> slideInVertically(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessLow
                ), initialOffsetY = { it })
        }
    }

    fun getExitTransition(croutonGravity: CroutonGravity): ExitTransition {
        return when (croutonGravity) {
            CroutonGravity.TOP -> slideOutVertically(
                targetOffsetY = { -it },
                animationSpec = tween(500)
            )
            CroutonGravity.CENTER -> scaleOut(animationSpec = tween(500))
            CroutonGravity.BOTTOM -> slideOutVertically(
                targetOffsetY = { it },
                animationSpec = tween(500)
            )
        }
    }
}