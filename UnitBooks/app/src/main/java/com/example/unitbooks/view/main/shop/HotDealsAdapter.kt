package com.example.unitbooks.view.main.shop

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.unitbooks.R
import com.example.unitbooks.databinding.HotDealsItemBinding
import com.example.unitbooks.model.BookItem
import com.example.unitbooks.model.BookResponse
import com.example.unitbooks.util.getBookPrice

class HotDealsAdapter : Adapter<HotDealsAdapter.HotDealsViewHolder>() {

    private val bookList: MutableList<BookItem> = mutableListOf()
    private lateinit var clickListener: ClickListener

    inner class HotDealsViewHolder(val binding: HotDealsItemBinding, clickListener: ClickListener) :
        ViewHolder(binding.root) {

        init {
            itemView.let {
                it.setOnClickListener {
                    clickListener.onItemClick(
                        bindingAdapterPosition, bookList[bindingAdapterPosition]
                    )
                }
            }
        }

        fun bind(book: BookItem) {
            val volumeInfo = book.volumeInfo
            binding.tvBookTitle.text = volumeInfo.title
            binding.tvBookPrice.text = getBookPrice()

            volumeInfo.imageLinks?.thumbnail?.let {
                Glide.with(itemView).load(it).into(binding.ivImage)
            } ?: Glide.with(itemView).load(R.drawable.r).into(binding.ivImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotDealsViewHolder {
        return HotDealsViewHolder(
            HotDealsItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), clickListener
        )
    }

    override fun getItemCount(): Int {

        return bookList.size
    }

    override fun onBindViewHolder(holder: HotDealsViewHolder, position: Int) {
        Log.i("onBindViewHolder", "onBindViewHolder: ${bookList[position].volumeInfo.title}")
        holder.bind(bookList[position])
    }

    interface ClickListener {
        fun onItemClick(position: Int, book: BookItem)
    }

    fun setOnClickListener(listener: ClickListener) {
        this.clickListener = listener
    }

    fun append(newItems: List<BookItem>) {
        bookList.addAll(newItems)
        notifyDataSetChanged()
    }

}