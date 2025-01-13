package com.example.firstapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button    // Pour utiliser Button
import android.widget.EditText
import android.widget.Toast     // Pour utiliser Toast.makeText


class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity" // Tag pour identifier facilement les logs


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) // Création de l'activité

        enableEdgeToEdge()  // Pour que l'application puisse utiliser tout l'écran,
                            // même derrière la barre d'activité

        setContentView(R.layout.activity_main)

        Log.d(TAG, "onCreate called") // Log



        // Texte entré par l'utilisateur
        var champTexte = findViewById<EditText>(R.id.champTexte)

        // On récupère le bouton depuis le layout en utilisant son ID
        val bouton = findViewById<Button>(R.id.monBouton)

        // On définit ce qui se passe quand on clique sur le bouton
        bouton.setOnClickListener {
            // Toast.makeText crée un petit message qui apparaît en bas de l'écran
            // Toast.LENGTH_SHORT : durée d'affichage courte
            val texte = champTexte.text.toString() // On lit le texte
            Toast.makeText(this, texte, Toast.LENGTH_SHORT).show()
        }


        // Le code pour la gestion Edge-to-Edge
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy called")
    }

}