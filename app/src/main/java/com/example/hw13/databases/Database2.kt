package com.example.hw13.databases





import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.hw13.models.Account

@Database(entities=[Account::class], version = 1)
abstract class Database2 : RoomDatabase() {

    abstract fun accountDao(): AccountDao

    companion object {
        private var INSTANCE: Database2? = null

        fun getMyDataBase(context: Context): Database2? {
            if (INSTANCE == null){
                synchronized(Database2::class){
                    INSTANCE =
                        Room.databaseBuilder(context.applicationContext,
                            Database2::class.java, "DB")
                            .allowMainThreadQueries()
                            .build()
                }
            }
            return INSTANCE
        }


    }

}
