package com.example.runtimepermissions

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {
    lateinit var btnCamera : Button
    lateinit var btnStorage : Button
    private val CAMERA_PERMISSION_CODE = 123
    private val STORAGE_PERMISSION_CODE = 321
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnCamera = findViewById<Button>(R.id.btnCamera)
        btnStorage = findViewById<Button>(R.id.btnStorage)

        btnCamera.setOnClickListener {
            checkPermissions(android.Manifest.permission.CAMERA, CAMERA_PERMISSION_CODE)
        }
        btnStorage.setOnClickListener {
            checkPermissions(android.Manifest.permission.READ_EXTERNAL_STORAGE, STORAGE_PERMISSION_CODE)
        }
    }

    private fun checkPermissions(permission: String, requestCode: Int) {
        if(ContextCompat.checkSelfPermission(this@MainActivity, permission) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this@MainActivity, arrayOf(permission), requestCode)
        }
        else {
            Toast.makeText(this@MainActivity, "Permission granted already!", Toast.LENGTH_LONG).show()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode == CAMERA_PERMISSION_CODE) {
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                btnCamera.setText("Permission granted!")
                Toast.makeText(this@MainActivity, "Camera permission granted!", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this@MainActivity, "Camera permission denied!", Toast.LENGTH_SHORT).show()
            }
        }
        else if(requestCode == STORAGE_PERMISSION_CODE) {
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                btnStorage.setText("Permission granted!")
                Toast.makeText(this@MainActivity, "Storage permission granted!", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this@MainActivity, "Storage permission denied!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}