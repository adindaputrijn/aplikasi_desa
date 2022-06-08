package com.ningsih.aplikasi_desa.ui.potensi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.ningsih.aplikasi_desa.MainActivity
import com.ningsih.aplikasi_desa.R
import com.ningsih.aplikasi_desa.databinding.ActivityPotensiBinding
import com.ningsih.aplikasi_desa.network.NetworkConfig
import com.ningsih.aplikasi_desa.response.ResponseListPotensi
import com.ningsih.aplikasi_desa.ui.home.adapter.AdapterPotensi
import retrofit2.Call
import retrofit2.Response

class PotensiActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPotensiBinding
    private lateinit var adapterPotensi: AdapterPotensi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lembaga)

        binding = ActivityPotensiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapterPotensi = AdapterPotensi {
            if (it?.idPotensi == 4){
                Intent(this@PotensiActivity, MainActivity::class.java).apply {
                    startActivity(this)
                }
            }else {

//                Toast.makeText(this, it?.judul, Toast.LENGTH_SHORT).show()
                Intent(this@PotensiActivity, DetailPotensiActivity::class.java).apply {
                    putExtra("id_potensi", it)
                    startActivity(this)
                }
            }
        }
        binding.activityPotensi.apply {
            adapter = adapterPotensi
            layoutManager = LinearLayoutManager(this@PotensiActivity)
        }
        getListPotensi()
    }

    private fun getListPotensi(){
        NetworkConfig.getService().getAllPotensi()
            .enqueue(object: retrofit2.Callback<ResponseListPotensi>{
                override fun onResponse(
                    call: Call<ResponseListPotensi>,
                    response: Response<ResponseListPotensi>
                ) {
                    if (response.isSuccessful)
                        response.body()?.potensi?.let { adapterPotensi.addItem(it) }
                }

                override fun onFailure(call: Call<ResponseListPotensi>, t: Throwable) {
                    Toast.makeText(this@PotensiActivity, t.message, Toast.LENGTH_SHORT).show()
                }
            })
    }
}