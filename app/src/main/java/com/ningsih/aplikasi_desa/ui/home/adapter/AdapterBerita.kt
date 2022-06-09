package com.ningsih.aplikasi_desa.ui.home.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ningsih.aplikasi_desa.R
import com.ningsih.aplikasi_desa.databinding.ItemAdapterBeritaBinding
import com.ningsih.aplikasi_desa.network.NetworkConfig
import com.ningsih.aplikasi_desa.response.BeritaItem

class AdapterBerita(private val click: (BeritaItem?)-> Unit) : RecyclerView.Adapter<AdapterBerita.ViewHolder>(){

    private val listItemBerita = mutableListOf<BeritaItem?>()

    inner  class ViewHolder(private val binding: ItemAdapterBeritaBinding)
        : RecyclerView.ViewHolder(binding.root){

            fun onBindItem(beritaItem: BeritaItem?){
                binding.namaBerita.text = beritaItem?.judulBerita
                Glide.with(binding.root.context)
                    .load("${NetworkConfig.BASEURL}berita/${beritaItem?.gambar}")
                    .into(binding.berita)

                binding.root.setOnClickListener {
                    click(beritaItem)
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return  ViewHolder(
            ItemAdapterBeritaBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBindItem(listItemBerita?.get(position))
    }

    override fun getItemCount(): Int {
        return listItemBerita.size
    }

    fun addItem(itemList : List<BeritaItem?>){
        listItemBerita.clear()
        listItemBerita.addAll(itemList)
        notifyDataSetChanged()
    }
}