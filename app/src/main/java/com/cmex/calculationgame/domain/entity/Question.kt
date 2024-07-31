package com.cmex.calculationgame.domain.entity

import java.io.Serializable

data class Question (
    val sum:Int,
    val visibleNumber:Int,
    val listAnswer:List<Int>
):Serializable{
    val correctAnswer:Int
        get() = sum-visibleNumber
}
