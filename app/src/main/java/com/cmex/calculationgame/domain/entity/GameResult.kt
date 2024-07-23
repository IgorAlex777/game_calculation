package com.cmex.calculationgame.domain.entity

import java.io.Serializable

data class GameResult(
    val winner:Boolean,
    val numberCorrectAnswers:Int,
    val numberQuestions:Int,
    val gameSettings: GameSettings
):Serializable
