package com.example.gitissuespull.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
data class Comment (

    @SerializedName("url"                      ) var url                   : String?    = null,
    @SerializedName("html_url"                 ) var htmlUrl               : String?    = null,
    @SerializedName("issue_url"                ) var issueUrl              : String?    = null,
    @PrimaryKey
    @SerializedName("id"                       ) var id                    : Int?       = null,
    @SerializedName("node_id"                  ) var nodeId                : String?    = null,
    @Embedded(prefix = "user_")
    @SerializedName("user"                     ) var user                  : User?      = User(),
    @SerializedName("created_at"               ) var createdAt             : String?    = null,
    @SerializedName("updated_at"               ) var updatedAt             : String?    = null,
    @SerializedName("body"                     ) var body                  : String?    = null,

):Serializable