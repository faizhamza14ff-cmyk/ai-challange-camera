package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
fun TrendingScreen(
    onNavigate: (String) -> Unit,
    onJoinChallenge: (String) -> Unit
) {
    val trendingList = listOf(
        TrendingItem("01", "Aura Check", "1.2M plays", ElectricPink),
        TrendingItem("02", "Future You", "843K plays", NeonCyan),
        TrendingItem("03", "Style Wars", "621K plays", CyberGold),
        TrendingItem("04", "Guess Me", "507K plays", NeonPurple),
        TrendingItem("05", "You vs Friend", "412K plays", Color(0xFF4CAF50))
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Trending ✦", color = Color.White, fontWeight = FontWeight.Bold) },
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingVals)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Text(
                    text = "What's happening now ✦",
                    style = MaterialTheme.typography.titleMedium,
                    color = OnDarkSurface
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            items(trendingList) { item ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onJoinChallenge(item.title) },
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
                                text = item.rank,
                                color = item.color,
                                fontWeight = FontWeight.Black,
                                fontSize = 18.sp,
                                modifier = Modifier.width(36.dp)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text(
                                    text = item.title,
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = item.plays,
                                    color = OnDarkSurface,
                                    fontSize = 13.sp
                                )
                            }
                        }
                        Button(
                            onClick = { onJoinChallenge(item.title) },
                            colors = ButtonDefaults.buttonColors(containerColor = item.color.copy(alpha = 0.2f)),
                            shape = MaterialTheme.shapes.small,
                            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp)
                        ) {
                            Text("Join", color = item.color, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                        }
                    }
                }
            }
        }
    }
}

data class TrendingItem(
    val rank: String,
    val title: String,
    val plays: String,
    val color: Color
)
