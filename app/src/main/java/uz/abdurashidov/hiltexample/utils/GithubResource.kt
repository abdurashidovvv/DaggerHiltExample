package uz.abdurashidov.hiltexample.utils

import uz.abdurashidov.hiltexample.models.GithubUser

sealed class GithubResource {

    object Loading : GithubResource()
    data class Success(val list: List<GithubUser>) : GithubResource()
    data class Error(val message: String) : GithubResource()
}