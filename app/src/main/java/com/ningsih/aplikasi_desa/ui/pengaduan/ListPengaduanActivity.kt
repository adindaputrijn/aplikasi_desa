package com.ningsih.aplikasi_desa.ui.pengaduan

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.ningsih.aplikasi_desa.MainActivity
import com.ningsih.aplikasi_desa.R
import com.ningsih.aplikasi_desa.databinding.ActivityListPengaduanBinding
import com.ningsih.aplikasi_desa.databinding.ActivityPotensiBinding
import com.ningsih.aplikasi_desa.network.NetworkConfig
import com.ningsih.aplikasi_desa.response.ResponsePengaduan
import com.ningsih.aplikasi_desa.ui.home.adapter.AdapterPotensi
import com.ningsih.aplikasi_desa.ui.lembaga.DetailLembagaActivity
import com.ningsih.aplikasi_desa.ui.login.LoginActivity
import com.ningsih.aplikasi_desa.ui.potensi.DetailPotensiActivity
import com.ningsih.aplikasi_desa.utils.Constant
import com.pixplicity.easyprefs.library.Prefs
import retrofit2.Call
import retrofit2.Response
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ListPengaduanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListPengaduanBinding
    private lateinit var adapterPengaduan: AdapterPengaduan

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListPengaduanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapterPengaduan = AdapterPengaduan {
            if (it?.idPengaduan == 1){
                Intent(this@ListPengaduanActivity, MainActivity::class.java).apply {
                    startActivity(this)
                }
            }else {

//                Toast.makeText(this, it?.ditambahkanOleh, Toast.LENGTH_SHORT).show()
                Intent(this@ListPengaduanActivity, DetailPengaduanActivity::class.java).apply {
                    putExtra("id_pengaduan", it)
                    startActivity(this)

                }
            }
        }

        binding.back.setOnClickListener {
            onBackPressed()
        }

        binding.activityPengaduan.apply {
            adapter = adapterPengaduan
            layoutManager = LinearLayoutManager(this@ListPengaduanActivity)
        }
        getListPengaduan()
    }

    private fun getListPengaduan() {

        NetworkConfig.getService().getAllPengaduan(
            Prefs.getString(Constant.NIK)
        )
            .enqueue(object : retrofit2.Callback<ResponsePengaduan>{
                override fun onResponse(
                    call: Call<ResponsePengaduan>,
                    response: Response<ResponsePengaduan>
                ) {
                    if (response.isSuccessful)
                        response.body()?.pengaduan?.let { adapterPengaduan.addItem(it) }
                }

                override fun onFailure(call: Call<ResponsePengaduan>, t: Throwable) {
//                    Toast.makeText(this@ListPengaduanActivity, t.message, Toast.LENGTH_SHORT).show()

                }

            })
    }


//    fun generateDate(){
//        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.O){
//            val firstApiFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
//            val date = LocalDate.parse("2019-08-07 09:00:00" , firstApiFormat)
//        }
//
//    }
}