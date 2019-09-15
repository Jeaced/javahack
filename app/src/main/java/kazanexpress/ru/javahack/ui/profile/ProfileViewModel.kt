package kazanexpress.ru.javahack.ui.profile

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import kazanexpress.ru.javahack.data.LoginRepository
import kazanexpress.ru.javahack.data.model.ClientInfoResponse
import kazanexpress.ru.javahack.network.RestApiService
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class ProfileViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private var token: String? = null
    private val clientData: MutableLiveData<ClientInfoResponse> by lazy {
        MutableLiveData<ClientInfoResponse>().also {
            update()
        }
    }

    fun getClientData(): LiveData<ClientInfoResponse> {
        return clientData
    }

    private val restApiService = RestApiService.getInstance()!!

    init {
        val user = loginRepository.user
        if (user != null) {
            token = user.accessToken
        }
    }

    fun update() {
        Log.e("FSF", "FASF")
        // can be launched in a separate asynchronous job
        if (token == null) {
            Log.e("token", "null")
            return
        }
        doAsync {
            val result = restApiService.getClientInfo(token!!).execute()
            Log.e("VM", result.code().toString())
            uiThread {
                if (result.isSuccessful) {
                    clientData.value = result.body()!!
                }
            }
        }
    }
}