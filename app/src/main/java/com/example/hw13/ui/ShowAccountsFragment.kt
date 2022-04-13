package com.example.hw13.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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



    private fun initView() {

        if(vModel.showAccount()){
//            var x=0
//            vModel.index.observe(requireActivity()){
//                x=it
//            }

            vModel.accountLiveData?.observe(requireActivity()){
                binding.textViewBalance.text=it.balance.toString()
                binding.textViewCardNumber.text=it.cardNumber
                binding.textViewTypeAccount.text=it.accountType.toString()
            }

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
            binding.textViewBalance.text="0"
            binding.textViewCardNumber.text="0"
            binding.textViewTypeAccount.text="0"
            binding.buttonNext.isEnabled=false
            binding.buttonBack.isEnabled=false
        }

    }


}