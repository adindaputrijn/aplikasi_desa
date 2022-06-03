package com.ningsih.aplikasi_desa.ui.berita

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.ningsih.aplikasi_desa.R
import com.ningsih.aplikasi_desa.databinding.FragmentBeritaBinding
import com.ningsih.aplikasi_desa.ui.home.adapter.AdapterBerita


class BeritaFragment : Fragment(R.layout.fragment_berita) {
    private lateinit var binding: FragmentBeritaBinding
    private lateinit var adapterBerita: AdapterBerita

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentBeritaBinding.bind(view)

        adapterBerita = AdapterBerita()
    }
}