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
package com.example.androiddevchallenge.ui.list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import com.example.androiddevchallenge.navigation.Screen
import dev.chrisbanes.accompanist.coil.CoilImage

@ExperimentalFoundationApi
@Composable
fun PuppyList(viewModel: PuppyListViewModel, navController: NavController) {
    val puppyState = viewModel.puppyList.observeAsState(initial = emptyList())

    Surface(color = MaterialTheme.colors.background) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "happy",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .height(48.dp)
                    .padding(12.dp)
            )
            LazyColumn(
                content = {
                    items(puppyState.value.size) { index ->
                        val puppy = puppyState.value[index]
                        Card(
                            modifier = Modifier
                                .padding(8.dp)
                                .clickable { navController.navigate("${Screen.PuppyDetails.route}/${puppy.id}") },
                            border = BorderStroke(2.dp, MaterialTheme.colors.secondaryVariant),
                            shape = RoundedCornerShape(8.dp),
                            elevation = 10.dp
                        ) {
                            Column {
                                puppy.imageUrl?.let {
                                    CoilImage(
                                        data = it,
                                        contentDescription = null,
                                        contentScale = ContentScale.Crop,
                                        fadeIn = true,
                                        modifier = Modifier.height(220.dp)
                                    )
                                }
                                Text(
                                    puppy.name,
                                    style = MaterialTheme.typography.h6,
                                    modifier = Modifier.padding(start = 8.dp, end = 8.dp),
                                    fontWeight = FontWeight.SemiBold
                                )
                                puppy.breed?.let {
                                    Text(
                                        it,
                                        style = MaterialTheme.typography.subtitle2,
                                        modifier = Modifier.padding(
                                            start = 8.dp,
                                            end = 8.dp,
                                            top = 4.dp,
                                            bottom = 4.dp
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
            )
        }
    }
}
