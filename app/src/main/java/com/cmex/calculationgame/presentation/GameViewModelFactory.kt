package com.cmex.calculationgame.presentation

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cmex.calculationgame.domain.entity.Level

class GameViewModelFactory(
    private val application: Application,
    private val level: Level
):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(application, level) as T
        }
        throw RuntimeException("обратились не к MainViewModel, к другой $modelClass ")
    }
}