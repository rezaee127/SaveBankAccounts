package com.example.hw13.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.hw13.databinding.FragmentShowAccountsBinding
import com.example.hw13.viewModels.AccountViewModel


class ShowAccountsFragment : Fragment() {
    private lateinit var  binding : FragmentShowAccountsBinding
    private val vModel: AccountViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShowAccountsBinding.inflate (inflater, container, false)
        return binding.root
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_show_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }



    @SuppressLint("SetTextI18n")
    private fun initView() {

        for (i in 0 until vModel.getAllAccounts().size){
            Toast.makeText(requireActivity(),"${vModel.getAllAccounts().get(i).balance}", Toast.LENGTH_SHORT).show()
        }


        if(vModel.getAllAccounts().size!=0){

            vModel.balanceLiveData.observe(requireActivity()){
                binding.textViewBalance.text="موجودی : ${it.toString()}"
            }

            vModel.cardNumberLiveData.observe(requireActivity()){
                binding.textViewCardNumber.text="شماره کارت : $it"
            }

            vModel.accountType.observe(requireActivity()){
                binding.textViewTypeAccount.text="نوع حساب : $it"
            }


        /*    vModel.accountLiveData?.observe(requireActivity()){
                binding.textViewBalance.text=it.balance.toString()
                binding.textViewCardNumber.text=it.cardNumber
                binding.textViewTypeAccount.text=it.accountType.toString()
            }

         */

            vModel.nextEnabledLiveData.observe(requireActivity()){
                binding.buttonNext.isEnabled=it
            }
            vModel.backEnabledLiveData.observe(requireActivity()){
                binding.buttonBack.isEnabled=it
            }

            binding.buttonNext.setOnClickListener {
                vModel.nextClicked()
            }

            binding.buttonBack.setOnClickListener {
                vModel.backClicked()
            }


        }else{
            binding.textViewCardNumber.text="دیتابیس خالی است"
            binding.buttonNext.isEnabled=false
            binding.buttonBack.isEnabled=false
        }

    }


}