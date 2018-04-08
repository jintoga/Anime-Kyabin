package com.example.jintoga.animelist.repository.model.anime

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Anime(
        @PrimaryKey(autoGenerate = true) var id: Long? = null,
        @ColumnInfo(name = "iconURL") @SerializedName("iconURL") var iconURL: String?,
        @ColumnInfo(name = "cur_temp") @SerializedName("cur_temp") var curTemp: Double,
        @ColumnInfo(name = "dtString") @SerializedName("dtString") var dtString: String?,
        @ColumnInfo(name = "cityName") @SerializedName("cityName") var cityName: String?
)