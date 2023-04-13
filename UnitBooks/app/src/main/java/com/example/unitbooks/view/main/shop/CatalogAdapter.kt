package com.example.unitbooks.view.main.shop

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.unitbooks.databinding.CatalogItemBinding
import com.example.unitbooks.model.BookItem

class CatalogAdapter() : PagingDataAdapter<BookItem, CatalogAdapter.CatalogViewHolder>(DiffUtilCallback) {

    private lateinit var clickListener: ClickListener

    inner class CatalogViewHolder(
        val binding: CatalogItemBinding, listener: ClickListener
    ) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.let {
                it.setOnClickListener {
                    listener.onItemClick(
                        getItem(bindingAdapterPosition)!!,
                        bindingAdapterPosition
                    )
                }
            }
        }

        fun bind(bookDetailsElement: BookItem) {
            binding.tvBookTitle.text = bookDetailsElement.volumeInfo.title
//            Glide.with(itemView).load(bookDetailsElement.volumeInfo.imageLinks).into(binding.ivImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogViewHolder {
        return CatalogViewHolder(
            CatalogItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            ), clickListener
        )
    }

    override fun onBindViewHolder(holder: CatalogViewHolder, position: Int) {
        val bookElement = getItem(position)!!
        holder.bind(bookElement)
        holder.setIsRecyclable(false)
    }

    interface ClickListener {
        fun onItemClick(volumeInfoElement: BookItem, position: Int)
    }

    fun setClickListener(listener: ClickListener) {
        clickListener = listener
    }

    object DiffUtilCallback: DiffUtil.ItemCallback<BookItem>(){
        override fun areItemsTheSame(oldItem: BookItem, newItem: BookItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: BookItem, newItem: BookItem): Boolean {
            return oldItem == newItem
        }

    }


}