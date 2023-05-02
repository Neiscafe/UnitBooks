package com.example.unitbooks.database

import android.content.Context
import androidx.room.*
import com.example.unitbooks.model.Book
import com.example.unitbooks.util.ListStringTypeConverter

@Database(
    entities = [Book::class], version = 1, exportSchema = false
)
@TypeConverters(ListStringTypeConverter::class)
abstract class RoomDataBase : RoomDatabase() {
    abstract val bookDao: BookDao

    companion object {
        @Volatile
        private var INSTANCE: RoomDataBase? = null

        fun getDatabase(context: Context): RoomDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoomDataBase::class.java,
                    "room_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}