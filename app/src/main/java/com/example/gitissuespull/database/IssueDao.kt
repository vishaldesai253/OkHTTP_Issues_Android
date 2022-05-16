package com.example.gitissuespull.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gitissuespull.model.Comment
import com.example.gitissuespull.model.Issue

@Dao
interface IssueDao {

    @Query("SELECT * FROM issue order by id desc")
    suspend fun getIssues(): List<Issue>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIssues(issue:List<Issue>)

    @Query("DELETE FROM issue")
    suspend fun deleteAllIssues()

    @Query("SELECT * FROM comment where issueUrl like '%issues/'||:issueNumber")
    suspend fun getComments(issueNumber: Int): List<Comment>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComments(comment: List<Comment>)

    @Query("DELETE FROM comment")
    suspend fun deleteAllComments()
}
