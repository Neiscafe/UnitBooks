package com.example.unitbooks.database

import androidx.room.*
import com.example.unitbooks.model.Book

@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBook(book: Book)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBookList(books: List<Book>)

    @Query("SELECT * FROM table_book")
    suspend fun getHotDealsDb(): List<Book>

    @Query("SELECT * FROM table_book")
    suspend fun getAllBooks(): List<Book>

//    @Delete
//    suspend fun deleteBook()

//    @Update
//    suspend fun updateBook()
}