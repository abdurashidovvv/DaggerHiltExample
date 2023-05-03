package uz.abdurashidov.hiltexample.networking

import retrofit2.Response
import retrofit2.http.GET
import uz.abdurashidov.hiltexample.models.GithubUser

interface ApiService {

    @GET("users")
    suspend fun getUsers(): Response<List<GithubUser>>
}