package com.example.hw13.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.hw13.databinding.FragmentCreateAccountsBinding
import com.example.hw13.models.Account
import com.example.hw13.models.AccountType
import com.example.hw13.viewModels.AccountViewModel
import com.example.hw13.viewModels.CreateAccountViewModel


class CreateAccountsFragment : Fragment() {

    private lateinit var binding: FragmentCreateAccountsBinding
    private val vModel: CreateAccountViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateAccountsBinding.inflate(inflater, container, false)
        return binding.root
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_create_account, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()


    }

    private fun initView() {

//        val listOfLinerLayout = arrayOf(binding.linear1, binding.linear2, binding.linear3,
//            binding.linear4, binding.linear5
//        )
//
//        val listOfETTypeAccount= arrayOf(binding.editTextTypeAccount1,
//            binding.editTextTypeAccount2,binding.editTextTypeAccount3,
//            binding.editTextTypeAccount4,binding.editTextTypeAccount5
//        )
//
//        val listOfETCardNumber = arrayOf(
//            binding.editTextCardNumber1, binding.editTextCardNumber2,
//            binding.editTextCardNumber3, binding.editTextCardNumber4, binding.editTextCardNumber5
//        )
//        val listOfETBalance = arrayOf(
//            binding.editTextBalance1, binding.editTextBalance2,
//            binding.editTextBalance3, binding.editTextBalance4, binding.editTextBalance5
//        )
        val regex = Regex("^\\d+\\.+\\d+\$")
        val pref = requireActivity().getSharedPreferences("share", Context.MODE_PRIVATE)
        val numberOfAccount = pref.getInt("numberOfAccount", 0)
        var x=1
        if (numberOfAccount != 0) {
            if (x==numberOfAccount)
                binding.buttonNext.isEnabled=false
            binding.buttonNext.setOnClickListener {
                x++
                binding.textViewTitle.text="حساب ${x}"
                if (x==numberOfAccount)
                    binding.buttonNext.isEnabled=false
                binding.editTextBalance.setText("")
                binding.editTextCardNumber.setText("")
                binding.editTextTypeAccount.setText("")
            }
            binding.btnRegister.setOnClickListener {

                    when{
                        binding.editTextTypeAccount.text.isNullOrBlank()->binding.editTextTypeAccount.error="نوع حساب را وارد کنید"
                        binding.editTextTypeAccount.text.toString().toInt() !in 1..3 -> binding.editTextTypeAccount.error="نوع حساب اشتباه وارد شده است"

                        binding.editTextCardNumber.text.isNullOrBlank()->binding.editTextCardNumber.error="شماره کارت را وارد کنید"
                        binding.editTextCardNumber.length()!=16 -> binding.editTextCardNumber.error="شماره کارت اشتباه است"

                        binding.editTextBalance.text.isNullOrBlank()->binding.editTextBalance.error="موجودی را وارد کنید"
                        !regex.matches(binding.editTextBalance.text) -> binding.editTextBalance.error="یک عدد اعشاری برای موجودی وارد کنید"

                        else->{
                            val type= when( binding.editTextTypeAccount.text.toString()){
                                "1"-> AccountType.SavingsAccount
                                "2"-> AccountType.ShortTerm
                                else -> AccountType.LongTerm
                            }

                            vModel.listOfAccount.add(Account(0,type,
                                binding.editTextCardNumber.text.toString(),
                                binding.editTextBalance.text.toString().toDouble()))
                            if(!binding.buttonNext.isEnabled)
                                binding.btnRegister.isEnabled=false
                            Toast.makeText(requireActivity(),"ذخیره اطلاعات انجام شد",Toast.LENGTH_SHORT).show()
                        }
                    }
                vModel.setList(vModel.listOfAccount)

        }

    }
    }
}