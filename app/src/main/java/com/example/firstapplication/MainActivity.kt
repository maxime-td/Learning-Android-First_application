package com.example.firstapplication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button    // Pour utiliser Button
import android.widget.Toast     // Pour utiliser Toast.makeText


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()  // Pour que l'application puisse utiliser tout l'écran,
                            // même derrière la barre d'activité


        setContentView(R.layout.activity_main)


        // On récupère le bouton depuis le layout en utilisant son ID
        val bouton = findViewById<Button>(R.id.monBouton)

        // On définit ce qui se passe quand on clique sur le bouton
        bouton.setOnClickListener {
            // Toast.makeText crée un petit message qui apparaît en bas de l'écran
            // this : contexte actuel (l'Activity)
            // "Hello!" : le message à afficher
            // Toast.LENGTH_SHORT : durée d'affichage courte
            Toast.makeText(this, "Hey ! 😎", Toast.LENGTH_SHORT).show()
        }


        // Le code pour la gestion Edge-to-Edge
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}