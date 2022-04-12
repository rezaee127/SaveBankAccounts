import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Account(@PrimaryKey(autoGenerate = true)var id:Int, var accountType:AccountType, var cardNumber:Int, var Balance:Double)

enum class AccountType{
    SavingsAccount , LongTerm , ShortTerm
}