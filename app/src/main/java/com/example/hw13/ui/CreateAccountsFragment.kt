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


class CreateAccountsFragment : Fragment() {

    private lateinit var binding: FragmentCreateAccountsBinding
    private val vModel: AccountViewModel by activityViewModels()
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

        val listOfLinerLayout = arrayOf(binding.linear1, binding.linear2, binding.linear3,
            binding.linear4, binding.linear5
        )

        val listOfETTypeAccount= arrayOf(binding.editTextTypeAccount1,
            binding.editTextTypeAccount2,binding.editTextTypeAccount3,
            binding.editTextTypeAccount4,binding.editTextTypeAccount5
        )

        val listOfETCardNumber = arrayOf(
            binding.editTextCardNumber1, binding.editTextCardNumber2,
            binding.editTextCardNumber3, binding.editTextCardNumber4, binding.editTextCardNumber5
        )
        val listOfETBalance = arrayOf(
            binding.editTextBalance1, binding.editTextBalance2,
            binding.editTextBalance3, binding.editTextBalance4, binding.editTextBalance5
        )

        var flag=false
        val regex = Regex("^\\d+\\.+\\d+\$")
        val pref = requireActivity().getSharedPreferences("share", Context.MODE_PRIVATE)
        val numberOfAccount = pref.getInt("numberOfAccount", 0)
        if (numberOfAccount != 0) {
            for (i in numberOfAccount until listOfLinerLayout.size) {
                listOfLinerLayout[i].visibility = View.GONE
            }
            binding.btnRegister.setOnClickListener {
                for (i in 0 until numberOfAccount) {
                    flag=false
                    when{

                        listOfETTypeAccount[i].text.isNullOrBlank()->listOfETTypeAccount[i].error="نوع حساب را وارد کنید"
                        listOfETTypeAccount[i].text.toString().toInt() !in 1..3 -> listOfETTypeAccount[i].error="نوع حساب اشتباه وارد شده است"

                        listOfETCardNumber[i].text.isNullOrBlank()->listOfETCardNumber[i].error="شماره کارت را وارد کنید"
                        listOfETCardNumber[i].length()!=16 -> listOfETCardNumber[i].error="شماره کارت اشتباه است"

                        listOfETBalance[i].text.isNullOrBlank()->listOfETBalance[i].error="موجودی را وارد کنید"
                        !regex.matches(listOfETBalance[i].text) -> listOfETBalance[i].error="یک عدد اعشاری برای موجودی وارد کنید"

                        else->{
                            val type= when( listOfETTypeAccount[i].toString()){
                                "1"-> AccountType.SavingsAccount
                                "2"-> AccountType.ShortTerm
                                else -> AccountType.LongTerm
                            }

                            vModel.listOfAccounts.add(Account(i+1,type,
                                listOfETCardNumber[i].text.toString(),
                                listOfETBalance[i].text.toString().toDouble()))
                            flag=true
                        }
                    }
                }
                vModel.setList(vModel.listOfAccounts)
                if(flag){
                    Toast.makeText(requireActivity(),"ذخیره اطلاعات انجام شد",Toast.LENGTH_SHORT).show()
                    Toast.makeText(requireActivity(),"${vModel.getAllAccounts()?.get(1)?.balance}",Toast.LENGTH_SHORT).show()
                }

            }


        }



    }


}