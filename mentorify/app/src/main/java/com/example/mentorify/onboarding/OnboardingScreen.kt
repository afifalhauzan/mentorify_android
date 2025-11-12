package com.example.mentorify.onboarding

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.mentorify.R

data class OnboardingPage(
    @DrawableRes val image: Int,
    @StringRes val title: Int,
    @StringRes val description: Int
)

val onboardingPages = listOf(
    OnboardingPage(
        image = R.drawable.onboarding_illustration, // You'll need to add this drawable
        title = R.string.onboarding_title_welcome,
        description = R.string.onboarding_description_welcome
    ),
    OnboardingPage(
        image = R.drawable.onboarding_illustration_2, // You'll need to add this drawable
        title = R.string.onboarding_title_2,
        description = R.string.onboarding_description_2
    ),
    OnboardingPage(
        image = R.drawable.onboarding_illustration_3, // You'll need to add this drawable
        title = R.string.onboarding_title_3,
        description = R.string.onboarding_description_3
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
                },
                onSkipClicked = { onFinishOnboarding() }
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
                .size(200.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = stringResource(id = page.title),
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(id = page.description),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BottomBar(
    pagerState: PagerState,
    onNextClicked: () -> Unit,
    onSkipClicked: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (pagerState.currentPage < onboardingPages.size - 1) {
            TextButton(onClick = onSkipClicked) {
                Text(text = "Lewati")
            }
        }
        
        HorizontalPagerIndicator(
            pagerState = pagerState,
            pageCount = onboardingPages.size,
            modifier = Modifier.weight(1f),
            activeColor = MaterialTheme.colorScheme.primary,
            inactiveColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
        )

        Button(onClick = onNextClicked) {
            Text(text = stringResource(id = if (pagerState.currentPage == onboardingPages.size - 1) R.string.button_get_started else R.string.button_next))
        }
    }
}
