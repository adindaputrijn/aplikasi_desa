package com.ningsih.aplikasi_desa.ui.home.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ningsih.aplikasi_desa.R
import com.ningsih.aplikasi_desa.databinding.ItemAdapterKategoriBinding
import com.ningsih.aplikasi_desa.network.NetworkConfig
import com.ningsih.aplikasi_desa.response.KategoriItem
import com.ningsih.aplikasi_desa.response.LayananItem

class AdapterKategori (private val click: (KategoriItem?) -> Unit): RecyclerView.Adapter<AdapterKategori.ViewHolder>(){

    private val listItemKategori = mutableListOf<KategoriItem?>()

    inner class ViewHolder(private val binding: ItemAdapterKategoriBinding)
        : RecyclerView.ViewHolder(binding.root){

            fun onBindItem(kategoriItem: KategoriItem?){
                binding.namaCover.text = kategoriItem?.namaKategori
                Glide.with(binding.root.context)
                    .load("${NetworkConfig.BASEURL}kategori/${kategoriItem?.gambar}")
                    .into(binding.coverKategori)

                binding.root.setOnClickListener{
                    click(kategoriItem)
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemAdapterKategoriBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBindItem(listItemKategori?.get(position))
    }

    override fun getItemCount(): Int {
        return  listItemKategori.size
    }

    fun addItem(itemList : List<KategoriItem?>){
        listItemKategori.clear()
        listItemKategori.addAll(itemList)
        notifyDataSetChanged()
    }


}