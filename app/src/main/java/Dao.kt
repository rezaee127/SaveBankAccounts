import androidx.room.*

@Dao
interface AccountDao {

   // @Query("SELECT Account.balance")

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg account: Account)

    @Delete
    fun deleteAll(vararg account: Account)

}