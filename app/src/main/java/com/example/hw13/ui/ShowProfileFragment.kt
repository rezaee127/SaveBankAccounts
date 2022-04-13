package com.example.hw13.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.hw13.R
import com.example.hw13.databinding.FragmentShowProfileBinding
import com.example.hw13.viewModels.ViewModel


class ShowProfileFragment : Fragment() {
    private lateinit var  binding : FragmentShowProfileBinding
    private val vModel: ViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShowProfileBinding.inflate (inflater, container, false)
        return binding.root
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_show_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

    }

    private fun initView() {
        val pref = requireActivity().getSharedPreferences("share", Context.MODE_PRIVATE)
        binding.textViewFirstName.text = pref.getString("firstName","")
        binding.textViewLastName.text = pref.getString("lastName","")
        binding.textViewFatherName.text = pref.getString("fatherName","")
        binding.textViewPostCode.text = pref.getString("postCode","")
        binding.textViewPhone.text = pref.getString("phone","")

        binding.buttonEdit.setOnClickListener {
            vModel.editProfileInfoFlag=true
            findNavController().navigate(R.id.action_showProfileFragment_to_profileFragment)
        }
    }


}