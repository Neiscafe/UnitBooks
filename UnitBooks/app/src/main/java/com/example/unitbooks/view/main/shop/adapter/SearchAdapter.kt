package com.example.unitbooks.view.main.shop.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.unitbooks.R
import com.example.unitbooks.databinding.CatalogItemBinding
import com.example.unitbooks.model.Book
import com.example.unitbooks.model.BookEntity
import com.example.unitbooks.util.getBookPrice

class SearchAdapter :
    PagingDataAdapter<Book, SearchAdapter.SearchViewHolder>(diffCallback = DiffUtilCallback) {

    private lateinit var clickListener: ClickListener

    inner class SearchViewHolder(val binding: CatalogItemBinding, clickListener: ClickListener) :
        ViewHolder(binding.root) {

        init {
            itemView.let {
                it.setOnClickListener {
                    clickListener.onItemClick(
                        getItem(bindingAdapterPosition)!!, bindingAdapterPosition
                    )
                }
            }
        }

        fun bind(item: Book) {
            binding.tvBookTitle.text = item.title
            binding.tvBookPrice.text = getBookPrice()

            item.thumbnail.let {
                if (it.isBlank()) {
                    Glide.with(itemView).load(R.drawable.r).into(binding.ivImage)
                } else {
                    Glide.with(itemView).load(it).into(binding.ivImage)
                }
            }
        }
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val item = getItem(position)!!
        holder.bind(item)
        holder.setIsRecyclable(false)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(
            CatalogItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false,
            ), clickListener
        )
    }

    fun setClickListener(listener: ClickListener) {
        clickListener = listener
    }

    interface ClickListener {
        fun onItemClick(book: Book, position: Int)
    }

    object DiffUtilCallback : DiffUtil.ItemCallback<Book>() {
        override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem == newItem
        }
    }
}




