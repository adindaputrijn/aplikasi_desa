package com.ningsih.aplikasi_desa.ui.home.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView
import com.ningsih.aplikasi_desa.R
import com.ningsih.aplikasi_desa.databinding.ItemAdapterLayananBinding
import com.ningsih.aplikasi_desa.databinding.ItemAdapterProgramBinding
import com.ningsih.aplikasi_desa.response.LayananItem
import com.ningsih.aplikasi_desa.response.ProgramItem

class AdapterProgram(private  val click: (ProgramItem?)-> Unit) : RecyclerView.Adapter<AdapterProgram.ViewHolder>(){
    inner class ViewHolder(private val binding: ItemAdapterProgramBinding) :
        RecyclerView.ViewHolder(binding.root){
        fun onBindItem(programItem: ProgramItem?){
            binding.judulProgram.text = programItem?.namaProgram

            binding.root.setOnClickListener {
                click(programItem)
            }
        }
    }

    private  val itemListProgram = mutableListOf<ProgramItem?>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterProgram.ViewHolder {
        return ViewHolder(
            ItemAdapterProgramBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AdapterProgram.ViewHolder, position: Int) {
        holder.onBindItem(itemListProgram?.get(position))
    }

    override fun getItemCount(): Int {
        return itemListProgram.size
    }

    fun addItem(itemProgram : List<ProgramItem?>){
        itemListProgram.clear()
        itemListProgram.addAll(itemProgram)
        notifyDataSetChanged()
    }

}