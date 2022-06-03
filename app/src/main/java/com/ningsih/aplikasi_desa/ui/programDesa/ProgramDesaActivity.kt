package com.ningsih.aplikasi_desa.ui.programDesa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.ningsih.aplikasi_desa.R
import com.ningsih.aplikasi_desa.databinding.ActivityProgramDesaBinding
import com.ningsih.aplikasi_desa.network.NetworkConfig
import com.ningsih.aplikasi_desa.response.ResponseListProgram
import com.ningsih.aplikasi_desa.ui.home.adapter.AdapterProgram
import retrofit2.Call
import retrofit2.Response

class ProgramDesaActivity : AppCompatActivity() {

    private  lateinit var binding:ActivityProgramDesaBinding
    private  lateinit var  adapterProgram: AdapterProgram

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProgramDesaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapterProgram = AdapterProgram {
            Intent(this@ProgramDesaActivity, DetailProgramActivity::class.java).apply {
                putExtra("id_program", it)
                startActivity(this)
            }
        }

        binding.activityProgram.apply {
            adapter = adapterProgram
            layoutManager = LinearLayoutManager(this@ProgramDesaActivity)
        }

        getListPotensi()
    }

    private fun getListPotensi() {
        NetworkConfig.getService().getAllProgram()
            .enqueue(object  : retrofit2.Callback<ResponseListProgram>{
                override fun onResponse(
                    call: Call<ResponseListProgram>,
                    response: Response<ResponseListProgram>
                ) {
                    Log.d("onResponse", "onResponse: ${response.raw()}")
                    if (response.isSuccessful){
                        response.body()?.program?.let { adapterProgram.addItem(it) }
                    }
                }

                override fun onFailure(call: Call<ResponseListProgram>, t: Throwable) {
                    Toast.makeText(this@ProgramDesaActivity,t.message, Toast.LENGTH_SHORT).show()
                }
            })
    }
}