package com.example.gitissuespull.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Reactions (

  @SerializedName("url"         ) var url        : String? = null,
  @SerializedName("total_count" ) var totalCount : Int?    = null,
  @SerializedName("+1"          ) var plus1         : Int?    = null,
  @SerializedName("-1"          ) var minus1         : Int?    = null,
  @SerializedName("laugh"       ) var laugh      : Int?    = null,
  @SerializedName("hooray"      ) var hooray     : Int?    = null,
  @SerializedName("confused"    ) var confused   : Int?    = null,
  @SerializedName("heart"       ) var heart      : Int?    = null,
  @SerializedName("rocket"      ) var rocket     : Int?    = null,
  @SerializedName("eyes"        ) var eyes       : Int?    = null

):Serializable