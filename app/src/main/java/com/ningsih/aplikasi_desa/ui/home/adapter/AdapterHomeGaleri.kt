//package com.ningsih.aplikasi_desa.ui.home.adapter
//
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
//import com.ningsih.aplikasi_desa.R
//import com.ningsih.aplikasi_desa.databinding.ItemAdapterBeritaBinding
//import com.ningsih.aplikasi_desa.databinding.ItemAdapterGaleriBinding
//import com.ningsih.aplikasi_desa.databinding.ItemAdapterHomeGaleriBinding
//import com.ningsih.aplikasi_desa.network.NetworkConfig
//import com.ningsih.aplikasi_desa.response.BeritaItem
//import com.ningsih.aplikasi_desa.response.GaleriItem
//
//class AdapterHomeGaleri : RecyclerView.Adapter<AdapterHomeGaleri.ViewHolder>() {
//
//    private val listItemGaleri = mutableListOf<AdapterHomeGaleri?>()
//
//    inner  class ViewHolder(private val binding: ItemAdapterHomeGaleriBinding)
//        : RecyclerView.ViewHolder(binding.root){
//
//        fun onBindItem(galeriItem: GaleriItem?){
//
//            Glide.with(binding.root.context)
//                .load("${NetworkConfig.BASEURL}galeri/${galeriItem?.gambar}")
//                .into(binding.galeri)
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        return  ViewHolder(
//            ItemAdapterHomeGaleriBinding.inflate(
//                LayoutInflater.from(parent.context),
//                parent,
//                false
//            )
//        )
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.onBindItem(listItemGaleri?.get(position))
//    }
//
//    override fun getItemCount(): Int {
//        return listItemGaleri.size
//    }
//
//    fun addItem(itemHomeGaleri : List<GaleriItem?>){
//        listItemGaleri.clear()
//        listItemGaleri.addAll(itemHomeGaleri)
//        notifyDataSetChanged()
//    }
//
//}