package com.example.data

import kotlinx.coroutines.flow.Flow

class AppRepository(private val dao: ChallengeDao) {
    val allChallenges: Flow<List<ChallengeEntity>> = dao.getAllChallenges()
    val userProfile: Flow<UserProfileEntity?> = dao.getUserProfile()

    suspend fun saveChallenge(challenge: ChallengeEntity) {
        dao.insertChallenge(challenge)
    }

    suspend fun updateProfile(profile: UserProfileEntity) {
        dao.upsertProfile(profile)
    }
}
