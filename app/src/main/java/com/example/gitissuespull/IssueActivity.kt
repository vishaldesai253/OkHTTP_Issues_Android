package com.example.gitissuespull

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.gitissuespull.api.IssueService
import com.example.gitissuespull.api.RetrofitApi
import com.example.gitissuespull.databinding.ActivityIssueBinding
import com.example.gitissuespull.model.Comment
import com.example.gitissuespull.model.Issue
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class IssueActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIssueBinding
    lateinit var commentAdapter: CommentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIssueBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecycleView()

        val issue = intent.getSerializableExtra("ISSUE_DATA") as? Issue
        if (issue != null) {
            Log.d("ISSUEDATA", issue.title.toString())
            binding.title.text = issue.title
            binding.description.text = issue.body
            Glide.with(this)
                .load(issue.user?.avatarUrl)
                .into(binding.userImage)
            binding.userName.text = issue.user?.login

            val service = RetrofitApi.getInstance().create(IssueService::class.java)

            issue.number?.let {
                service.getComments(it).enqueue(object : Callback<List<Comment>> {
                    override fun onResponse(
                        call: Call<List<Comment>>?,
                        response: Response<List<Comment>>?
                    ) {

                        if (response?.body() != null && response.body()!!.isNotEmpty()) {
                            Log.d("Commentdata",response.body().toString())
                            commentAdapter.setCommentList(response.body())
                            commentAdapter.notifyDataSetChanged()
                        }
                        else
                            binding.noComment.visibility= View.VISIBLE
                    }
                    override fun onFailure(call: Call<List<Comment>>?, t: Throwable?) {
                        binding.noComment.visibility= View.VISIBLE
                    }
                })
            }
        }
    }

    private fun initRecycleView() {
        binding.commentRecyclerView.layoutManager = LinearLayoutManager(this)
        commentAdapter = CommentAdapter(this)
        binding.commentRecyclerView.adapter = commentAdapter
    }
}
