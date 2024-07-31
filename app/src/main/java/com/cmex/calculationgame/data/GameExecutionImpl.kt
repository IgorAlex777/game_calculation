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
                GameSettings(10,1,10,20)
            }

            Level.EASY->{
                GameSettings(10,20,40,40)
            }

            Level.AVERAGE->{
                GameSettings(49,20,40,30)
            }

            Level.HARD->{
                GameSettings(99,20,40,20)
            }
        }
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onGenerateQuestion(sum: Int, numberAnswers: Int): Question {
        val sumScreen=Random.nextInt(MIN_NUMBER,sum)
         myLog("сумма=$sumScreen")
        val numberVisible=Random.nextInt(MIN_ANSWER,sumScreen)
        val correctAnswer=sumScreen-numberVisible
        myLog("правильный ответ=$correctAnswer")
        listAnswer.clear()
        listAnswer.add(correctAnswer)

        while(listAnswer.size<numberAnswers){
            listAnswer.add(Random.nextInt(MIN_ANSWER,sum))
        }

        val list= mutableListOf<Int>()
        list.addAll(listAnswer)
        list.shuffle()
        return Question(sumScreen,numberVisible, list)
    }
}