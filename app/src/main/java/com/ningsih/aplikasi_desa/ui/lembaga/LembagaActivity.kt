package com.ningsih.aplikasi_desa.ui.lembaga

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.ningsih.aplikasi_desa.MainActivity
import com.ningsih.aplikasi_desa.R
import com.ningsih.aplikasi_desa.databinding.ActivityLembagaBinding
import com.ningsih.aplikasi_desa.network.NetworkConfig
import com.ningsih.aplikasi_desa.response.ResponseListLembaga
import com.ningsih.aplikasi_desa.ui.home.adapter.AdapterLembaga
import com.ningsih.aplikasi_desa.ui.layanan.DetailLayananActivity
import retrofit2.Call
import retrofit2.Response

class LembagaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLembagaBinding
    private lateinit var adapterLembaga: AdapterLembaga

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lembaga)

        binding = ActivityLembagaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapterLembaga = AdapterLembaga {
            if (it?.idLembaga == 1){
                Intent(this@LembagaActivity, MainActivity::class.java).apply {
                    startActivity(this)
                }
            }else {

                Toast.makeText(this, it?.namaLembaga, Toast.LENGTH_SHORT).show()
                Intent(this@LembagaActivity, DetailLembagaActivity::class.java).apply {
                    putExtra("id_lembaga", it)
                    startActivity(this)
                }
            }
        }
        binding.rvLembaga.apply {
            adapter = adapterLembaga
            layoutManager = LinearLayoutManager(this@LembagaActivity)
        }
        getListLembaga()
    }

    private fun getListLembaga(){
        NetworkConfig.getService().getAllLembaga()
            .enqueue(object: retrofit2.Callback<ResponseListLembaga>{
                override fun onResponse(
                    call: Call<ResponseListLembaga>,
                    response: Response<ResponseListLembaga>
                ) {
                    if (response.isSuccessful)
                        response.body()?.lembaga?.lembaga?.let { adapterLembaga.addItem(it) }
                }

                override fun onFailure(call: Call<ResponseListLembaga>, t: Throwable) {
                    Toast.makeText(this@LembagaActivity, t.message, Toast.LENGTH_SHORT).show()
                }
            })
    }
}