package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ui.screens.*
import com.example.ui.theme.MyApplicationTheme
import com.example.viewmodel.AppViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: AppViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()
                val currentResult by viewModel.currentResult.collectAsState()
                val battleResult by viewModel.battleResult.collectAsState()

                NavHost(
                    navController = navController,
                    startDestination = "welcome",
                    modifier = Modifier.fillMaxSize()
                ) {
                    composable("welcome") {
                        WelcomeScreen(
                            onGetStarted = { navController.navigate("home") }
                        )
                    }
                    composable("home") {
                        HomeScreen(
                            onNavigate = { route -> navController.navigate(route) },
                            onSelectChallenge = { vibe -> navController.navigate("create?vibe=$vibe") }
                        )
                    }
                    composable("create") {
                        CreateScreen(
                            initialVibe = "Aura",
                            onBack = { navController.popBackStack() },
                            onCreateSubmitted = { vibe, prompt ->
                                viewModel.createChallenge(vibe, prompt)
                                navController.navigate("generating")
                            }
                        )
                    }
                    composable("generating") {
                        GeneratingScreen(
                            onFinished = { navController.navigate("result") }
                        )
                    }
                    composable("result") {
                        val res = currentResult
                        ResultScreen(
                            score = res?.score ?: 94,
                            rankTitle = res?.rankTitle ?: "LEGENDARY",
                            confidence = res?.confidence ?: 96,
                            energy = res?.energy ?: 95,
                            presence = res?.presence ?: 94,
                            onChallengeFriend = {
                                viewModel.startBattle()
                                navController.navigate("battle")
                            },
                            onShare = { navController.navigate("share") },
                            onDone = { navController.navigate("home") }
                        )
                    }
                    composable("battle") {
                        val bRes = battleResult
                        BattleScreen(
                            myScore = bRes?.myScore ?: 94,
                            oppName = bRes?.oppName ?: "ALEX",
                            oppScore = bRes?.oppScore ?: 87,
                            won = bRes?.won ?: true,
                            xpGain = bRes?.xpGain ?: 150,
                            onRematch = {
                                viewModel.startBattle()
                            },
                            onShare = { navController.navigate("share") },
                            onClose = { navController.navigate("home") }
                        )
                    }
                    composable("trending") {
                        TrendingScreen(
                            onNavigate = { route -> navController.navigate(route) },
                            onJoinChallenge = { title -> navController.navigate("create?vibe=$title") }
                        )
                    }
                    composable("leaderboard") {
                        LeaderboardScreen(
                            onNavigate = { route -> navController.navigate(route) }
                        )
                    }
                    composable("dailydrop") {
                        DailyDropScreen(
                            onNavigate = { route -> navController.navigate(route) },
                            onTakeChallenge = { navController.navigate("create?vibe=DailyDrop") }
                        )
                    }
                    composable("profile") {
                        ProfileScreen(
                            onNavigate = { route -> navController.navigate(route) }
                        )
                    }
                    composable("achievements") {
                        AchievementsScreen(
                            onNavigate = { route -> navController.navigate(route) }
                        )
                    }
                    composable("streak") {
                        StreakScreen(
                            onNavigate = { route -> navController.navigate(route) },
                            onKeepGoing = { navController.navigate("create") }
                        )
                    }
                    composable("rewards") {
                        RewardsScreen(
                            onNavigate = { route -> navController.navigate(route) },
                            onEarnMore = { navController.navigate("create") }
                        )
                    }
                    composable("pro") {
                        ProScreen(
                            onNavigate = { route -> navController.navigate(route) },
                            onUnlockPro = { viewModel.unlockPro(); navController.navigate("home") }
                        )
                    }
                    composable("share") {
                        ShareScreen(
                            onNavigate = { route -> navController.navigate(route) }
                        )
                    }
                    composable("notifications") {
                        NotificationsScreen(
                            onNavigate = { route -> navController.navigate(route) }
                        )
                    }
                    composable("empty") {
                        EmptyStateScreen(
                            onCreateClick = { navController.navigate("create") }
                        )
                    }
                    composable("offline") {
                        OfflineScreen(
                            onRetry = { navController.navigate("home") }
                        )
                    }
                    composable("report") {
                        ReportScreen(
                            onClose = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}
