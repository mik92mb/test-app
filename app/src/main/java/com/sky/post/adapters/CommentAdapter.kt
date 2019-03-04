package com.sky.post.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sky.post.R
import com.sky.post.network.model.Comment
import kotlinx.android.synthetic.main.item_comment.view.*

class CommentAdapter(val context: Context) : RecyclerView.Adapter<CommentAdapter.ItemCommentHolder>() {

    private val comments : ArrayList<Comment> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemCommentHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_comment, parent, false)
        return ItemCommentHolder(view)
    }

    override fun getItemCount(): Int {
        return comments.size
    }

    fun addComments(lst: List<Comment>) {
        comments.addAll(lst)
        notifyDataSetChanged()
    }

    fun clearAll(){
        comments.clear()
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ItemCommentHolder, position: Int) {
        holder.bind(comments[position])
    }


    class ItemCommentHolder(private val view:View) : RecyclerView.ViewHolder(view) {

        fun bind(comment: Comment) {
            view.commentName.text = comment.name
            view.commentEmail.text = comment.email
            view.commentBody.text = comment.body
        }
    }
}



