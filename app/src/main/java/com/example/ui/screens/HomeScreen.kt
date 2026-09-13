package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*

@Composable
fun HomeScreen(
    onNavigate: (String) -> Unit,
    onSelectChallenge: (String) -> Unit
) {
    val challenges = listOf(
        ChallengeItem("Aura Check", "How much aura do you have?", Icons.Default.FlashOn, ElectricPink),
        ChallengeItem("Future You", "Meet the version of you that's coming.", Icons.Default.AutoAwesome, NeonCyan),
        ChallengeItem("Style Wars", "Your fit vs. theirs.", Icons.Default.Checkroom, CyberGold),
        ChallengeItem("Guess Me", "Let AI take a guess.", Icons.Default.Psychology, NeonPurple),
        ChallengeItem("You vs. Friend", "One challenge. Two results.", Icons.Default.People, Color(0xFF4CAF50))
    )

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = DarkSurface,
                tonalElevation = 8.dp
            ) {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") },
                    selected = true,
                    onClick = {}
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.AddCircle, contentDescription = "Create") },
                    label = { Text("Create") },
                    selected = false,
                    onClick = { onNavigate("create") }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.LocalFireDepartment, contentDescription = "Trending") },
                    label = { Text("Trending") },
                    selected = false,
                    onClick = { onNavigate("trending") }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Leaderboard, contentDescription = "Leaderboard") },
                    label = { Text("Leaderboard") },
                    selected = false,
                    onClick = { onNavigate("leaderboard") }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                    label = { Text("Profile") },
                    selected = false,
                    onClick = { onNavigate("profile") }
                )
            }
        }
    ) { paddingVals ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(DarkBackground)
                .padding(paddingVals)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Good evening, Alex",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            text = "Ready for your next challenge?",
                            style = MaterialTheme.typography.bodyMedium,
                            color = OnDarkSurface
                        )
                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Surface(
                            shape = MaterialTheme.shapes.small,
                            color = DarkSurfaceVariant,
                            modifier = Modifier.clickable { onNavigate("rewards") }
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(Icons.Default.Star, contentDescription = null, tint = CyberGold, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("2,450", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                            }
                        }
                        Surface(
                            shape = MaterialTheme.shapes.small,
                            color = DarkSurfaceVariant,
                            modifier = Modifier.clickable { onNavigate("streak") }
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text("🔥", fontSize = 14.sp)
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("7d", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                            }
                        }
                    }
                }
            }

            // Daily Drop Banner
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onNavigate("dailydrop") },
                    colors = CardDefaults.cardColors(containerColor = DarkSurface),
                    shape = MaterialTheme.shapes.large
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                Brush.horizontalGradient(
                                    colors = listOf(NeonPurple.copy(alpha = 0.4f), ElectricPink.copy(alpha = 0.4f))
                                )
                            )
                            .padding(20.dp)
                    ) {
                        Column {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text("DAILY DROP ✦", color = CyberGold, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                                Text("08 : 42 : 17", color = Color.White, fontWeight = FontWeight.Medium, fontSize = 12.sp)
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Text("WHO HAS MORE AURA?", color = Color.White, fontWeight = FontWeight.ExtraBold, fontSize = 20.sp)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text("+500 XP • +100 coins • 1 badge", color = OnDarkSurface, fontSize = 13.sp)
                        }
                    }
                }
            }

            // For You Header
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "For you ✦",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    TextButton(onClick = { onNavigate("trending") }) {
                        Text("Explore all", color = ElectricPink)
                    }
                }
            }

            // Challenge Cards Horizontal/Vertical List
            items(challenges) { item ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onSelectChallenge(item.title) },
                    colors = CardDefaults.cardColors(containerColor = DarkSurface),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .clip(MaterialTheme.shapes.medium)
                                .background(item.color.copy(alpha = 0.2f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(item.icon, contentDescription = null, tint = item.color, modifier = Modifier.size(24.dp))
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(text = item.title, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(text = item.subtitle, color = OnDarkSurface, fontSize = 13.sp)
                        }
                        Icon(Icons.Default.ChevronRight, contentDescription = null, tint = OnDarkSurface)
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

data class ChallengeItem(
    val title: String,
    val subtitle: String,
    val icon: ImageVector,
    val color: Color
)
