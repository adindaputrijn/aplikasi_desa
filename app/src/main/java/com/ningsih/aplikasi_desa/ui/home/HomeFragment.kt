package com.ningsih.aplikasi_desa.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ningsih.aplikasi_desa.R
import com.ningsih.aplikasi_desa.databinding.FragmentHomeBinding
import com.ningsih.aplikasi_desa.network.NetworkConfig
import com.ningsih.aplikasi_desa.response.ResponseBerita
import com.ningsih.aplikasi_desa.response.ResponseKategori
import com.ningsih.aplikasi_desa.ui.berita.BeritaActivity
import com.ningsih.aplikasi_desa.ui.galeri.ActivityGaleri
import com.ningsih.aplikasi_desa.ui.home.adapter.AdapterBerita
import com.ningsih.aplikasi_desa.ui.home.adapter.AdapterKategori
import com.ningsih.aplikasi_desa.ui.layanan.LayananActivity
import com.ningsih.aplikasi_desa.ui.lembaga.LembagaActivity
import com.ningsih.aplikasi_desa.ui.potensi.PotensiActivity
import com.ningsih.aplikasi_desa.ui.programDesa.ProgramDesaActivity
import com.pixplicity.easyprefs.library.Prefs
import retrofit2.Call
import retrofit2.Response

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapterBerita: AdapterBerita
    private lateinit var adapterKategori: AdapterKategori

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        adapterBerita = AdapterBerita()
        binding.beritaRecycler.apply{
            adapter = adapterBerita
            layoutManager = LinearLayoutManager(requireActivity(), RecyclerView.HORIZONTAL,false)
        }

        adapterKategori = AdapterKategori{
            if (it?.idKategori == 1) {
                Intent(requireActivity(), LayananActivity::class.java).apply {
                    startActivity(this)
                }
            }else if (it?.idKategori == 2){
                Intent(requireActivity(), LembagaActivity::class.java).apply {
                    startActivity(this)
                }
            }else if (it?.idKategori == 4) {
                Intent(requireActivity(), ActivityGaleri::class.java).apply {
                    startActivity(this)
                }
            }else if (it?.idKategori == 5) {
                Intent(requireActivity(), PotensiActivity::class.java).apply {
                    startActivity(this)
                }
            }else if (it?.idKategori == 6) {
                Intent(requireActivity(), BeritaActivity::class.java).apply {
                    startActivity(this)
                }
            }else if (it?.idKategori == 3) {
                Intent(requireActivity(), ProgramDesaActivity::class.java).apply {
                    startActivity(this)
                }
            }
        }
        binding.kategoriRecycler.apply {
            adapter = adapterKategori
            layoutManager = GridLayoutManager(requireActivity(),3)
        }

        binding.notif.setOnClickListener {
            Prefs.clear()
            requireActivity().recreate()
        }

        getBerita()
        getKategori()
    }

    private fun getBerita(){
        NetworkConfig.getService().getAllBerita()
            .enqueue(object: retrofit2.Callback<ResponseBerita>{
                override fun onResponse(
                    call: Call<ResponseBerita>,
                    response: Response<ResponseBerita>
                ){
                    if (response.isSuccessful){
                        val data = response.body()?.berita
                        data?.let { adapterBerita.addItem(it) }
                    }
                }

                override fun onFailure(call: Call<ResponseBerita>, t: Throwable) {

                }
            })
    }

    private  fun getKategori(){
        NetworkConfig.getService().getAllKategori()
            .enqueue(object : retrofit2.Callback<ResponseKategori>{
                override fun onResponse(
                    call: Call<ResponseKategori>,
                    response: Response<ResponseKategori>
                ) {
                    if (response.isSuccessful){
                        val data = response.body()?.kategori
                        data?.let { adapterKategori.addItem(it) }
                    }
                }

                override fun onFailure(call: Call<ResponseKategori>, t: Throwable) {

                }
            })
    }

}