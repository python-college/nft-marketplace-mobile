package ru.technosopher.nftmarketplaceapp.marketplace.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.technosopher.nftmarketplaceapp.R
import ru.technosopher.nftmarketplaceapp.databinding.ItemNftBinding
import ru.technosopher.nftmarketplaceapp.marketplace.domain.entities.NftEntity

class NftItemAdapter : RecyclerView.Adapter<NftItemAdapter.NftItemViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<NftEntity>() {
        override fun areItemsTheSame(oldItem: NftEntity, newItem: NftEntity): Boolean {
            return oldItem.address == newItem.address
        }

        override fun areContentsTheSame(oldItem: NftEntity, newItem: NftEntity): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    private var onItemClickListener: ((NftEntity) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NftItemViewHolder {
        return NftItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_nft,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun setOnItemClickListener(listener: (NftEntity) -> Unit) {
        onItemClickListener = listener
    }

    override fun onBindViewHolder(holder: NftItemViewHolder, position: Int) {
        val nft = differ.currentList[position]
        holder.itemView.setOnClickListener {
            onItemClickListener?.let { it(nft) }
        }
        holder.bindData(nft)
    }

    class NftItemViewHolder(private val itemNftBinding: ItemNftBinding) : RecyclerView.ViewHolder(itemNftBinding.root){
        fun bindData(nftEntity: NftEntity) {
            itemNftBinding.nft = nftEntity
            itemNftBinding.executePendingBindings()
        }
    }
}