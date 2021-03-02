/*
 * Copyright 2021 The Android Open Source Project
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
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.navigation.Screen
import com.example.androiddevchallenge.ui.details.PuppyDetails
import com.example.androiddevchallenge.ui.details.PuppyDetailsViewModel
import com.example.androiddevchallenge.ui.list.PuppyList
import com.example.androiddevchallenge.ui.list.PuppyListViewModel
import com.example.androiddevchallenge.ui.theme.MyTheme

class MainActivity : AppCompatActivity() {
    private val puppyListViewModel by viewModels<PuppyListViewModel>()
    private val puppyDetailsViewModel by viewModels<PuppyDetailsViewModel>()

    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                DogApp(puppyListViewModel, puppyDetailsViewModel)
            }
        }
    }
}

// Start building your app here!
@ExperimentalFoundationApi
@Composable
fun DogApp(puppyListViewModel: PuppyListViewModel, puppyDetailsViewModel: PuppyDetailsViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.PuppyList.route) {
        composable(Screen.PuppyList.route) {
            PuppyList(puppyListViewModel, navController)
        }
        composable(
            "${Screen.PuppyDetails.route}/{puppyId}",
            arguments = listOf(navArgument("puppyId") { type = NavType.StringType }),

        ) { backStackEntry ->
            PuppyDetails(
                viewModel = puppyDetailsViewModel,
                id = backStackEntry.arguments?.getString("puppyId")!!,
                navController = navController
            )
        }
    }
}

// @Preview("Light Theme", widthDp = 360, heightDp = 640)
// @Composable
// fun LightPreview() {
//    MyTheme {
//        DogApp()
//    }
// }
//
// @Preview("Dark Theme", widthDp = 360, heightDp = 640)
// @Composable
// fun DarkPreview() {
//    MyTheme(darkTheme = true) {
//        DogApp()
//    }
// }
