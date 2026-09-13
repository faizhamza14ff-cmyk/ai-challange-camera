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
fun ProScreen(
    onNavigate: (String) -> Unit,
    onUnlockPro: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Challenge Pro ✦", color = Color.White) },
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
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Spacer(modifier = Modifier.height(10.dp))
                Text("Go beyond.", style = MaterialTheme.typography.titleMedium, color = CyberGold, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Challenge Pro", style = MaterialTheme.typography.displaySmall, fontWeight = FontWeight.Black, color = Color.White)

                Spacer(modifier = Modifier.height(32.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = DarkSurface),
                    shape = MaterialTheme.shapes.large
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        verticalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        ProFeatureRow("Unlimited challenges.")
                        ProFeatureRow("Premium AI generation.")
                        ProFeatureRow("HD results & downloads.")
                        ProFeatureRow("Zero ads.")
                        ProFeatureRow("Exclusive daily drops.")
                    }
                }
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                Button(
                    onClick = onUnlockPro,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = CyberGold),
                    shape = MaterialTheme.shapes.extraLarge
                ) {
                    Text("Unlock Pro", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Black)
                }
                Spacer(modifier = Modifier.height(12.dp))
                Text("Cancel anytime.", color = OnDarkSurface, fontSize = 13.sp)
            }
        }
    }
}

@Composable
fun ProFeatureRow(text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Icon(Icons.Default.Check, contentDescription = null, tint = CyberGold, modifier = Modifier.size(20.dp))
        Text(text, color = Color.White, fontWeight = FontWeight.Medium, fontSize = 15.sp)
    }
}
