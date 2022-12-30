package dev.garousi.stock_watcher.feature.watchlist.presentation

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class WatchlistScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun setup() {
        with(composeTestRule) {
            setContent {
                WatchlistScreen()
            }
        }
    }

    @Test
    fun assertIsShowing() {
        composeTestRule
            .onNodeWithTag("watchlist_root")
            .assertIsDisplayed()
    }

}