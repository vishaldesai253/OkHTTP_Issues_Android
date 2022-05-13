package com.example.gitissuespull

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.gitissuespull.api.IssueService
import com.example.gitissuespull.api.RetrofitHelper

import com.example.gitissuespull.databinding.ActivityMainBinding

import com.example.gitissuespull.repository.IssueRepository
import com.example.gitissuespull.viewmodels.MainViewModel
import com.example.gitissuespull.viewmodels.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel
    lateinit var recyclerAdapter: IssueAdapter
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecycleView()

        initViewModel()
    }

    private fun initRecycleView() {
        binding.issueRecyclerView.layoutManager = LinearLayoutManager(this)
        recyclerAdapter = IssueAdapter(this)
        binding.issueRecyclerView.adapter = recyclerAdapter
    }

    private fun initViewModel() {

        val issueService = RetrofitHelper.getInstance().create(IssueService::class.java)
        val repository = IssueRepository(issueService, applicationContext)

        mainViewModel =
            ViewModelProvider(this, MainViewModelFactory(repository))[MainViewModel::class.java]

        mainViewModel.issues.observe(this, Observer {
            Toast.makeText(this@MainActivity, it.size.toString(), Toast.LENGTH_SHORT).show()
            recyclerAdapter.setIssueList(it)
            recyclerAdapter.notifyDataSetChanged()

        })
    }
}

