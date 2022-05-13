package com.example.gitissuespull.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Comment (

    @SerializedName("url"                      ) var url                   : String?    = null,
    @SerializedName("html_url"                 ) var htmlUrl               : String?    = null,
    @SerializedName("issue_url"                ) var issueUrl              : String?    = null,
    @SerializedName("id"                       ) var id                    : Int?       = null,
    @SerializedName("node_id"                  ) var nodeId                : String?    = null,
    @SerializedName("user"                     ) var user                  : User?      = User(),
    @SerializedName("created_at"               ) var createdAt             : String?    = null,
    @SerializedName("updated_at"               ) var updatedAt             : String?    = null,
    @SerializedName("author_association"       ) var authorAssociation     : String?    = null,
    @SerializedName("body"                     ) var body                  : String?    = null,
    @SerializedName("reactions"                ) var reactions             : Reactions? = Reactions(),
    @SerializedName("performed_via_github_app" ) var performedViaGithubApp : String?    = null

):Serializable