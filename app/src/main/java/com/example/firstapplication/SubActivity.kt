package com.example.firstapplication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.firstapplication.databinding.ActivitySubBinding
import android.provider.MediaStore
import android.graphics.Bitmap
import android.content.Intent


class SubActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySubBinding

    private companion object {
        const val CAMERA_PERMISSION_CODE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        binding = ActivitySubBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_sub)

        val bouton = findViewById<Button>(R.id.monBoutonCamera)

        Log.d("CameraDebug", "onCreate appelé")

        bouton.setOnClickListener {
            Log.d("CameraDebug", "Bouton cliqué")
            requestCameraPermission()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }

    private fun requestCameraPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            // Ajout d'un log ici
            Log.d("CameraDebug", "Permission déjà accordée")
            openCamera()
        } else {
            // Et ici
            Log.d("CameraDebug", "Demande de permission")
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                CAMERA_PERMISSION_CODE
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        // Log ici aussi
        Log.d("CameraDebug", "onRequestPermissionsResult appelé")
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d("CameraDebug", "Permission accordée")
                openCamera()
            } else {
                Log.d("CameraDebug", "Permission refusée")
                Toast.makeText(
                    this,
                    "Permission refusée",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun openCamera() {
        try {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, CAMERA_PERMISSION_CODE)
        } catch (e: Exception) {
            Toast.makeText(
                this,
                "Erreur lors de l'ouverture de la caméra",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CAMERA_PERMISSION_CODE && resultCode == RESULT_OK) {
            // L'image capturée est dans data?.extras?.get("data") sous forme de Bitmap
            val imageBitmap = data?.extras?.get("data") as? Bitmap
            // Vous pouvez utiliser ce Bitmap si vous voulez afficher l'image
        }
    }
}