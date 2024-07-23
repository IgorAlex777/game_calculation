package com.cmex.calculationgame.domain.repository

import com.cmex.calculationgame.domain.entity.GameSettings
import com.cmex.calculationgame.domain.entity.Level
import com.cmex.calculationgame.domain.entity.Question

interface InterfaceGame {
    fun onSettingGame(level: Level):GameSettings
    fun onGenerateQuestion(sum:Int,numberAnswers:Int):Question
}