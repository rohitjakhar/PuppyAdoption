package com.example.androiddevchallenge.navigation

import com.example.androiddevchallenge.R

sealed class Screen(val route: String, val resourceId: Int) {
    object PuppyList : Screen("puppyList", R.string.puppy_list)
    object PuppyDetails : Screen("puppyDetails", R.string.puppy_details)
}
