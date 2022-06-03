package com.ningsih.aplikasi_desa.ui.potensi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.ningsih.aplikasi_desa.R
import com.ningsih.aplikasi_desa.databinding.ActivityDetailPotensiBinding
import com.ningsih.aplikasi_desa.databinding.ActivityPotensiBinding
import com.ningsih.aplikasi_desa.network.NetworkConfig
import com.ningsih.aplikasi_desa.response.LayananItem
import com.ningsih.aplikasi_desa.response.PotensiItem
import com.ningsih.aplikasi_desa.response.ResponseListPotensi
import retrofit2.Call
import retrofit2.Response

class DetailPotensiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailPotensiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailPotensiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val idPotensi = intent.getParcelableExtra<PotensiItem>("id_potensi")

        MapsInitializer.initialize(this)
        binding.mapView.onCreate(savedInstanceState)

        getDetailPotensi(idPotensi)


    }

    private fun getDetailPotensi(idPotensi: PotensiItem?) {

        binding.jdlPotensi.text = idPotensi?.judul
        binding.isiPotensi.text = idPotensi?.deskripsi
        Glide.with(this@DetailPotensiActivity)
            .load("${NetworkConfig.BASEURL}potensi/${idPotensi?.gambar}")
            .into(binding.cover)

        binding.mapView.getMapAsync {
            setMarker(it, idPotensi?.lat, idPotensi?.lang)
        }

    }

    private fun setMarker(googleMap: GoogleMap, lat: Double?, lang: Double?) {
        val latLng = LatLng(lat!!, lang!!)
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12f))
        googleMap.addMarker(MarkerOptions().position(latLng))
    }

    override fun onResume() {
        super.onResume()
        binding.mapView.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.mapView.onDestroy()
    }

    override fun onPause() {
        super.onPause()
        binding.mapView.onPause()
    }

}