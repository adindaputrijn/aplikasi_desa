package com.ningsih.aplikasi_desa.ui.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ningsih.aplikasi_desa.databinding.ActivityRegisterBinding
import com.ningsih.aplikasi_desa.network.NetworkConfig
import com.ningsih.aplikasi_desa.response.ResponseRegister
import retrofit2.Call
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            Toast.makeText(this, "test", Toast.LENGTH_SHORT).show()
            validasiInput()
        }
    }

    private fun validasiInput() {
        if (binding.nik.text.toString().isEmpty()){
            Toast.makeText(this, "NIK Harus Diisi", Toast.LENGTH_SHORT).show()

        }else if (binding.namaLengkap.text.toString().isEmpty()){
            Toast.makeText(this, "Nama Harus Diisi", Toast.LENGTH_SHORT).show()

        }else if (binding.alamat.text.toString().isEmpty()){
            Toast.makeText(this, "Alamat Harus Diisi", Toast.LENGTH_SHORT).show()

        }else if (binding.pedukuhan.text.toString().isEmpty()){
            Toast.makeText(this, "Pedukuhan Harus Diisi", Toast.LENGTH_SHORT).show()

        }else {
            kirimData()
        }
    }

    private fun kirimData() {
        NetworkConfig.getService().registrasi(
            nik = binding.nik.text.toString(),
            nama = binding.namaLengkap.text.toString(),
            alamat = binding.alamat.text.toString(),
            pedukuhan = binding.pedukuhan.text.toString()
        ).enqueue(object: retrofit2.Callback<ResponseRegister>{
            override fun onResponse(
                call: Call<ResponseRegister>,
                response: Response<ResponseRegister>
            ) {
                val res =response.body()?.message
                Toast.makeText(this@RegisterActivity, res, Toast.LENGTH_SHORT).show()
                finish()
            }

            override fun onFailure(call: Call<ResponseRegister>, t: Throwable) {
                Toast.makeText(this@RegisterActivity,t.message, Toast.LENGTH_SHORT).show()
                t.printStackTrace()
            }

        })
    }
}