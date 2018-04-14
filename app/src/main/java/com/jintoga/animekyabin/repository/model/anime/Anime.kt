package com.jintoga.animekyabin.repository.model.anime

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Anime(
        @PrimaryKey(autoGenerate = true) var uid: Long? = null,
        @ColumnInfo(name = "id") @SerializedName("id") var id: Int,
        @ColumnInfo(name = "title_romaji") @SerializedName("title_romaji") var titleRomaji: String?,
        @ColumnInfo(name = "title_english") @SerializedName("title_english") var titleEnglish: String?,
        @ColumnInfo(name = "title_japanese") @SerializedName("title_japanese") var titleJapanese: String?,
        @ColumnInfo(name = "image_url_lge") @SerializedName("image_url_lge") var imageUrl: String?
)