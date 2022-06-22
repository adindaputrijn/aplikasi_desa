package com.ningsih.aplikasi_desa.ui.home.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ningsih.aplikasi_desa.databinding.ItemAdapterPotensiBinding
import com.ningsih.aplikasi_desa.response.PotensiItem

class AdapterPotensi (private val click: (PotensiItem?) -> Unit) : RecyclerView.Adapter<AdapterPotensi.ViewHolder>(){
    inner class ViewHolder(private val binding: ItemAdapterPotensiBinding) :
        RecyclerView.ViewHolder(binding.root){
        fun onBindItem(potensiItem: PotensiItem?){
            binding.namaPotensi.text = potensiItem?.judul
            binding.root.setOnClickListener {
                Toast.makeText(binding.root.context, potensiItem?.judul, Toast.LENGTH_SHORT).show()
                click(potensiItem)
            }
        }
    }

    private val itemListPotensi = mutableListOf<PotensiItem?>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterPotensi.ViewHolder {
        return ViewHolder(
            ItemAdapterPotensiBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AdapterPotensi.ViewHolder, position: Int) {
        holder.onBindItem(itemListPotensi?.get(position))
    }

    override fun getItemCount(): Int {
        return itemListPotensi.size
    }

    fun addItem(itemPotensi : List<PotensiItem?>){
        itemListPotensi.clear()
        itemListPotensi.addAll(itemPotensi)
        notifyDataSetChanged()
    }
}