package com.example.gitissuespull.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.gitissuespull.api.IssueService
import com.example.gitissuespull.database.IssueRoomDatabase
import com.example.gitissuespull.model.Issue
import com.example.gitissuespull.utils.NetworkUtils

class IssueRepository(
    private val issueService: IssueService,
    private val issueDatabase: IssueRoomDatabase,
    private val applicationContext: Context
) {

    private val issuesLiveData = MutableLiveData<List<Issue>>()

    val issues: LiveData<List<Issue>>
    get() = issuesLiveData

    suspend fun getIssues(){

        if(NetworkUtils.isInternetAvailable(applicationContext)){
            val result = issueService.getIssues()
            if(result.body() != null){
                issuesLiveData.postValue(result.body())
                issueDatabase.issueDao().insertIssues(result.body()!!)
            }
        }
        else {
            val issues=issueDatabase.issueDao().getIssues()
            issuesLiveData.postValue(issues)
        }
    }
}







