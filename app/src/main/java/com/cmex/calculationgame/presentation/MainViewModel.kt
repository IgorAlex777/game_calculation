package com.cmex.calculationgame.presentation

import android.annotation.SuppressLint
import android.app.Application
import android.os.CountDownTimer
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cmex.calculationgame.data.ConstantsApp.FINISH_TIMER
import com.cmex.calculationgame.data.ConstantsApp.MILLIS_IN_SECONDS
import com.cmex.calculationgame.data.ConstantsApp.START_TIMER
import com.cmex.calculationgame.data.GameExecutionImpl
import com.cmex.calculationgame.data.myLog
import com.cmex.calculationgame.domain.entity.GameResult
import com.cmex.calculationgame.domain.entity.GameSettings
import com.cmex.calculationgame.domain.entity.Level
import com.cmex.calculationgame.domain.entity.Question
import com.cmex.calculationgame.domain.usecase.GenerateQuestion
import com.cmex.calculationgame.domain.usecase.GetSettingLevel

class MainViewModel(private val application:Application,private  val level: Level):ViewModel() {
    private lateinit var gameSettings: GameSettings
    private lateinit var timer:CountDownTimer
    private val context=application
    private var countQuestion=0
    private var countCorrectAnswer=0

    private val listenerExecutionImpl=GameExecutionImpl
    private val getSettingLevelUseCase=GetSettingLevel(listenerExecutionImpl)
    private val generateQuestionUseCase=GenerateQuestion(listenerExecutionImpl)

    private val _timerModel=MutableLiveData<String>()
     val timerModel:LiveData<String>
         get() = _timerModel

    private val _questionModel=MutableLiveData<Question>()
     val questionModel:LiveData<Question>
        get() = _questionModel
    private val _countCorrectAnswerModel=MutableLiveData<Int>()
    val countCorrectAnswerModel:LiveData<Int>
        get() = _countCorrectAnswerModel

    private fun settingGame(){
        gameSettings=getSettingLevelUseCase(level)
        _gameSettingsModel.value=gameSettings
    }
    private  val _gameSettingsModel=MutableLiveData<GameSettings>()
    val gameSettingsModel:LiveData<GameSettings>
        get() = _gameSettingsModel
    private val _gameResultModel=MutableLiveData<GameResult>()
    val gameResultModel:LiveData<GameResult>
        get() = _gameResultModel

    init {
        onStartGame()
    }

   private fun onStartGame(){
        settingGame()
        onStartTimer()
        generateQuestionModel()
    }
    private fun onStartTimer(){
        val timeMs=gameSettings.timeInSeconds*MILLIS_IN_SECONDS
        timer=object :CountDownTimer(timeMs,MILLIS_IN_SECONDS){
            override fun onTick(ms: Long) {
                _timerModel.value=formattedTimer(ms)
            }
            override fun onFinish() {
              onFinishGame()
            }

        }.start()
    }

    private fun onFinishGame() {
        _gameResultModel.value=GameResult(
            onWinnerRound(),
            countCorrectAnswer,
            countQuestion,
            gameSettings
        )
    }
    private fun generateQuestionModel() {
        _questionModel.value=generateQuestionUseCase(gameSettings.maxSum)
    }
    @SuppressLint("SuspiciousIndentation")
     fun checkAnswer(number:Int){
     val correctAnswer=questionModel.value?.correctAnswer
        if(number==correctAnswer){
            countCorrectAnswer++
            _countCorrectAnswerModel.value=countCorrectAnswer
        }
        countQuestion++
        generateQuestionModel()

    }
    private fun onWinnerRound():Boolean{
      return countCorrectAnswer>gameSettings.minNumberCorrectAnswer
    }
    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }
    private fun formattedTimer(timer:Long):String{
        val secondsTemp=timer/ MILLIS_IN_SECONDS
        val minutes=secondsTemp/60
        val seconds=secondsTemp-(minutes*60)
        return String.format("%02d:%02d",minutes,seconds)
    }
}