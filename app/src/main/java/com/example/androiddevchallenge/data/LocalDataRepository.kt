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
package com.example.androiddevchallenge.data

import com.example.androiddevchallenge.data.FakeData.boomer
import com.example.androiddevchallenge.data.FakeData.bronson
import com.example.androiddevchallenge.data.FakeData.buzz
import com.example.androiddevchallenge.data.FakeData.dante
import com.example.androiddevchallenge.data.FakeData.kiki
import com.example.androiddevchallenge.data.FakeData.leopold
import com.example.androiddevchallenge.data.FakeData.melva
import com.example.androiddevchallenge.data.FakeData.mona
import com.example.androiddevchallenge.data.FakeData.parvana
import com.example.androiddevchallenge.data.FakeData.pebbles
import com.example.androiddevchallenge.data.FakeData.poohBear
import com.example.androiddevchallenge.data.FakeData.remy
import com.example.androiddevchallenge.data.FakeData.rocky
import com.example.androiddevchallenge.model.Puppy

class LocalDataRepository {

    private val listOfPuppy = listOf(
        boomer,
        bronson,
        dante,
        melva,
        kiki,
        pebbles,
        mona,
        buzz,
        parvana,
        leopold,
        rocky,
        poohBear,
        remy
    )

    fun getAllPuppy() = listOfPuppy

    fun getPuppyById(id: String): Puppy? {
        return listOfPuppy.find { it.id == id }
    }
}
