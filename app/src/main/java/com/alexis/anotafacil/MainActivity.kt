package com.alexis.anotafacil

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.alexis.anotafacil.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCamara.setOnClickListener{
            var intent = Intent(this,CamaraActivity::class.java)
            startActivity(intent)
        }

        binding.btnMapa.setOnClickListener {
            /*var addressUri = Uri.parse("geo:0,0?q=-12.0653305,-77.0065496")
            val intent  = Intent(Intent.ACTION_VIEW, addressUri)
            intent.setPackage("com.google.android.apps.maps")
            intent.resolveActivity(packageManager).let {
                startActivity(intent)
            }*/
            startActivity(Intent(this,MapaActivity::class.java))
        }
    }
}