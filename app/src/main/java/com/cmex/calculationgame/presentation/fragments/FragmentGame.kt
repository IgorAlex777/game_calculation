package com.cmex.calculationgame.presentation.fragments

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.cmex.calculationgame.R
import com.cmex.calculationgame.data.ConstantsApp
import com.cmex.calculationgame.data.ConstantsApp.FINISH_TIMER
import com.cmex.calculationgame.data.myLog
import com.cmex.calculationgame.data.utilOpenFragment
import com.cmex.calculationgame.databinding.FragmentGameBinding
import com.cmex.calculationgame.domain.entity.GameResult
import com.cmex.calculationgame.domain.entity.GameSettings
import com.cmex.calculationgame.domain.entity.Level
import com.cmex.calculationgame.presentation.GameViewModelFactory
import com.cmex.calculationgame.presentation.MainViewModel


class FragmentGame : Fragment() {
       private lateinit var binding:FragmentGameBinding
       private lateinit var level: Level
       private lateinit var  gameSettings : GameSettings

       private val gameViewModelFactory by lazy {
           GameViewModelFactory(requireActivity().application, level)
       }

    private val model by lazy {
        ViewModelProvider(this,gameViewModelFactory)[MainViewModel::class.java]
    }

      private val listTextView by lazy {
          mutableListOf<TextView>().apply {
              with(binding){
                  add(tv1)
                  add(tv2)
                  add(tv3)
                  add(tv4)
                  add(tv5)
                  add(tv6)
              }
          }
      }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getArgsFragment()


    }
    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
                cv1->{ model.checkAnswer(tv1.text.toString().toInt())}
                 cv2->{model.checkAnswer(tv2.text.toString().toInt())}
                 cv3->{model.checkAnswer(tv3.text.toString().toInt())}
                 cv4->{model.checkAnswer(tv4.text.toString().toInt())}
                 cv5->{model.checkAnswer(tv5.text.toString().toInt())}
                 cv6->{model.checkAnswer(tv6.text.toString().toInt())}
                }
            }
        }
    }
    private fun initObserverFragment()= with(binding){
        model.timerModel.observe(viewLifecycleOwner){
            tvTime.text=it

        }

        model.questionModel.observe(viewLifecycleOwner){
            tvAnswer.text=it.sum.toString()
            tvVisibleNumber.text=it.visibleNumber.toString()
            for(i in 0 until listTextView.size){
               listTextView[i].text=it.listAnswer[i].toString()
            }
        }
       model.gameResultModel.observe(viewLifecycleOwner){
           utilOpenFragment(FragmentResult
               .newInstance(it),
               null)
       }
        model.countCorrectAnswerModel.observe(viewLifecycleOwner){
            progressBar.progress=it
            if(it>gameSettings.minNumberCorrectAnswer){
                progressBar.progressTintList= ColorStateList.valueOf(requireActivity().getColor(R.color.lime))
            }
        }
        model.gameSettingsModel.observe(viewLifecycleOwner){
            gameSettings=it
            progressBar.max=it.minPercentCorrectAnswer
            progressBar.secondaryProgress=it.minNumberCorrectAnswer

        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onListenerClickNumber()
        initObserverFragment()

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