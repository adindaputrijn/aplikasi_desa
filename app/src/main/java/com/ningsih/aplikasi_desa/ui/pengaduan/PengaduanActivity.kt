package com.ningsih.aplikasi_desa.ui.pengaduan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ningsih.aplikasi_desa.databinding.ActivityPengaduanBinding
import com.ningsih.aplikasi_desa.network.NetworkConfig
import com.ningsih.aplikasi_desa.response.ResponsePengaduan
import retrofit2.Call
import retrofit2.Response

class PengaduanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPengaduanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding = ActivityPengaduanBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        binding.pengaduan.setOnClickListener {
            Toast.makeText(this@PengaduanActivity, "test", Toast.LENGTH_SHORT).show()
            validasiInput()
        }
    }

    private fun validasiInput() {
        if (binding.pengaduan.text.toString().isEmpty()) {
            Toast.makeText(this, "Keluhan Harus Di isi", Toast.LENGTH_SHORT).show()
        }else if(binding.nama.text.toString().isEmpty()){

        }else{
            kirimData()
        }
    }

    private fun kirimData() {
        NetworkConfig.getService().pengaduan(
            pengaduan = binding.pengaduan.text.toString()
        ).enqueue(object : retrofit2.Callback<ResponsePengaduan>{
            override fun onResponse(
                call: Call<ResponsePengaduan>,
                response: Response<ResponsePengaduan>
            ) {
                Toast.makeText(this@PengaduanActivity, "Pengaduan berhasil dikirimkan", Toast.LENGTH_SHORT).show()
                finish()
            }

            override fun onFailure(call: Call<ResponsePengaduan>, t: Throwable) {
                Toast.makeText(this@PengaduanActivity, t.message, Toast.LENGTH_SHORT).show()
                t.printStackTrace()
            }

        })
    }
}