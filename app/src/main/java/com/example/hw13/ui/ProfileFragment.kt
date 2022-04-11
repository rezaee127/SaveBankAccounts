package com.example.hw13.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.hw13.R
import com.example.hw13.databinding.FragmentProfileBinding
import com.example.hw13.repository.Repository

class ProfileFragment : Fragment() {
    private lateinit var  binding : FragmentProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate (inflater, container, false)
        return binding.root
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pref = requireActivity().getSharedPreferences("share", Context.MODE_PRIVATE)

        when {
            pref.getString("firstName", "").isNullOrBlank() -> register()
            Repository.editProfileInfoFlag -> edit()
        }
    }




    private fun register(){
        val regex1 =   Regex("^[ آابپتثجچحخدذرزژسشصضطظعغفقکگلمنوهیئ]+$")

        binding.buttonRegister.setOnClickListener {
            when {
                binding.editTextFirstName .text.isNullOrBlank() -> binding.editTextFirstName.error = "نام را وارد کنید"
                !regex1.matches(binding.editTextFirstName.text) -> binding.editTextFirstName.error = "نام اشتباه است"
                binding.editTextLastName.text.isNullOrBlank() -> binding.editTextLastName.error = "نام خانوادگی را وارد کنید"
                !regex1.matches(binding.editTextLastName.text) -> binding.editTextLastName.error = "نام خانوادگی اشتباه است"
                binding.editTextFatherName.text.isNullOrBlank() -> binding.editTextFatherName.error = "نام پدر را وارد کنید"
                !regex1.matches(binding.editTextFatherName.text) -> binding.editTextFatherName.error = "نام پدر اشتباه است"
                binding.editTextPostCode.text.isNullOrBlank() -> binding.editTextPostCode.error = "کدپستی را وارد کنید"
                binding.editTextPostCode.length()!=10 -> binding.editTextPostCode.error="کدپستی اشتباه است"
                binding.editTextPhone.text.isNullOrBlank() -> binding.editTextPhone.error = "شماره تلفن را وارد کنید"
                binding.editTextPhone.length()!=11 -> binding.editTextPhone.error="شماره تلفن اشتباه است"

                else -> saveOnSharedPreferences()
            }
        }
    }


    private fun saveOnSharedPreferences() {
        val firstName = binding.editTextFirstName.text.toString()
        val lastName=binding.editTextLastName.text.toString()
        val fatherName=binding.editTextFatherName.text.toString()
        val postCode=binding.editTextPostCode.text.toString()
        val phone=binding.editTextPhone.text.toString()

        val pref = requireActivity().getSharedPreferences("share", Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putString("firstName", firstName)
        editor.putString("lastName", lastName)
        editor.putString("fatherName", fatherName)
        editor.putString("postCode", postCode)
        editor.putString("phone", phone)
        editor.apply()
        Toast.makeText(activity, "ذخیره اطلاعات انجام شد", Toast.LENGTH_LONG).show()

        findNavController().navigate(R.id.action_profileFragment_to_showProfileFragment)
    }


    private fun edit() {
        Repository.editProfileInfoFlag=false
        val pref = requireActivity().getSharedPreferences("share", Context.MODE_PRIVATE)
        binding.editTextFirstName.setText(pref.getString("firstName",""))
        binding.editTextLastName.setText(pref.getString("lastName",""))
        binding.editTextFatherName.setText(pref.getString("fatherName",""))
        binding.editTextPostCode.setText(pref.getString("postCode",""))
        binding.editTextPhone.setText(pref.getString("phone",""))

        register()
    }

}