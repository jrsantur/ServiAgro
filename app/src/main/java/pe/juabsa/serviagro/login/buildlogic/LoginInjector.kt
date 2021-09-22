package pe.juabsa.serviagro.login.buildlogic

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.google.firebase.FirebaseApp
import pe.juabsa.data.auth.FirebaseAuthRepositoryImpl
import pe.juabsa.domain.DispatcherProvider
import pe.juabsa.domain.interactor.AuthSource
import pe.juabsa.domain.repository.IAuthRepository
import pe.juabsa.domain.servicelocator.UserServiceLocator
import pe.juabsa.serviagro.login.LoginActivity
import pe.juabsa.serviagro.login.LoginLogic

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