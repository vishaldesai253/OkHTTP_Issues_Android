package com.example.gitissuespull.api


import com.example.gitissuespull.model.Comment
import com.example.gitissuespull.model.Issue
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface IssueService {

    @GET("issues")
    suspend fun getIssues() : Response<List<Issue>>

    @GET("issues/{id}/comments")
     fun getComments(@Path("id") id:Int):Call<List<Comment>>

}