package com.ningsih.aplikasi_desa.ui.pengaduan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ningsih.aplikasi_desa.databinding.ActivityPengaduanBinding
import com.ningsih.aplikasi_desa.network.NetworkConfig
import com.ningsih.aplikasi_desa.response.GeneralResponse
import com.ningsih.aplikasi_desa.response.ResponsePengaduan
import retrofit2.Call
import retrofit2.Response

class PengaduanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPengaduanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding = ActivityPengaduanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnKirim.setOnClickListener {
            Toast.makeText(this@PengaduanActivity, "", Toast.LENGTH_SHORT).show()
            validasiInput()
        }


    }

    private fun validasiInput() {
        if (binding.pengaduan.text.toString().isEmpty()) {
            Toast.makeText(this, "Keluhan Harus Di isi", Toast.LENGTH_SHORT).show()
        }else if(binding.nama.text.toString().isEmpty()){
            Toast.makeText(this, "Nama Harus Di isi", Toast.LENGTH_SHORT).show()
        }else{
            kirimData()
        }
    }

    private fun kirimData() {
        NetworkConfig.getService().pengaduanSubmit(
            pengaduan = binding.pengaduan.text.toString(),
            nama = binding.nama.text.toString()
        ).enqueue(object : retrofit2.Callback<GeneralResponse>{
            override fun onResponse(
                call: Call<GeneralResponse>,
                response: Response<GeneralResponse>
            ) {
                Toast.makeText(this@PengaduanActivity, response.body()?.message.toString(), Toast.LENGTH_SHORT).show()
                finish()
            }

            override fun onFailure(call: Call<GeneralResponse>, t: Throwable) {
                Toast.makeText(this@PengaduanActivity, t.message, Toast.LENGTH_SHORT).show()
                t.printStackTrace()
            }

        })
    }
}