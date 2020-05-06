package com.example.huda_haryana.fragments_bottom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.huda_haryana.R
import com.example.huda_haryana.databinding.FragmentLabelsBinding

class LabelsFragment : Fragment() {
    lateinit var binding: FragmentLabelsBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding=DataBindingUtil.inflate(inflater, R.layout.fragment_labels,container,false)

        return binding.root
    }

}
