package com.ningsih.aplikasi_desa.ui.pengaduan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.ningsih.aplikasi_desa.MainActivity
import com.ningsih.aplikasi_desa.databinding.ActivityListPengaduanBinding
import com.ningsih.aplikasi_desa.databinding.ActivityPotensiBinding
import com.ningsih.aplikasi_desa.ui.home.adapter.AdapterPotensi
import com.ningsih.aplikasi_desa.ui.potensi.DetailPotensiActivity

class ListPengaduanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListPengaduanBinding
    private lateinit var adapterPengaduan: AdapterPengaduan

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListPengaduanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapterPengaduan = AdapterPengaduan {
        }

        binding.acti.apply {
            adapter = adapterPotensi
            layoutManager = LinearLayoutManager(this@PotensiActivity)
        }
        getListPotensi()
    }
}