package com.example.unitbooks.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.unitbooks.model.Book

@Dao
interface BookDao {
//    @Insert
//    suspend fun insertBook()

    @Query("SELECT * FROM table_book")
    fun getAllBooks(): List<Book>

//    @Delete
//    suspend fun deleteBook()

//    @Update
//    suspend fun updateBook()
}