package com.jintoga.animekyabin.repository.model.anime

import android.arch.persistence.room.*
import com.google.gson.annotations.SerializedName

@Entity(indices = [(Index(name = "id", value = "id", unique = true))])
data class Anime(
        @PrimaryKey(autoGenerate = true) var uid: Long? = null,
        @ColumnInfo(name = "id") @SerializedName("id") var id: Int,
        @ColumnInfo(name = "title_romaji") @SerializedName("title_romaji") var titleRomaji: String?,
        @ColumnInfo(name = "title_english") @SerializedName("title_english") var titleEnglish: String?,
        @ColumnInfo(name = "title_japanese") @SerializedName("title_japanese") var titleJapanese: String?,
        @ColumnInfo(name = "image_url_lge") @SerializedName("image_url_lge") var imageUrl: String?,
        @ColumnInfo(name = "image_url_banner") @SerializedName("image_url_banner") var bannerUrl: String?
) {
    @Ignore
    var hasFadedIn: Boolean = false
}