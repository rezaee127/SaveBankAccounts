package com.example.hw13.repository

data class Account(var id:Int, var accountType:AccountType, var cardNumber:Int,var Balance:Double)

enum class AccountType{
     SavingsAccount , LongTerm , ShortTerm
}

object Repository {
    var numberOfAccount=1
    var editProfileInfoFlag=false
    var listOfAccount=ArrayList<Account>()



}