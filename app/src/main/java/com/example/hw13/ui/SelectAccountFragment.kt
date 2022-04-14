package com.example.hw13.ui

import com.example.hw13.viewModels.SelectAccountViewModel
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.hw13.databinding.FragmentSelectAccountBinding
import com.example.hw13.models.AccountType


class SelectAccountFragment : Fragment() {
    private lateinit var  binding : FragmentSelectAccountBinding
    private val vModel: SelectAccountViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSelectAccountBinding.inflate (inflater, container, false)
        return binding.root
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_select_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    @SuppressLint("SetTextI18n")
    private fun initView() {

        if(vModel.getAllAccounts().size!=0) {
            binding.button.setOnClickListener {
                if (binding.editTextCardNumber.length() != 16)
                    binding.editTextCardNumber.error = "شماره کارت اشتباه است"
                else if (vModel.getCardNumber(binding.editTextCardNumber.text.toString())){
                    val cardNumber=binding.editTextCardNumber.text.toString()

                    vModel.accountLiveData=vModel.getAccountByCardNumber(cardNumber)
                    vModel.accountLiveData.observe(requireActivity()){
                        val x= when(it.accountType){
                            AccountType.SavingsAccount -> "پس انداز"
                            AccountType.LongTerm -> "بلند مدت"
                            else -> "کوتاه مدت"
                        }

                        binding.textViewTypeAccount.text="نوع حساب : $x"
                        binding.textViewBalance.text="موجودی : ${it.balance}"
                    }

//                    binding.textViewTypeAccount.text="نوع حساب : ${vModel.getAccountTypeByCardNumber(cardNumber).toString()}"
//                    binding.textViewBalance.text="موجودی : ${vModel.getBalanceByCardNumber(cardNumber).toString()}"


//                    binding.textViewTypeAccount.text="نوع حساب : ${vModel.getAccountTypeByCardNumberLiveData(cardNumber).value}"
//                    binding.textViewBalance.text="موجودی : ${vModel.getBalanceByCardNumberLiveData(cardNumber).value}"


                }else{
                    binding.textViewBalance.text="شماره کارت پیدا نشد"
                    binding.textViewTypeAccount.text=""
                }
            }

        }else {
            binding.textViewBalance.text = "دیتا بیس خالی است"
            binding.button.isEnabled = false
            binding.editTextCardNumber.isEnabled = false
        }
    }


}