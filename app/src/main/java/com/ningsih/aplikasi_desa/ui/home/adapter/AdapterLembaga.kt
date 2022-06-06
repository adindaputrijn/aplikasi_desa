package com.ningsih.aplikasi_desa.ui.home.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.ningsih.aplikasi_desa.R
import com.ningsih.aplikasi_desa.databinding.ItemAdapterLembagaBinding
import com.ningsih.aplikasi_desa.response.LembagaItem

class AdapterLembaga(private val click: (LembagaItem?) -> Unit): RecyclerView.Adapter<AdapterLembaga.ViewHolder>(){
    inner class ViewHolder(private val binding: ItemAdapterLembagaBinding):
        RecyclerView.ViewHolder(binding.root){
            fun onBindItem(dataItem: LembagaItem?){
                binding.judul.text = dataItem?.namaLembaga

                binding.root.setOnClickListener{
                    Toast.makeText(binding.root.context, dataItem?.namaLembaga, Toast.LENGTH_SHORT).show()
                    click(dataItem)
                }
            }
        }
    private val itemListLembaga = mutableListOf<LembagaItem?>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterLembaga.ViewHolder {
        return ViewHolder(
            ItemAdapterLembagaBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
        )
    }

    override fun onBindViewHolder(holder: AdapterLembaga.ViewHolder, position: Int) {
        holder.onBindItem(itemListLembaga?.get(position))
    }

    override fun getItemCount(): Int {
        return itemListLembaga.size
    }

    fun addItem(itemLembaga : List<LembagaItem?>){
        itemListLembaga.clear()
        itemListLembaga.addAll(itemLembaga)
        notifyDataSetChanged()
    }

}