package com.cmex.calculationgame.domain.usecase

import com.cmex.calculationgame.domain.entity.Question
import com.cmex.calculationgame.domain.repository.InterfaceGame

class GenerateQuestion(private val listener:InterfaceGame) {
    operator fun invoke(maxSum:Int):Question{
      return  listener.onGenerateQuestion(maxSum, NUMBER_ATTEMPTS)
    }

    private companion object{
       private const val NUMBER_ATTEMPTS=6
    }
}