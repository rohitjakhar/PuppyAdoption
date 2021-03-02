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
package com.example.androiddevchallenge.ui.details

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.androiddevchallenge.component.InfoCard
import dev.chrisbanes.accompanist.coil.CoilImage

@ExperimentalFoundationApi
@Composable
fun PuppyDetails(
    id: String,
    viewModel: PuppyDetailsViewModel,
    navController: NavController
) {
    LaunchedEffect(id) {
        viewModel.findDog(id)
    }
    Surface(color = MaterialTheme.colors.background) {
        val puppyState = viewModel.puppyData.observeAsState()

        if (puppyState.value != null) {
            val puppy = puppyState.value
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                puppy?.imageUrl?.let {
                    CoilImage(
                        data = it,
                        contentDescription = null,
                        fadeIn = true,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(300.dp)
                            .clip(
                                RoundedCornerShape(
                                    topStart = CornerSize(0.dp),
                                    topEnd = CornerSize(0.dp),
                                    bottomEnd = CornerSize(32.dp),
                                    bottomStart = CornerSize(32.dp)
                                )
                            )
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = puppy.name,
                            style = MaterialTheme.typography.h5,
                            modifier = Modifier.padding(16.dp)
                        )
                        Text(
                            text = puppy.gender,
                            modifier = Modifier
                                .padding(8.dp)
                                .height(32.dp),
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Basic Info",
                        modifier = Modifier
                            .padding(8.dp)
                            .height(32.dp),
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        puppy.apply {
                            InfoCard(title = "name", value = puppy.name)
                            InfoCard(title = "age", value = puppy.age)
                            InfoCard(title = "weight", value = puppy.weight)
                        }
                    }
                    Text(
                        text = puppy.story,
                        fontSize = 18.sp,
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier.padding(16.dp)
                    )
                    Spacer(modifier = Modifier.height(9.dp))
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { }
                    ) {
                        Text(text = "Adopt Me")
                    }
                }
            }
            Icon(
                Icons.Sharp.ArrowBack, "back",
                modifier = Modifier
                    .size(48.dp)
                    .clickable {
                        navController.popBackStack()
                    }
                    .padding(12.dp)
            )
        }
    }
}
