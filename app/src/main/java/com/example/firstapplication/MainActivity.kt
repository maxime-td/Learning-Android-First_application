package com.example.firstapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import kotlinx.coroutines.launch
import androidx.compose.ui.Modifier
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items


class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity" // Tag pour identifier facilement les logs


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) // Création de l'activité

        setContent {
            MainScreen()
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


// Fonctions compose
@Composable
fun MainScreen() {
    // Pour contrôler l'état de la Snackbar
    val snackbarHostState = remember { SnackbarHostState() }
    // Pour le scope des coroutines (nécessaire pour afficher la Snackbar)
    val coroutineScope = rememberCoroutineScope()
    // Pour le texte du TextField
    var text by rememberSaveable { mutableStateOf("") }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            TextField(
                value = text,
                onValueChange = { text = it }
            )
            Button(
                onClick = {
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(text)
                    }
                }
            ) {
                Text("Afficher Snackbar")
            }
        }
    }
}