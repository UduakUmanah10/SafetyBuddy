package com.safetyapp.safetybuddy.feature.onboarding.presentation
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.safetyapp.safetybuddy.core.view.theme.PreviewLightDark
import com.safetyapp.safetybuddy.core.view.theme.SafeBuddyTheme


@Composable
fun OnboardingScreen(
    listOfOnboardingItems:List<OnBoardingPage>  = listOf(
        OnBoardingPage.First,
        OnBoardingPage.Second,
        OnBoardingPage.Third,
    ),
    onNavigate: () -> Unit = {}

) {

    val pagerState = rememberPagerState(0){
        listOfOnboardingItems.size
    }

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.secondary)
            .fillMaxSize()
            .statusBarsPadding().padding(top = 50.dp)
            .navigationBarsPadding(),
    ) {

        HorizontalPager(
            modifier = Modifier,
            state = pagerState,
            verticalAlignment = Alignment.Top,
        ) { position ->

                PagerComposable(
                    currentIndex = position,
                    image = listOfOnboardingItems[position].image,
                    firstScreenText = listOfOnboardingItems[position].title,
                    secondScreenText = listOfOnboardingItems[position].description,
                    onfinishClicked = onNavigate
                )

        }
    }
}



@Composable
@PreviewLightDark
fun OnBoardingScreenPreview() {

    SafeBuddyTheme{
        OnboardingScreen()

    }

}