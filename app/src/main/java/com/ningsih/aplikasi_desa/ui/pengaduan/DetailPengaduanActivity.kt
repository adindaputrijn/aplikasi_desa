package com.ningsih.aplikasi_desa.ui.pengaduan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ningsih.aplikasi_desa.databinding.ActivityDetailPengaduanBinding
import com.ningsih.aplikasi_desa.response.PengaduanItem

class DetailPengaduanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailPengaduanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailPengaduanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val idPengaduan = intent.getParcelableExtra<PengaduanItem>("id_pengaduan")

        binding.namaUser.text = idPengaduan?.ditambahkanOleh
        binding.isiPengaduan.text = idPengaduan?.isiPengaduan
    }
}