package com.ningsih.aplikasi_desa.ui.home.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ningsih.aplikasi_desa.R
import com.ningsih.aplikasi_desa.databinding.ItemAdapterGaleriBinding
import com.ningsih.aplikasi_desa.network.NetworkConfig
import com.ningsih.aplikasi_desa.response.GaleriItem

class AdapterGaleri (
    private val click: (GaleriItem?)-> Unit) : RecyclerView.Adapter<AdapterGaleri.ViewHolder>(){
    inner class ViewHolder(private val binding: ItemAdapterGaleriBinding) :
        RecyclerView.ViewHolder(binding.root){
        fun onBindItem(galeriItem: GaleriItem?){
            Glide.with(itemView.context)
                .load("${NetworkConfig.BASEURL}galeri/${galeriItem?.gambar}")
                .into(binding.coverGaleri)

            binding.root.setOnClickListener{
                click(galeriItem)
            }
        }
    }

    private val itemListGaleri = mutableListOf<GaleriItem?>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemAdapterGaleriBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBindItem(itemListGaleri?.get(position))
    }

    override fun getItemCount(): Int {
        return itemListGaleri.size
    }

    fun addItem(itemGaleri : List<GaleriItem?>){
        itemListGaleri.clear()
        itemListGaleri.addAll(itemGaleri)
        notifyDataSetChanged()
    }
    }
