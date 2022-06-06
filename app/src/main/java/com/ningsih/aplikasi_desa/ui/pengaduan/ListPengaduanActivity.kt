package com.ningsih.aplikasi_desa.ui.pengaduan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.ningsih.aplikasi_desa.MainActivity
import com.ningsih.aplikasi_desa.R
import com.ningsih.aplikasi_desa.databinding.ActivityListPengaduanBinding
import com.ningsih.aplikasi_desa.databinding.ActivityPotensiBinding
import com.ningsih.aplikasi_desa.network.NetworkConfig
import com.ningsih.aplikasi_desa.response.ResponsePengaduan
import com.ningsih.aplikasi_desa.ui.home.adapter.AdapterPotensi
import com.ningsih.aplikasi_desa.ui.lembaga.DetailLembagaActivity
import com.ningsih.aplikasi_desa.ui.potensi.DetailPotensiActivity
import retrofit2.Call
import retrofit2.Response

class ListPengaduanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListPengaduanBinding
    private lateinit var adapterPengaduan: AdapterPengaduan

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_pengaduan)


        binding = ActivityListPengaduanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapterPengaduan = AdapterPengaduan {
            if (it?.idPengaduan == 1){
                Intent(this@ListPengaduanActivity, MainActivity::class.java).apply {
                    startActivity(this)
                }
            }else {

                Toast.makeText(this, it?.ditambahkanOleh, Toast.LENGTH_SHORT).show()
                Intent(this@ListPengaduanActivity, DetailPengaduanActivity::class.java).apply {
                    putExtra("id_pengaduan", it)
                    startActivity(this)
                }
            }
        }

        binding.activityPengaduan.apply {
            adapter = adapterPengaduan
            layoutManager = LinearLayoutManager(this@ListPengaduanActivity)
        }
        getListPengaduan()
    }

    private fun getListPengaduan() {
        NetworkConfig.getService().getAllPengaduan()
            .enqueue(object : retrofit2.Callback<ResponsePengaduan>{
                override fun onResponse(
                    call: Call<ResponsePengaduan>,
                    response: Response<ResponsePengaduan>
                ) {
                    if (response.isSuccessful)
                        response.body()?.pengaduan?.let { adapterPengaduan.addItem(it) }
                }

                override fun onFailure(call: Call<ResponsePengaduan>, t: Throwable) {
                    Toast.makeText(this@ListPengaduanActivity, t.message, Toast.LENGTH_SHORT).show()

                }

            })
    }
}