package com.example.gitissuespull.model


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
data class Issue(
  @PrimaryKey
  @SerializedName("id"                       ) var id                    : Int?              = null,
  @SerializedName("node_id"                  ) var nodeId                : String?           = null,
  @SerializedName("number"                   ) var number                : Int?              = null,
  @SerializedName("title"                    ) var title                 : String?           = null,
  @Embedded(prefix = "user_")
  @SerializedName("user"                     ) var user                  : User?             = User(),
  @SerializedName("comments"                 ) var comments              : Int?              = null,
  @SerializedName("created_at"               ) var createdAt             : String?           = null,
  @SerializedName("updated_at"               ) var updatedAt             : String?           = null,
  @SerializedName("closed_at"                ) var closedAt              : String?           = null,
  @SerializedName("body"                     ) var body                  : String?           = null

):Serializable