package com.example.gitissuespull.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.gitissuespull.model.Issue
import com.example.gitissuespull.repository.IssueRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: IssueRepository) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO){
            repository.getIssues()
        }
    }

    val issues : LiveData<List<Issue>>
    get() = repository.issues
}