package kazanexpress.ru.javahack.ui.profile

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import kazanexpress.ru.javahack.data.LoginDataSource
import kazanexpress.ru.javahack.data.LoginRepository

class ProfileViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            return ProfileViewModel(
                    loginRepository = LoginRepository(
                            dataSource = LoginDataSource()
                    )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
