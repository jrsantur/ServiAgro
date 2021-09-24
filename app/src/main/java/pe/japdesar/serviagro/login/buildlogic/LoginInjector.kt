package pe.japdesar.serviagro.login.buildlogic

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.google.firebase.FirebaseApp
import pe.japdesar.data.auth.FirebaseAuthRepositoryImpl
import pe.japdesar.domain.DispatcherProvider
import pe.japdesar.domain.interactor.AuthSource
import pe.japdesar.domain.repository.IAuthRepository
import pe.japdesar.domain.servicelocator.UserServiceLocator
import pe.japdesar.serviagro.login.LoginActivity
import pe.japdesar.serviagro.login.LoginLogic

class LoginInjector(application: Application) : AndroidViewModel(application) {
    init {
        FirebaseApp.initializeApp(application)
    }

    //For user management
    private val auth: IAuthRepository by lazy {
        //by using lazy, I don't load this resource until I need it
        FirebaseAuthRepositoryImpl()
    }


    fun buildLoginLogic(view: LoginActivity): LoginLogic = LoginLogic(
        DispatcherProvider,
        UserServiceLocator(auth),
        view,
        AuthSource()
    ).also { view.setObserver(it) }
}