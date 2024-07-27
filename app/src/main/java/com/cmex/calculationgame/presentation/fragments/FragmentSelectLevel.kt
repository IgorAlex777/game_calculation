package com.cmex.calculationgame.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cmex.calculationgame.R
import com.cmex.calculationgame.data.ConstantsApp
import com.cmex.calculationgame.data.utilOpenFragment
import com.cmex.calculationgame.databinding.FragmentSelectLevelBinding
import com.cmex.calculationgame.domain.entity.Level


class FragmentSelectLevel : Fragment() {
       private lateinit var binding: FragmentSelectLevelBinding



       override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
         binding=FragmentSelectLevelBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListenerClick()
    }
    private fun setListenerClick()=with(binding){
        val listener=onClickLevel()
        tvTest.setOnClickListener(listener)
        tvEase.setOnClickListener(listener)
        tvAverage.setOnClickListener(listener)
        tvHard.setOnClickListener(listener)
    }

    private fun onClickLevel():View.OnClickListener{
        return View.OnClickListener {
            when(it){
               binding.tvTest->{utilOpenFragment(FragmentGame.newInstance(Level.TEST),ConstantsApp.NAME_FRAGMENT_GAME)}
                binding.tvEase->{utilOpenFragment(FragmentGame.newInstance(Level.EASY),ConstantsApp.NAME_FRAGMENT_GAME)}
                binding.tvAverage->{utilOpenFragment(FragmentGame.newInstance(Level.AVERAGE),ConstantsApp.NAME_FRAGMENT_GAME)}
                binding.tvHard->{utilOpenFragment(FragmentGame.newInstance(Level.HARD),ConstantsApp.NAME_FRAGMENT_GAME)}
            }

        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            FragmentSelectLevel()
    }
}