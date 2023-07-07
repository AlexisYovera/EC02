package com.alexis.anotafacil

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.alexis.anotafacil.databinding.ActivityCamaraBinding
import com.alexis.anotafacil.databinding.ActivityMainBinding
import java.util.jar.Manifest

class CamaraActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCamaraBinding
    private lateinit var openCameraLauncher: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCamaraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnTomarFoto.setOnClickListener{
            if(permisionValidated()){
                tomarFoto()
            }
        }
        openCameraLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
            if(result.resultCode == RESULT_OK){
                val photo = result.data?.extras?.get("data") as Bitmap
                binding.imgFoto.setImageBitmap(photo)
            }
        }
    }

    private fun tomarFoto(){
        val intent = Intent()
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE)
        openCameraLauncher.launch(intent)
    }

    private fun permisionValidated():Boolean{
        val cameraPermission = ContextCompat.checkSelfPermission(this,android.Manifest.permission.CAMERA)
        var permissionList: MutableList<String> = mutableListOf()
        if(cameraPermission != PackageManager.PERMISSION_GRANTED){
            permissionList.add(android.Manifest.permission.CAMERA)
        }
        if(permissionList.isEmpty()){
            ActivityCompat.requestPermissions(this,permissionList.toTypedArray(),1000)
            return false
        }
        return true
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            1000-> {
                if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
                    tomarFoto()
                }
            }
        }
    }
}