package com.safetyapp.safetybuddy.feature.onboarding.presentation
import androidx.annotation.RawRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.safetyapp.safetybuddy.core.view.composables.PreviewLightDark
import kotlinx.coroutines.delay

/**
 These composable renders a view pager component from the material 3 design system.
  it takes in parameters such as
  @Param[currentOnBoardingPage] : to display the current onboarding  screen details,
  from the onboarding page class.
  @Param[modifier] : to modify the composable
  @Param[playAnimation] : to play or stop the animation
  @Param[delayTime]: to delay the animation
  @Param[animationSpeed]: to determine the speed of the animation
 * **/

@Composable
fun AnimatedDisplayComposable(
    modifier:Modifier = Modifier,
    @RawRes image:Int ,
    playAnimation:Boolean= true,
    delayTime:Long = 2700,
    animationSpeed:Float =1f,
    restartOnPlay: Boolean =true

) {
    Column(
       modifier =modifier
    ) {
        val composition by rememberLottieComposition(
            spec = LottieCompositionSpec.RawRes(
                image
            )
        )
        var isAnimationPlaying by remember { mutableStateOf(playAnimation) }
        val speed by remember { mutableFloatStateOf(animationSpeed) }

        LaunchedEffect(key1 = isAnimationPlaying) {
            delay(delayTime)
            isAnimationPlaying = !playAnimation
        }

        val progress by animateLottieCompositionAsState(
            composition = composition,
            iterations = LottieConstants.IterateForever,
            isPlaying = isAnimationPlaying,
            speed = speed,
            restartOnPlay = restartOnPlay,
        )
        LottieAnimation(
            composition = composition,
            progress = progress,
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .background(Color.Transparent),
        )
    }
}




@PreviewLightDark
@Composable
fun AnimatedContentPreview(){
    val pages = listOf(
        OnBoardingPage.First,
        OnBoardingPage.Second,
        OnBoardingPage.Third,
    )
    AnimatedDisplayComposable(
        modifier =  Modifier
        .fillMaxWidth().background(Color.Transparent),
        image = pages[0].image,
    )
}