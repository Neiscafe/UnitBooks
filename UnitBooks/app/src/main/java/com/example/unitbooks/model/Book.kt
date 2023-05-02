package com.example.unitbooks.model

import androidx.room.Entity
import androidx.room.PrimaryKey

data class BookResponse(
    val kind: String, val totalItems: Int, val items: List<BookEntity>
)
data class BookEntity(
    val kind: String,
    val id: String,
    val etag: String,
    val selfLink: String,
    val volumeInfo: VolumeInfo
)
data class VolumeInfo(
    val title: String?,
    val subtitle: String?,
    val authors: List<String>?,
    val publisher: String?,
    val publishedData: String?,
    val description: String?,
    val pageCount: Int?,
    val maturityRating: String?,
    val imageLinks: ImageLinks?,
    val language: String?
)
data class ImageLinks(
    val thumbnail: String?, val smallThumbnail: String?
)

@Entity(tableName = "table_book")
data class Book(
    @PrimaryKey val id: String,
    val title: String,
    val subtitle: String,
    val authors: List<String>,
    val publisher: String,
    val publishedData: String,
    val description: String,
    val pageCount: Int,
    val maturityRating: String,
    val thumbnail: String,
    val smallThumbnail: String,
    val language: String
)
