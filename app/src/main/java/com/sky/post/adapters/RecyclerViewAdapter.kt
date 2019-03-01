package com.sky.post.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sky.post.R
import com.sky.post.network.model.Post
import kotlinx.android.synthetic.main.item.view.*


const val HEADER = 0
const val ITEM = 1

interface OnItemClick {
    fun onItemClick(post: Post)
}

class RecyclerViewAdapter(
    private val context: Context,
    private val listener: OnItemClick
) : RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>() {

    private val lstPost: ArrayList<Post> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = if (viewType == HEADER) {
            LayoutInflater.from(context).inflate(R.layout.header, parent, false)
        } else {
            LayoutInflater.from(context).inflate(R.layout.item, parent, false)
        }
        return ItemViewHolder(view, listener)
    }


    override fun getItemViewType(position: Int): Int {
        return if (position == 0)
            HEADER
        else {
            ITEM
        }
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
    class ItemViewHolder(private val view: View, private val listner: OnItemClick) : RecyclerView.ViewHolder(view) {
        fun bind(post: Post) {
            view.postTitle.text = post.title
            view.postBody.text = post.body
            view.setOnClickListener {
                listner.onItemClick(post)
            }
        }
    }

}
