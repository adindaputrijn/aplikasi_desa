package com.ningsih.aplikasi_desa.ui.berita

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.ningsih.aplikasi_desa.R
import com.ningsih.aplikasi_desa.databinding.ActivityDetailBeritaBinding
import com.ningsih.aplikasi_desa.network.NetworkConfig
import com.ningsih.aplikasi_desa.response.BeritaItem
import com.ningsih.aplikasi_desa.utils.Constant
import com.pixplicity.easyprefs.library.Prefs

class DetailBeritaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBeritaBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBeritaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.back.setOnClickListener {
            onBackPressed()
        }

        val berita = intent.getParcelableExtra<BeritaItem>("id_berita")

        binding.berita.text = berita?.judulBerita
        binding.isiBerita.text = berita?.deskripsiBerita
        Glide.with(this)
            .load("${NetworkConfig.BASEURL}berita/${berita?.gambar}")
            .into(binding.coverBeritaKat2)
    }





}