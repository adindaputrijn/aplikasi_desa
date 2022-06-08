package com.ningsih.aplikasi_desa.ui.pengaduan

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.ningsih.aplikasi_desa.R
import com.ningsih.aplikasi_desa.databinding.ItemAdapterPengaduanBinding
import com.ningsih.aplikasi_desa.databinding.ItemAdapterPotensiBinding
import com.ningsih.aplikasi_desa.response.PengaduanItem
import com.ningsih.aplikasi_desa.response.PotensiItem
import com.ningsih.aplikasi_desa.ui.home.adapter.AdapterPotensi
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class AdapterPengaduan (private val click: (PengaduanItem?) -> Unit) : RecyclerView.Adapter<AdapterPengaduan.ViewHolder>(){
    inner class ViewHolder(private val binding: ItemAdapterPengaduanBinding) :
        RecyclerView.ViewHolder(binding.root){
        fun onBindItem(pengaduanItem: PengaduanItem?){
            binding.namaPengaduan.text = pengaduanItem?.ditambahkanPada

            binding.root.setOnClickListener {
//                Toast.makeText(binding.root.context, pengaduanItem?.ditambahkanPada, Toast.LENGTH_SHORT).show()
                click(pengaduanItem)
            }
        }
    }

    private val itemListPengaduan = mutableListOf<PengaduanItem?>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterPengaduan.ViewHolder {
        return ViewHolder(
            ItemAdapterPengaduanBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBindItem(itemListPengaduan?.get(position))
    }

    override fun getItemCount(): Int {
        return itemListPengaduan.size
    }

    fun addItem(itemPengaduan : List<PengaduanItem?>){
//        itemListPengaduan.clear()
        itemListPengaduan.addAll(itemPengaduan)
        notifyDataSetChanged()
    }

//    fun generateDate(date : String) : String{
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
//            val firstApiFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
//            val date = LocalDate.parse(date, firstApiFormat)
//
//            return "${date.dayOfWeek} - ${date.month}"
//        }
//        return date
//
//    }

}