package com.example.unitbooks.util

import com.example.unitbooks.model.Book
import com.example.unitbooks.model.BookEntity

class NetworkBookMapper : Mapper<BookEntity, Book> {
    override fun mapFromEntity(entity: BookEntity): Book {
        with(entity) {
            return Book(
                id = id,
                title = volumeInfo.title ?: "",
                subtitle = volumeInfo.subtitle ?: "",
                authors = volumeInfo.authors?: emptyList(),
                publisher = volumeInfo.publisher ?: "",
                publishedData = volumeInfo.publishedData ?: "",
                description = volumeInfo.description ?: "",
                pageCount = volumeInfo.pageCount ?: 0,
                maturityRating = volumeInfo.maturityRating ?: "",
                smallThumbnail = volumeInfo.imageLinks?.smallThumbnail ?: "",
                thumbnail = volumeInfo.imageLinks?.thumbnail ?: "",
                language = volumeInfo.language ?: ""
            )
        }
    }

    override fun mapToEntity(domainModel: Book): BookEntity {
        TODO("Not yet implemented")
    }

    fun fromEntityList(initial: List<BookEntity>): List<Book> {
        return initial.map { mapFromEntity(it) }
    }
}
