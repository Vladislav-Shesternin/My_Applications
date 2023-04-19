package com.pharhaslo.slo7.fragment.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.pharhaslo.slo7.databinding.ProductPreviewItemBinding

import com.squareup.picasso.Picasso
import com.pharhaslo.slo7.model.entity.Product

class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>(){
   inner class ProductViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
       var binding : ProductPreviewItemBinding = ProductPreviewItemBinding.bind(itemView)
   }

    val differ = AsyncListDiffer(this, object : DiffUtil.ItemCallback<Product>(){

        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean =
            oldItem.productUrl == newItem.productUrl

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean =
            oldItem == newItem
    })


    var onItemClickListener : ((Product) -> Unit)? = null

    var onPlayClickListener : ((Product) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = ProductPreviewItemBinding.inflate(inflater, parent, false)
        val view = binding.root

        val holder = ProductViewHolder(view)

        holder.binding.buttonPlay.setOnClickListener {
            val casino = differ.currentList[holder.adapterPosition]
            onPlayClickListener?.let { it(casino) }
        }

        holder.itemView.setOnClickListener {
            val casino = differ.currentList[holder.adapterPosition]
            onItemClickListener?.let { it(casino) }
        }
        return holder
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = differ.currentList[position]
        holder.binding.apply {
            Picasso.get()
                .load(product.productImageSmall[0])
                .into(imageView3)
        }
    }

    override fun getItemCount(): Int = differ.currentList.size
}
