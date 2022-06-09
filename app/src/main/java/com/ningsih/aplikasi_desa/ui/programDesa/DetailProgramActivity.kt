package com.ningsih.aplikasi_desa.ui.programDesa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.ningsih.aplikasi_desa.R
import com.ningsih.aplikasi_desa.databinding.ActivityDetailBeritaBinding
import com.ningsih.aplikasi_desa.databinding.ActivityDetailProgramBinding
import com.ningsih.aplikasi_desa.network.NetworkConfig
import com.ningsih.aplikasi_desa.response.BeritaItem
import com.ningsih.aplikasi_desa.response.ProgramItem

class DetailProgramActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityDetailProgramBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailProgramBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.back.setOnClickListener {
            onBackPressed()
        }

        val program = intent.getParcelableExtra<ProgramItem>("id_program")

        binding.program.text = program?.namaProgram
        binding.isiProgram.text = program?.deskripsiProgram
        Glide.with(this)
            .load("${NetworkConfig.BASEURL}program/${program?.gambar}")
            .into(binding.coverProgram)
    }
}