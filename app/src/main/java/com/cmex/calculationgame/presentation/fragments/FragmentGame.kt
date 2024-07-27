package com.cmex.calculationgame.presentation.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cmex.calculationgame.data.ConstantsApp
import com.cmex.calculationgame.data.myLog
import com.cmex.calculationgame.data.utilOpenFragment
import com.cmex.calculationgame.databinding.FragmentGameBinding
import com.cmex.calculationgame.domain.entity.GameResult
import com.cmex.calculationgame.domain.entity.GameSettings
import com.cmex.calculationgame.domain.entity.Level


class FragmentGame : Fragment() {
       private lateinit var binding:FragmentGameBinding
       private lateinit var level: Level
      private lateinit var gameSettings: GameSettings

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getArgsFragment()
        gameSettings= GameSettings(0,0,0,0)
    }
    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
     binding=FragmentGameBinding.inflate(inflater,container,false)
        return binding.root
    }
     private fun onListenerClickNumber()=with(binding){
     val listener=onClickNumberResult()
     cv1.setOnClickListener(listener)
     cv2.setOnClickListener(listener)
     cv3.setOnClickListener(listener)
     cv4.setOnClickListener(listener)
     cv5.setOnClickListener(listener)
     cv6.setOnClickListener(listener)
     }
    private fun onClickNumberResult():View.OnClickListener{
        return View.OnClickListener {
            with(binding) {
                when (it) {
                 cv1->{utilOpenFragment(FragmentResult
                     .newInstance(GameResult(true,0,0,gameSettings)),
                     null)}
                 cv2->{utilOpenFragment(FragmentResult
                     .newInstance(GameResult(false,0,0,gameSettings)),
                     null)}
                 cv3->{}
                 cv4->{}
                 cv4->{}
                 cv5->{}

                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onListenerClickNumber()
        binding.progressBar.progress = 25
        binding.progressBar.max=100

    }
    @Suppress("DEPRECATION")
    private fun getArgsFragment(){
        level=requireArguments().getSerializable(ConstantsApp.KEY_LEVEL) as Level
        myLog("level=$level")
    }
    companion object {

        @JvmStatic
        fun newInstance(level: Level) =
            FragmentGame().apply {
                arguments=Bundle().apply {
                    putSerializable(ConstantsApp.KEY_LEVEL,level)
                }
            }
    }
}