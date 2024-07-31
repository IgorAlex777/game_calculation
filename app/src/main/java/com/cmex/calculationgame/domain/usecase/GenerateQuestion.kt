package com.cmex.calculationgame.domain.usecase

import com.cmex.calculationgame.data.ConstantsApp
import com.cmex.calculationgame.data.ConstantsApp.COUNT_OPTIONS_ANSWER
import com.cmex.calculationgame.domain.entity.Question
import com.cmex.calculationgame.domain.repository.InterfaceGame

class GenerateQuestion(private val listener:InterfaceGame) {
    operator fun invoke(maxSum:Int):Question{
      return  listener.onGenerateQuestion(maxSum, COUNT_OPTIONS_ANSWER)
    }

}