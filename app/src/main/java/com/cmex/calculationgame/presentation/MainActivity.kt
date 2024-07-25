package com.cmex.calculationgame.presentation

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cmex.calculationgame.data.GameExecutionImpl
import com.cmex.calculationgame.data.myLog
import com.cmex.calculationgame.data.utilOpenFragment
import com.cmex.calculationgame.databinding.ActivityMainBinding
import com.cmex.calculationgame.domain.entity.Question
import com.cmex.calculationgame.presentation.fragments.FragmentPresentation

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var question: Question
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }
    private fun init(){
        utilOpenFragment(FragmentPresentation.newInstance(),"a")
        question=GameExecutionImpl.onGenerateQuestion(99,8)
    }

}