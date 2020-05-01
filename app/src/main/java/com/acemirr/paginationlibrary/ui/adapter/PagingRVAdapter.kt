package com.acemirr.paginationlibrary.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.acemirr.paginationlibrary.R
import com.acemirr.paginationlibrary.data.model.News
import com.acemirr.paginationlibrary.utils.LoadingState
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_row.view.*

class PagingRVAdapter():PagedListAdapter<News,RecyclerView.ViewHolder>(cb) {

    companion object{
        const val VIEW_TYPE_ITEM = 1
        const val VIEW_TYPE_LOAD = 2
    }
    private var loadingState = LoadingState.LOADING
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == VIEW_TYPE_ITEM){
            val x = inflater.inflate(R.layout.item_row,parent,false)
            return Holder(x)
        }else{
            val x = inflater.inflate(R.layout.item_load_more,parent,false)
            LoadMoreHolder(x)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is Holder){
            try {
                val news: News? = getItem(holder.adapterPosition)
                news?.let {
                    holder.bindItem(news)
                }
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position < super.getItemCount()) VIEW_TYPE_ITEM else VIEW_TYPE_LOAD
    }
    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasFooter()) 1 else 0
    }

    private fun hasFooter(): Boolean {
        return super.getItemCount() != 0 && loadingState == LoadingState.LOADING
    }

    object cb:DiffUtil.ItemCallback<News>() {
        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem == newItem
        }
    }

    inner class Holder(itemView: View):RecyclerView.ViewHolder(itemView) {
        fun bindItem(news: News) {
            itemView.tvItem.text = news.title
            Picasso.get()
                .load(news.urlToImage)
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .resize(100,100)
                .placeholder(R.drawable.ic_launcher_background)
                .into(itemView.ivItem)
        }
    }

    inner class LoadMoreHolder(view: View):RecyclerView.ViewHolder(view)
}