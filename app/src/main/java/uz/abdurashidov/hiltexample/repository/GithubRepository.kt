package uz.abdurashidov.hiltexample.repository

import kotlinx.coroutines.flow.flow
import uz.abdurashidov.hiltexample.networking.ApiService
import uz.abdurashidov.hiltexample.utils.GithubResource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getUsers() = flow { emit(apiService.getUsers()) }
}