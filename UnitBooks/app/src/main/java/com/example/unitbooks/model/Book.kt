package com.example.unitbooks.model

data class BookResponse (
    val kind: String,
    val totalItems: Int,
    val items: List<BookItem>
        )

data class BookItem(
    val kind: String,
    val id: String,
    val etag: String,
    val selfLink: String,
    val volumeInfo: BookInfo
)

data class BookInfo(
    val title: String,
    val subtitle: String,
    val authors: List<String>,
    val publisher: String,
    val publishedData: String,
    val description: String,
    val pageCount: Int,
    val maturityRating: String,
    val imageLinks: BookImage,
    val language: String
)

data class BookImage (
    val thumbnail: String,
    val smallThumbnail: String
)
