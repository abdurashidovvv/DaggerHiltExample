package uz.abdurashidov.hiltexample.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.abdurashidov.hiltexample.models.GithubUser
import uz.abdurashidov.hiltexample.repository.GithubRepository
import uz.abdurashidov.hiltexample.utils.GithubResource
import javax.inject.Inject

@HiltViewModel
class GithubViewModel @Inject constructor(private val githubRepository: GithubRepository) :
    ViewModel() {

    private val stateFlow = MutableStateFlow<GithubResource>(GithubResource.Loading)


    fun fetchUser() : MutableStateFlow<GithubResource>{
        viewModelScope.launch {
            stateFlow.value = GithubResource.Loading
            val flow = githubRepository.getUsers()
            flow.catch {
                stateFlow.value = GithubResource.Error(it.message ?: "Error")
            }.collect {
                if (it.isSuccessful) {
                    val list = ArrayList<GithubUser>()
                    it.body()?.forEach { user ->
                        list.add(user)
                    }
                    stateFlow.value = GithubResource.Success(list)
                } else {
                    stateFlow.value = GithubResource.Error("It is not successfully !")
                }
            }
        }
        return stateFlow
    }
}