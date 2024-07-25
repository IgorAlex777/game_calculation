package com.cmex.calculationgame.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cmex.calculationgame.R
import com.cmex.calculationgame.databinding.FragmentTestBinding


class FragmentTest : Fragment() {
       private lateinit var binding:FragmentTestBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
     binding=FragmentTestBinding.inflate(inflater,container,false)
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            FragmentTest()
    }
}