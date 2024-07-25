package com.cmex.calculationgame.data

import android.annotation.SuppressLint
import com.cmex.calculationgame.domain.entity.GameSettings
import com.cmex.calculationgame.domain.entity.Level
import com.cmex.calculationgame.domain.entity.Question
import com.cmex.calculationgame.domain.repository.InterfaceGame
import kotlin.random.Random

object GameExecutionImpl:InterfaceGame {
    const val MIN_NUMBER=2
    const val MIN_ANSWER=1
    private var listAnswer= mutableSetOf<Int>()//HashSet элементы уникальныеб не повторяются
    override fun onSettingGame(level: Level): GameSettings {
        return when(level){
            Level.TEST->{
                GameSettings(10,3,0,20)
            }

            Level.EASY->{
                GameSettings(10,0,0,40)
            }

            Level.AVERAGE->{
                GameSettings(49,0,0,30)
            }

            Level.HARD->{
                GameSettings(99,0,0,20)
            }
        }
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onGenerateQuestion(sum: Int, numberAnswers: Int): Question {
        var flagAnswer=true
        val sumScreen=Random.nextInt(MIN_NUMBER,sum+1)
          myLog("сумма=$sumScreen")
        var numberTemp=0
        val numberVisible=Random.nextInt(MIN_ANSWER,sumScreen)
        myLog("видимое число=$numberVisible")
        val correctAnswer=sumScreen-numberVisible
        myLog("правильный ответ=$correctAnswer")
        listAnswer.add(correctAnswer)

        while(listAnswer.size<numberAnswers){
            listAnswer.add(Random.nextInt(MIN_ANSWER,sum))
        }
        val list= mutableListOf<Int>()
        list.addAll(listAnswer)
        list.shuffle()
        myLog("ответы=${list.toList()}")
        return Question(sum,numberVisible, list)
    }
}