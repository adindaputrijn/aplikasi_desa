package com.ningsih.aplikasi_desa.ui.layanan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ningsih.aplikasi_desa.R
import com.ningsih.aplikasi_desa.databinding.ActivityDetailLayananBinding
import com.ningsih.aplikasi_desa.databinding.ActivityLayananBinding
import com.ningsih.aplikasi_desa.response.LayananItem

class DetailLayananActivity : AppCompatActivity() {

    private  lateinit var binding: ActivityDetailLayananBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailLayananBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.back.setOnClickListener {
            onBackPressed()
        }

        val idLayanan = intent.getParcelableExtra<LayananItem>("id_layanan")

        binding.layanan.text = idLayanan?.namaLayanan
        binding.isiLayanan.text = idLayanan?.deskripsiLayanan
    }
}