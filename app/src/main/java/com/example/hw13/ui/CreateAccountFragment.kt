package com.example.hw13.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hw13.databinding.FragmentCreateAccountBinding


class CreateAccountFragment : Fragment() {

    private lateinit var  binding : FragmentCreateAccountBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateAccountBinding.inflate (inflater, container, false)
        return binding.root
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_create_account, container, false)
    }


}