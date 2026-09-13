package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StreakScreen(
    onNavigate: (String) -> Unit,
    onKeepGoing: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Streak", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { onNavigate("home") }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = DarkBackground)
            )
        },
        containerColor = DarkBackground
    ) { paddingVals ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingVals)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Spacer(modifier = Modifier.height(40.dp))
                Text("🔥", fontSize = 64.sp)
                Spacer(modifier = Modifier.height(16.dp))
                Text("You're on fire.", style = MaterialTheme.typography.titleLarge, color = OnDarkSurface)
                Spacer(modifier = Modifier.height(8.dp))
                Text("7 DAYS 🔥", style = MaterialTheme.typography.displayMedium, fontWeight = FontWeight.Black, color = Color.White)
                Spacer(modifier = Modifier.height(16.dp))
                Text("Don't break it now.", style = MaterialTheme.typography.bodyLarge, color = OnDarkSurface)
            }

            Button(
                onClick = onKeepGoing,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = ElectricPink),
                shape = MaterialTheme.shapes.extraLarge
            ) {
                Text("Keep it going", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
        }
    }
}
