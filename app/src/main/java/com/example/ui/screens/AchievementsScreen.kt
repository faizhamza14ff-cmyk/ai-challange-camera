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
fun AchievementsScreen(onNavigate: (String) -> Unit) {
    val achievements = listOf(
        AchievementItem("First Drop", "Your first challenge.", true),
        AchievementItem("On Fire", "7-day streak.", true),
        AchievementItem("Challenger", "50 challenges completed.", true),
        AchievementItem("Winner", "25 battles won.", true),
        AchievementItem("Viral", "10K views.", false),
        AchievementItem("Legend", "Level 50.", false)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Achievements ✦", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { onNavigate("profile") }) {
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
                Text("Little wins.", style = MaterialTheme.typography.titleMedium, color = OnDarkSurface)
                Spacer(modifier = Modifier.height(8.dp))
            }

            items(achievements) { item ->
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
                            Text("✦", color = if (item.unlocked) CyberGold else OnDarkSurface, fontSize = 20.sp)
                            Spacer(modifier = Modifier.width(16.dp))
                            Column {
                                Text(item.title, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(item.desc, color = OnDarkSurface, fontSize = 13.sp)
                            }
                        }
                        if (item.unlocked) {
                            Surface(color = CyberGold.copy(alpha = 0.2f), shape = MaterialTheme.shapes.small) {
                                Text("Unlocked", color = CyberGold, fontSize = 11.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}

data class AchievementItem(val title: String, val desc: String, val unlocked: Boolean)
