package com.example.gitissuespull


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gitissuespull.databinding.IssueItemBinding
import com.example.gitissuespull.model.Issue
import java.text.DateFormat
import java.util.*

class IssueAdapter(private val activity: Activity) :
    RecyclerView.Adapter<IssueAdapter.IssueViewHolder>() {

    private var issueList: List<Issue>? = null

    fun setIssueList(issueList: List<Issue>?) {
        this.issueList = issueList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IssueViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding=IssueItemBinding.inflate(inflater, parent, false)
        return IssueViewHolder(binding,activity)
    }

    override fun onBindViewHolder(holder: IssueViewHolder, position: Int) {
        holder.bind(issueList?.get(position)!!)
        holder.itemView.setOnClickListener(View.OnClickListener { v ->
            val context: Context = v.context
            val intent = Intent(context, IssueActivity::class.java)
            intent.putExtra("ISSUE_DATA", issueList!![position])
            context.startActivity(intent)
        })
    }

    override fun getItemCount(): Int {
        return if (issueList == null) 0
        else issueList?.size!!
    }

    class IssueViewHolder(binding: IssueItemBinding, private val activity: Activity) :
        RecyclerView.ViewHolder(binding.root) {
        private val title: TextView = binding.title
        private val description: TextView = binding.description
        private val userImage: ImageView = binding.userImage
        private val userName: TextView = binding.userName
        private val updatedAt: TextView = binding.updatedAt

        fun bind(data: Issue) {

            Glide.with(activity)
                .load(data.user?.avatarUrl)
                .into(userImage)
            userName.text = data.user?.login.toString()
            title.text = data.title.toString();
            if (data.body.toString().length >= 200)
                description.text = data.body.toString().substring(0, 200) + "...read more"
            else
                description.text = data.body.toString()
            if(data.updatedAt!=null) {
                var formatDate: String ="update at " +
                    data.updatedAt!!.substring(5, 10) + "-" +data.updatedAt!!.substring(0, 4)
                   updatedAt.text = formatDate
            }
        }
    }
}