package com.alexis.anotafacil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alexis.anotafacil.databinding.ActivityMainBinding
import com.alexis.anotafacil.databinding.ActivityMapaBinding
import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions

class MapaActivity : AppCompatActivity() ,OnMapReadyCallback{

    private lateinit var map : GoogleMap
    private lateinit var binding: ActivityMapaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentMap = supportFragmentManager.findFragmentById(R.id.fragment_map) as SupportMapFragment
        fragmentMap.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        val cusco = LatLng(-13.5226400,-71.9673400)
        val tumbes = LatLng(-3.5669400,-80.4515300)
        val tacna = LatLng(-18.0146500,-70.2536200)
        map.addMarker(MarkerOptions().title("Cusco").position(cusco))
        map.addMarker(MarkerOptions().title("Tumbes").position(tumbes))
        map.addMarker(MarkerOptions().title("Tacna").position(tacna))
        val bounds = LatLngBounds.Builder()
        bounds.include(cusco)
        bounds.include(tumbes)
        bounds.include(tacna)
        map.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds.build(),150))
    }
}