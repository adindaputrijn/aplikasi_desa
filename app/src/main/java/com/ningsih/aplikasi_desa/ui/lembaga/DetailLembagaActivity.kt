package com.ningsih.aplikasi_desa.ui.lembaga

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ningsih.aplikasi_desa.R
import com.ningsih.aplikasi_desa.databinding.ActivityDetailLayananBinding
import com.ningsih.aplikasi_desa.databinding.ActivityDetailLembagaBinding
import com.ningsih.aplikasi_desa.databinding.ActivityDetailPengaduanBinding
import com.ningsih.aplikasi_desa.response.LayananItem
import com.ningsih.aplikasi_desa.response.LembagaItem
import com.ningsih.aplikasi_desa.response.PengaduanItem

class DetailLembagaActivity : AppCompatActivity() {

    private  lateinit var binding: ActivityDetailLembagaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailLembagaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.back.setOnClickListener {
            onBackPressed()
        }

        val idLembaga = intent.getParcelableExtra<LembagaItem>("id_lembaga")

        binding.lembaga.text = idLembaga?.namaLembaga
        binding.isiLembaga.text = idLembaga?.deskripsi
    }
}
