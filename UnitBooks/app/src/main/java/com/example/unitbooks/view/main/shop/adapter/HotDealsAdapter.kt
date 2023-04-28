package com.example.unitbooks.view.main.shop.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.unitbooks.R
import com.example.unitbooks.databinding.HotDealsItemBinding
import com.example.unitbooks.model.Book
import com.example.unitbooks.util.getBookPrice

class HotDealsAdapter : Adapter<HotDealsAdapter.HotDealsViewHolder>() {

    private val bookList: MutableList<Book> = mutableListOf()
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

        fun bind(item: Book) {
            binding.tvBookTitle.text = item.title
            binding.tvBookPrice.text = getBookPrice()

            item.thumbnail.let {
                if (it.isBlank()) {
                    Glide.with(itemView).load(R.drawable.r).into(binding.ivImage)
                } else {
                    Glide.with(itemView).load(it).into(binding.ivImage)
                }
            }        }
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
        Log.i("onBindViewHolder", "onBindViewHolder: ${bookList[position].title}")
        holder.bind(bookList[position])
    }

    interface ClickListener {
        fun onItemClick(position: Int, book: Book)
    }

    fun setOnClickListener(listener: ClickListener) {
        clickListener = listener
    }

    fun append(newItems: List<Book>) {
        bookList.addAll(newItems)
        notifyDataSetChanged()
    }

}