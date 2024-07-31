package com.cmex.calculationgame.presentation.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.FragmentManager
import com.bumptech.glide.Glide
import com.cmex.calculationgame.R
import com.cmex.calculationgame.data.ConstantsApp
import com.cmex.calculationgame.databinding.FragmentResultBinding
import com.cmex.calculationgame.domain.entity.GameResult


class FragmentResult : Fragment() {
    private lateinit var binding: FragmentResultBinding
    private lateinit var gameResult: GameResult

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getArgumentsFragment()

    }
    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
      binding=FragmentResultBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onCheckingResult()
        onBackPressedFragment()
        onSetTextView()
    }
    private fun onCheckingResult(){
        if(gameResult.winner){
            Glide.with(this)
                .load(R.drawable.correct_answer)
                .override(777,777)
                .fitCenter()
                .error(R.drawable.circle)
                .into(binding.ivResult)
        }else{
            Glide.with(this)
                .load(R.drawable.incorrect_answer)
                .override(777,777)
                .fitCenter()
                .error(R.drawable.circle)
                .into(binding.ivResult)
        }
    }
    private fun onSetTextView()= with(binding){
        tvMinCountAnswer.text= String.format(requireActivity().getString(R.string.minCountAnswer),
            gameResult.gameSettings.minNumberCorrectAnswer)
        tvCountAnswer.text= String.format((requireActivity().getString(R.string.CountAnswer)),gameResult.numberCorrectAnswers)
    }
    private fun goToSelection(){
        requireActivity().supportFragmentManager.popBackStack(ConstantsApp.NAME_FRAGMENT_GAME,
            FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
     private fun onBackPressedFragment(){
      requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,object :OnBackPressedCallback(true){
          override fun handleOnBackPressed() {
            goToSelection()
          }

      })
     }

  @Suppress("DEPRECATION")
    private fun getArgumentsFragment(){
        gameResult=requireArguments().getSerializable(ConstantsApp.KEY_RESULT) as GameResult
    }
    companion object {

        @JvmStatic
        fun newInstance(gameResult: GameResult) =
            FragmentResult().apply {
                arguments=Bundle().apply {
                    putSerializable(ConstantsApp.KEY_RESULT,gameResult)
                }
            }
    }
}