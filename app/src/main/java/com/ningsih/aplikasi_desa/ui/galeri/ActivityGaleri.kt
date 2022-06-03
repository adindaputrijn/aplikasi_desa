package com.ningsih.aplikasi_desa.ui.galeri

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ningsih.aplikasi_desa.databinding.ActivityGaleriBinding
import com.ningsih.aplikasi_desa.network.NetworkConfig
import com.ningsih.aplikasi_desa.response.ResponseGaleri
import com.ningsih.aplikasi_desa.ui.home.adapter.AdapterGaleri
import retrofit2.Call
import retrofit2.Response

class ActivityGaleri : AppCompatActivity() {
    private lateinit var binding: ActivityGaleriBinding
    private lateinit var adapterGaleri: AdapterGaleri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGaleriBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapterGaleri = AdapterGaleri {  }
        binding.cover.apply {
            adapter = adapterGaleri
            layoutManager = LinearLayoutManager(this@ActivityGaleri)
        }

        getListGaleri()
    }

    private fun getListGaleri(){
        NetworkConfig.getService().getAllGaleri()
            .enqueue(object : retrofit2.Callback<ResponseGaleri>{
                override fun onResponse(
                    call: Call<ResponseGaleri>,
                    response: Response<ResponseGaleri>
                ) {
                    Log.d("onResponse", "onResponse: ${response.raw()}")
                    if (response.isSuccessful){
                        response.body()?.galeri?.let { adapterGaleri.addItem(it) }
                    }
                }

                override fun onFailure(call: Call<ResponseGaleri>, t: Throwable) {
                    Toast.makeText(this@ActivityGaleri, t.message, Toast.LENGTH_SHORT).show()
                }
            })
    }
}