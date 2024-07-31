package com.cmex.calculationgame.domain.usecase

import com.cmex.calculationgame.domain.entity.GameSettings
import com.cmex.calculationgame.domain.entity.Level
import com.cmex.calculationgame.domain.repository.InterfaceGame

class GetSettingLevel(private val listener:InterfaceGame) {

    operator fun invoke(level: Level):GameSettings{
        return listener.onSettingGame(level)
    }
}