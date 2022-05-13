package com.example.gitissuespull


import android.annotation.SuppressLint
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
import com.example.gitissuespull.databinding.CommentItemBinding
import com.example.gitissuespull.databinding.IssueItemBinding
import com.example.gitissuespull.model.Comment

class CommentAdapter(private val activity: Activity) :
    RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

    private var commentList: List<Comment>? = null

    fun setCommentList(commentList: List<Comment>?) {
        this.commentList = commentList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CommentItemBinding.inflate(inflater, parent, false)
        return CommentViewHolder(binding, activity)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(commentList?.get(position)!!)
        holder.itemView.setOnClickListener(View.OnClickListener { v ->
            val context: Context = v.context
            val intent = Intent(context, IssueActivity::class.java)
            intent.putExtra("ISSUE_DATA", commentList!![position])
            context.startActivity(intent)
        })
    }

    override fun getItemCount(): Int {
        return if (commentList == null) 0
        else commentList?.size!!
    }

    class CommentViewHolder(binding: CommentItemBinding, private val activity: Activity) :
        RecyclerView.ViewHolder(binding.root) {
        private val description: TextView = binding.description
        private val userImage: ImageView = binding.userImage
        private val userName: TextView = binding.userName
        private val commentedOn: TextView = binding.commentedOn

        @SuppressLint("SetTextI18n")
        fun bind(data: Comment) {

            Glide.with(activity)
                .load(data.user?.avatarUrl)
                .into(userImage)
            userName.text = data.user?.login.toString()
            description.text = data.body.toString()
            commentedOn.text ="Commented on "+ data.updatedAt?.substring(0,10)
        }
    }
}
