package com.cmex.calculationgame.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cmex.calculationgame.R
import com.cmex.calculationgame.data.utilOpenFragment
import com.cmex.calculationgame.databinding.FragmentPresentationBinding
import com.cmex.calculationgame.domain.entity.Level


class FragmentPresentation : Fragment() {
    private lateinit var binding:FragmentPresentationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       binding=FragmentPresentationBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClickButton()
    }
    private fun onClickButton(){
        binding.btnStartGame.setOnClickListener {
            utilOpenFragment(FragmentSelectLevel.newInstance(),null)
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            FragmentPresentation()
    }
}