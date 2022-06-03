package com.ningsih.aplikasi_desa.ui.home.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.ningsih.aplikasi_desa.R
import com.ningsih.aplikasi_desa.databinding.ItemAdapterLayananBinding
import com.ningsih.aplikasi_desa.response.LayananItem

class AdapterLayanan (private val click: (LayananItem?) -> Unit) : RecyclerView.Adapter<AdapterLayanan.ViewHolder>(){
    inner class ViewHolder(private val binding: ItemAdapterLayananBinding) :
        RecyclerView.ViewHolder(binding.root){
            fun onBindItem(layananItem: LayananItem?){
                binding.namaLayanan.text = layananItem?.namaLayanan

                binding.root.setOnClickListener {
                    Toast.makeText(binding.root.context, layananItem?.namaLayanan, Toast.LENGTH_SHORT).show()
                    click(layananItem)
                }
            }
        }

    private val itemListLayanan = mutableListOf<LayananItem?>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterLayanan.ViewHolder {
        return ViewHolder(
            ItemAdapterLayananBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AdapterLayanan.ViewHolder, position: Int) {
        holder.onBindItem(itemListLayanan?.get(position))
    }

    override fun getItemCount(): Int {
        return itemListLayanan.size
    }

    fun addItem(itemLayanan : List<LayananItem?>){
        itemListLayanan.clear()
        itemListLayanan.addAll(itemLayanan)
        notifyDataSetChanged()
    }

}