package com.ningsih.aplikasi_desa.ui.berita

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.ningsih.aplikasi_desa.R
import com.ningsih.aplikasi_desa.databinding.ActivityBeritaBinding
import com.ningsih.aplikasi_desa.databinding.ItemAdapterBeritaBinding
import com.ningsih.aplikasi_desa.network.NetworkConfig
import com.ningsih.aplikasi_desa.response.BeritaItem
import com.ningsih.aplikasi_desa.response.ResponseBerita
import com.ningsih.aplikasi_desa.ui.home.HomeFragment
import com.ningsih.aplikasi_desa.ui.home.adapter.AdapterKategoriBerita
import com.pixplicity.easyprefs.library.Prefs
import retrofit2.Call
import retrofit2.Response

class BeritaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBeritaBinding
    private lateinit var adapterKategoriBerita: AdapterKategoriBerita

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBeritaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapterKategoriBerita = AdapterKategoriBerita {
            Intent(this@BeritaActivity, DetailBeritaActivity::class.java).apply {
                putExtra("id_berita", it)
                startActivity(this)
            }
        }

        binding.back.setOnClickListener {
            onBackPressed()
        }


        binding.activityBerita.apply {
            adapter = adapterKategoriBerita
            layoutManager = LinearLayoutManager(this@BeritaActivity)
        }

        getListBeritaKat()
    }

    private fun getListBeritaKat(){
        NetworkConfig.getService().getAllBerita("berita")
            .enqueue(object : retrofit2.Callback<ResponseBerita>{
                override fun onResponse(
                    call: Call<ResponseBerita>,
                    response: Response<ResponseBerita>
                ) {
                    if (response.isSuccessful){
                        response.body()?.berita?.let { adapterKategoriBerita.addItem(it) }

                    }
                }

                override fun onFailure(call: Call<ResponseBerita>, t: Throwable) {
                    Toast.makeText(this@BeritaActivity, t.message, Toast.LENGTH_SHORT).show()
                }
            })
    }
}