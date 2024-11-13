package ru.technosopher.nftmarketplaceapp.marketplace.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.technosopher.nftmarketplaceapp.R
import ru.technosopher.nftmarketplaceapp.databinding.ItemCollectionBinding
import ru.technosopher.nftmarketplaceapp.marketplace.domain.entities.NftCollectionEntity

class NftAdapter : RecyclerView.Adapter<NftAdapter.NftCollectionViewHolder>(){

    private val differCallback = object : DiffUtil.ItemCallback<NftCollectionEntity>() {
        override fun areItemsTheSame(oldItem: NftCollectionEntity, newItem: NftCollectionEntity): Boolean {
            return oldItem.address == newItem.address
        }

        override fun areContentsTheSame(oldItem: NftCollectionEntity, newItem: NftCollectionEntity): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NftCollectionViewHolder {
        return NftCollectionViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_collection,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }



    override fun onBindViewHolder(holder: NftCollectionViewHolder, position: Int) {
        val collection = differ.currentList[position]
        holder.bindData(collection)
    }

    class NftCollectionViewHolder(private val itemCollectionBinding: ItemCollectionBinding) : RecyclerView.ViewHolder(itemCollectionBinding.root) {
        fun bindData(collectionEntity: NftCollectionEntity) {
            itemCollectionBinding.collection = collectionEntity
            itemCollectionBinding.executePendingBindings()
        }
    }
}