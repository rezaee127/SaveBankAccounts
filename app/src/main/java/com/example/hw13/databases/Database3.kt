package com.example.hw13.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.hw13.models.Account


@Database(entities = [Account::class],version=2)
abstract class Database3 : RoomDatabase() {

    abstract fun accountDao(): AccountDao

    companion object {
        private var INSTANCE: Database3? = null

        fun getMyDataBase(context: Context): Database3? {
            if (INSTANCE == null){
                synchronized(Database3::class){
                    INSTANCE =
                        Room.databaseBuilder(context.applicationContext,
                            Database3::class.java, "DB")
                            .allowMainThreadQueries()
                            .build()
                }
            }
            return INSTANCE
        }



        fun destroyDataBase(){
            INSTANCE = null
        }

    }

}
