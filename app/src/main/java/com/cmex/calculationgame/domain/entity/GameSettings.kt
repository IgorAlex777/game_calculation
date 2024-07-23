package com.cmex.calculationgame.domain.entity

import java.io.Serializable

data class GameSettings (
    val maxSum:Int,
    val minNumberCorrectAnswer:Int,
    val minPercentCorrectAnswer:Int,
    val timeInSeconds:Int
):Serializable