package com.example.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.AppDatabase
import com.example.data.AppRepository
import com.example.data.ChallengeEntity
import com.example.data.UserProfileEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class AppViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: AppRepository

    init {
        val dao = AppDatabase.getDatabase(application).challengeDao()
        repository = AppRepository(dao)
        
        viewModelScope.launch {
            dao.getUserProfile()
        }
    }

    val challenges: Flow<List<ChallengeEntity>> = repository.allChallenges
    val userProfile: Flow<UserProfileEntity?> = repository.userProfile

    private val _isGenerating = MutableStateFlow(false)
    val isGenerating: StateFlow<Boolean> = _isGenerating.asStateFlow()

    private val _currentResult = MutableStateFlow<ChallengeResult?>(null)
    val currentResult: StateFlow<ChallengeResult?> = _currentResult.asStateFlow()

    private val _battleResult = MutableStateFlow<BattleResult?>(null)
    val battleResult: StateFlow<BattleResult?> = _battleResult.asStateFlow()

    fun createChallenge(vibe: String, promptText: String) {
        viewModelScope.launch {
            _isGenerating.value = true
            
            val score = Random.nextInt(88, 99)
            val confidence = Random.nextInt(90, 99)
            val energy = Random.nextInt(88, 99)
            val presence = Random.nextInt(85, 98)
            var title = "AURA CHECK"

            if (vibe.contains("Style", ignoreCase = true)) title = "STYLE WARS"
            else if (vibe.contains("Future", ignoreCase = true)) title = "FUTURE YOU"
            else if (vibe.contains("Guess", ignoreCase = true)) title = "AI GUESS"
            else if (vibe.contains("DailyDrop", ignoreCase = true)) title = "DAILY DROP"

            val rankTitle = if (score >= 93) "LEGENDARY ✦" else "ELITE"

            val result = ChallengeResult(
                title = title,
                score = score,
                rankTitle = rankTitle,
                confidence = confidence,
                energy = energy,
                presence = presence
            )
            _currentResult.value = result

            repository.saveChallenge(
                ChallengeEntity(
                    title = title,
                    category = vibe,
                    score = score,
                    badge = rankTitle
                )
            )

            _isGenerating.value = false
        }
    }

    fun startBattle(opponentName: String = "Alex") {
        viewModelScope.launch {
            val myScore = Random.nextInt(88, 98)
            val oppScore = Random.nextInt(80, myScore + 4)
            val won = myScore >= oppScore
            val xpGain = if (won) 150 else 50
            _battleResult.value = BattleResult(
                myName = "YOU",
                myScore = myScore,
                oppName = opponentName,
                oppScore = oppScore,
                won = won,
                xpGain = xpGain
            )
        }
    }

    fun unlockPro() {
        viewModelScope.launch {
            // Unlock Pro
        }
    }
}

data class ChallengeResult(
    val title: String,
    val score: Int,
    val rankTitle: String,
    val confidence: Int,
    val energy: Int,
    val presence: Int
)

data class BattleResult(
    val myName: String,
    val myScore: Int,
    val oppName: String,
    val oppScore: Int,
    val won: Boolean,
    val xpGain: Int
)
