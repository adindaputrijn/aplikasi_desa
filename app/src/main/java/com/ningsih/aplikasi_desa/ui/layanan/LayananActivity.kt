package com.ningsih.aplikasi_desa.ui.layanan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.ningsih.aplikasi_desa.MainActivity
import com.ningsih.aplikasi_desa.R
import com.ningsih.aplikasi_desa.databinding.ActivityLayananBinding
import com.ningsih.aplikasi_desa.network.NetworkConfig
import com.ningsih.aplikasi_desa.response.ResponseListLayanan
import com.ningsih.aplikasi_desa.ui.home.adapter.AdapterLayanan
import retrofit2.Call
import retrofit2.Response

class LayananActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLayananBinding
    private lateinit var adapterLayanan: AdapterLayanan

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLayananBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapterLayanan = AdapterLayanan {
            if (it?.idLayanan == 4){
                Intent(this@LayananActivity, MainActivity::class.java).apply {
                    startActivity(this)
                }
            }else

            Toast.makeText(this, it?.namaLayanan, Toast.LENGTH_SHORT).show()
            Intent(this@LayananActivity, DetailLayananActivity::class.java).apply{
                putExtra("id_layanan", it)
                startActivity(this)
            }
        }

        binding.activityLayanan.apply {
            adapter = adapterLayanan
            layoutManager = LinearLayoutManager(this@LayananActivity)
        }

        getListLayanan()
    }

    private fun getListLayanan(){
        NetworkConfig.getService().getAllLayanan()
            .enqueue(object : retrofit2.Callback<ResponseListLayanan>{
                override fun onResponse(
                    call: Call<ResponseListLayanan>,
                    response: Response<ResponseListLayanan>
                ) {
                    Log.d("onResponse", "OnResponse: ${response.raw()}")
                    if (response.isSuccessful){
                        response.body()?.layanan?.let { adapterLayanan.addItem(it) }
                    }
                }

                override fun onFailure(call: Call<ResponseListLayanan>, t: Throwable) {
                    Toast.makeText(this@LayananActivity, t.message, Toast.LENGTH_SHORT).show()
                }
            })
    }
}