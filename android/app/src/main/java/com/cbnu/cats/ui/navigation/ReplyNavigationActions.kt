/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cbnu.cats.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Article
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.cbnu.cats.R

object ReplyRoute {
    const val HOME = "홈"
    const val LP = "글씨 연습"
    const val DM = "DirectMessages"
    const val PROFILE = "프로필"
}

data class ReplyTopLevelDestination(
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconTextId: Int
)

class ReplyNavigationActions(private val navController: NavHostController) {

    fun navigateTo(destination: ReplyTopLevelDestination) {
        navController.navigate(destination.route) {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }
    }
}

val TOP_LEVEL_DESTINATIONS = listOf(
    ReplyTopLevelDestination(
        route = ReplyRoute.HOME,
        selectedIcon = Icons.Default.Home,
        unselectedIcon = Icons.Default.Home,
        iconTextId = R.string.tab_Home
    ),
    ReplyTopLevelDestination(
        route = ReplyRoute.LP,
        selectedIcon = Icons.Default.EditNote,
        unselectedIcon = Icons.Default.EditNote,
        iconTextId = R.string.tab_letter_practice
    ),
    ReplyTopLevelDestination(
        route = ReplyRoute.DM,
        selectedIcon = Icons.Outlined.ChatBubbleOutline,
        unselectedIcon = Icons.Outlined.ChatBubbleOutline,
        iconTextId = R.string.tab_dm
    ),
    ReplyTopLevelDestination(
        route = ReplyRoute.PROFILE,
        selectedIcon = Icons.Default.Person,
        unselectedIcon = Icons.Default.Person,
        iconTextId = R.string.tab_profile
    )

)
