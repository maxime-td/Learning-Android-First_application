package com.example.firstapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp


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
    // State to hold our list of strings
    var textList by rememberSaveable { mutableStateOf(List(5) { "Item ${it + 1}" }) }

    // Function to generate random text
    fun generateRandomText(): String {
        val words = listOf("Happy", "Sunny", "Cloudy", "Rainy", "Windy", "Snowy",
            "Cold", "Warm", "Hot", "Cool")
        val adjectives = listOf("Very", "Somewhat", "Extremely", "Moderately", "Slightly")
        return "${adjectives.random()} ${words.random()} Day"
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // LazyColumn for the list
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(textList) { text ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Text(
                        text = text,
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                    )
                }
            }
        }

        // Button to add new random text
        Button(
            onClick = {
                textList = textList + generateRandomText()
            },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text("Add Random Text")
        }
    }
}