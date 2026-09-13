package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
fun LeaderboardScreen(onNavigate: (String) -> Unit) {
    val leaders = listOf(
        LeaderEntry("01", "Alex", "98.4K XP", CyberGold),
        LeaderEntry("02", "Ryan", "91.8K XP", Color(0xFFE0E0E0)),
        LeaderEntry("03", "Sam", "87.4K XP", Color(0xFFCD7F32)),
        LeaderEntry("04", "Noah", "82.1K XP", OnDarkSurface),
        LeaderEntry("05", "Jay", "79.5K XP", OnDarkSurface)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Leaderboard ✦", color = Color.White, fontWeight = FontWeight.Bold) },
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
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    Text(
                        text = "Global Top Players",
                        style = MaterialTheme.typography.titleMedium,
                        color = OnDarkSurface
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }

                items(leaders) { leader ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = DarkSurface),
                        shape = MaterialTheme.shapes.medium
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = leader.rank,
                                    color = leader.badgeColor,
                                    fontWeight = FontWeight.Black,
                                    fontSize = 18.sp,
                                    modifier = Modifier.width(36.dp)
                                )
                                Spacer(modifier = Modifier.width(12.dp))
                                Text(
                                    text = leader.name,
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp
                                )
                            }
                            Text(
                                text = leader.xp,
                                color = CyberGold,
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp
                            )
                        }
                    }
                }
            }

            // User Rank Card at bottom
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = DarkSurfaceVariant),
                shape = MaterialTheme.shapes.medium
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text("Keep going.", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                        Spacer(modifier = Modifier.height(2.dp))
                        Text("Your rank: #247", color = ElectricPink, fontSize = 13.sp)
                    }
                    Button(
                        onClick = { onNavigate("create") },
                        colors = ButtonDefaults.buttonColors(containerColor = ElectricPink),
                        shape = MaterialTheme.shapes.small
                    ) {
                        Text("Climb Rank")
                    }
                }
            }
        }
    }
}

data class LeaderEntry(
    val rank: String,
    val name: String,
    val xp: String,
    val badgeColor: Color
)
