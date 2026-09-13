package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateScreen(
    initialVibe: String = "Aura",
    onBack: () -> Unit,
    onCreateSubmitted: (String, String) -> Unit
) {
    var selectedVibe by remember { mutableStateOf(initialVibe) }
    var promptText by remember { mutableStateOf("") }
    val vibes = listOf("Aura", "Style", "Future", "Sports", "Friends", "Random", "Custom")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Create a Challenge", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
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
                .padding(20.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
                Text(
                    text = "Make it yours.",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Text(
                    text = "Pick a vibe",
                    style = MaterialTheme.typography.titleMedium,
                    color = OnDarkSurface
                )

                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(vibes) { vibe ->
                        val isSelected = vibe == selectedVibe
                        FilterChip(
                            selected = isSelected,
                            onClick = { selectedVibe = vibe },
                            label = { Text(vibe) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = ElectricPink,
                                selectedLabelColor = Color.White,
                                containerColor = DarkSurface,
                                labelColor = OnDarkSurface
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Add your photo or prompt",
                    style = MaterialTheme.typography.titleMedium,
                    color = OnDarkSurface
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .clip(MaterialTheme.shapes.large)
                        .background(DarkSurface)
                        .border(1.dp, DarkSurfaceVariant, MaterialTheme.shapes.large)
                        .clickable { /* Photo picker or camera */ },
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(Icons.Default.CameraAlt, contentDescription = null, tint = ElectricPink, modifier = Modifier.size(40.dp))
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = "Upload →", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = "Clear photos work best.", color = OnDarkSurface, fontSize = 12.sp)
                    }
                }

                OutlinedTextField(
                    value = promptText,
                    onValueChange = { promptText = it },
                    placeholder = { Text("Add custom instructions (optional)...", color = OnDarkSurface) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = ElectricPink,
                        unfocusedBorderColor = DarkSurfaceVariant,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White
                    )
                )
            }

            Button(
                onClick = { onCreateSubmitted(selectedVibe, promptText) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = ElectricPink),
                shape = MaterialTheme.shapes.extraLarge
            ) {
                Text("Create ✦", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}
