package com.ningsih.aplikasi_desa.ui.home.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ningsih.aplikasi_desa.R
import com.ningsih.aplikasi_desa.databinding.ItemAdapterKategoriBeritaBinding
import com.ningsih.aplikasi_desa.network.NetworkConfig
import com.ningsih.aplikasi_desa.response.BeritaItem

class AdapterKategoriBerita (private val click: (BeritaItem?)-> Unit) : RecyclerView.Adapter<AdapterKategoriBerita.ViewHolder>(){
    inner  class ViewHolder(private val binding: ItemAdapterKategoriBeritaBinding):
        RecyclerView.ViewHolder(binding.root){
            fun onBindItem(beritaItem: BeritaItem?){
                binding.judulBerita.text = beritaItem?.judulBerita
                binding.tglBerita.text = beritaItem?.tanggal
                Glide.with(binding.root.context)
                    .load("${NetworkConfig.BASEURL}berita/${beritaItem?.gambar}")
                    .into(binding.coverBeritaKat)

                binding.root.setOnClickListener {
                    click(beritaItem)
                }
            }
        }

    private  val itemListBeritaKat = mutableListOf<BeritaItem?>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterKategoriBerita.ViewHolder {
        return ViewHolder(
            ItemAdapterKategoriBeritaBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AdapterKategoriBerita.ViewHolder, position: Int) {
        holder.onBindItem(itemListBeritaKat?.get(position))
    }

    override fun getItemCount(): Int {
        return itemListBeritaKat.size
    }

    fun addItem(itemBeritaKat : List<BeritaItem?>){
        itemListBeritaKat.clear()
        itemListBeritaKat.addAll(itemBeritaKat)
        notifyDataSetChanged()
    }

}