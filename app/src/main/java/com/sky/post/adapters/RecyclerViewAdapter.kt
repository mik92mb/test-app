package com.sky.post.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sky.post.R
import com.sky.post.network.model.Comment
import com.sky.post.network.model.Post
import kotlinx.android.synthetic.main.item.view.*


class RecyclerViewAdapter(
    private val context: Context
) : RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>() {

    private val lstPost: ArrayList<Post> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return lstPost.size
    }


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(lstPost[position])
    }

    fun clearList() {
        lstPost.clear()
        notifyDataSetChanged()
    }

    fun addAll(list: List<Post>) {
        lstPost.addAll(list)
        notifyDataSetChanged()
    }


    // ********* CLASSE ITEMHOLDER ************
    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(post: Post) {
            view.postTitle.text = post.title
            view.postBody.text = post.body

        }
    }
}
