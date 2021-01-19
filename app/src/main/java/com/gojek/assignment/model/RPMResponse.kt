package com.gojek.assignment.model

class RPMResponse : ArrayList<RPMResponseItem>()
data class RPMResponseItem(
    val max: Int,
    val min: Int,
    val random: Int,
    val status: String
)