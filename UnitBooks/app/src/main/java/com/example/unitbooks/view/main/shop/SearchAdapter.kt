package com.example.unitbooks.view.main.shop

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.unitbooks.R
import com.example.unitbooks.databinding.CatalogItemBinding
import com.example.unitbooks.model.BookItem
import com.example.unitbooks.util.getBookPrice

class SearchAdapter :
    PagingDataAdapter<BookItem, SearchAdapter.SearchViewHolder>(diffCallback = DiffUtilCallback) {

    private lateinit var clickListener: ClickListener

    inner class SearchViewHolder(val binding: CatalogItemBinding, clickListener: ClickListener) :
        ViewHolder(binding.root) {

        init {
            itemView.let {
                it.setOnClickListener {
                    clickListener.onItemClick(getItem(bindingAdapterPosition)!!, bindingAdapterPosition)
                }
            }
        }

        fun bind(bookItem: BookItem) {
            val volumeInfo = bookItem.volumeInfo
            binding.tvBookTitle.text = volumeInfo.title
            binding.tvBookPrice.text = getBookPrice()

            volumeInfo.imageLinks?.thumbnail?.let {
                Glide.with(itemView).load(it).into(binding.ivImage)
            } ?: Glide.with(itemView).load(R.drawable.r)
                .into(binding.ivImage)
        }

    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val bookItem = getItem(position)!!
        holder.bind(bookItem)
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
        this.clickListener = listener
    }

    interface ClickListener {
        fun onItemClick(bookItem: BookItem, position: Int)
    }

    object DiffUtilCallback : DiffUtil.ItemCallback<BookItem>() {
        override fun areItemsTheSame(oldItem: BookItem, newItem: BookItem): Boolean {
            return oldItem.apiId == newItem.apiId
        }

        override fun areContentsTheSame(oldItem: BookItem, newItem: BookItem): Boolean {
            return oldItem == newItem
        }
    }
}




