package com.example.mentorify.onboarding

import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mentorify.R
import com.example.mentorify.ui.theme.MentorifyTheme
import com.google.accompanist.pager.HorizontalPagerIndicator // Keep this import for now, if it's not causing issues, although not directly used in the final BottomBar logic
import kotlinx.coroutines.launch

data class OnboardingPage(
    @DrawableRes val image: Int,
    val title: String,
    val description: String
)

// String content directly in the file
val onboardingTitleWelcome = "Selamat Datang"
val onboardingDescriptionWelcome = "Temukan guru terbaikmu bersama Mentorify!"
val onboardingTitle2 = "Tersedia Berbagai Bidang"
val onboardingDescription2 = "Temukan gurumu dari berbagai bidang/mata pelajaran yang siap membantu Anda."
val onboardingTitle3 = "Hubungi Langsung, No Ribet!"
val onboardingDescription3 = "Manfaatkan fitur chat dari kami."
val buttonNext = "Selanjutnya"
val buttonGetStarted = "Mulai"
val buttonSkip = "Lewati"

val onboardingPages = listOf(
    OnboardingPage(
        image = R.drawable.onboarding_1,
        title = onboardingTitleWelcome,
        description = onboardingDescriptionWelcome
    ),
    OnboardingPage(
        image = R.drawable.onboarding_2,
        title = onboardingTitle2,
        description = onboardingDescription2
    ),
    OnboardingPage(
        image = R.drawable.onboarding_3,
        title = onboardingTitle3,
        description = onboardingDescription3
    )
)

@Composable
fun OnboardingScreen(
    navController: NavHostController,
    onFinishOnboarding: () -> Unit
) {
    val pagerState = rememberPagerState { onboardingPages.size }
    val scope = rememberCoroutineScope()

    Scaffold(
        bottomBar = {
            BottomBar(
                pagerState = pagerState,
                onNextClicked = {
                    scope.launch {
                        if (pagerState.currentPage < onboardingPages.size - 1) {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        } else {
                            onFinishOnboarding()
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) { page ->
            OnboardingPageContent(page = onboardingPages[page])
        }
    }
}

@Composable
fun OnboardingPageContent(page: OnboardingPage) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = page.image),
            contentDescription = null,
            modifier = Modifier
                .size(300.dp),
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = page.title,
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = page.description,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BottomBar(
    pagerState: PagerState,
    onNextClicked: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = onNextClicked,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 32.dp) // Added padding to center and make it not full width
        ) {
            Text(text = if (pagerState.currentPage == onboardingPages.size - 1) buttonGetStarted else buttonNext)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingScreenPreview() {
    MentorifyTheme {
        val navController = rememberNavController()
        OnboardingScreen(navController = navController, onFinishOnboarding = {})
    }
}