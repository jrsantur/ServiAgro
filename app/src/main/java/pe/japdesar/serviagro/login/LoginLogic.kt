package pe.japdesar.serviagro.login

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import androidx.lifecycle.Observer
import pe.japdesar.domain.DispatcherProvider
import pe.japdesar.domain.domainmodel.Result
import pe.japdesar.domain.error.AgroError
import pe.japdesar.domain.interactor.AuthSource
import pe.japdesar.domain.servicelocator.UserServiceLocator
import pe.japdesar.serviagro.common.BaseLogic

class LoginLogic(dispatcher: DispatcherProvider,
                 val userLocator: UserServiceLocator,
                 val view: ILoginContract.View,
                 val authSource: AuthSource
) : BaseLogic(dispatcher), CoroutineScope, Observer<LoginEvent<LoginResult>> {


    init {
        jobTracker = Job()
    }

    override val coroutineContext: CoroutineContext
        get() = dispatcher.provideUIContext() + jobTracker


    override fun onChanged(event: LoginEvent<LoginResult>) {
        when (event) {
            is LoginEvent.OnStart -> onStart()
            is LoginEvent.OnDestroy -> jobTracker.cancel()
            is LoginEvent.OnBackClick -> onBackClick()
            is LoginEvent.OnAuthButtonClick -> onAuthButtonClick(event.username,event.pass )
            is LoginEvent.OnRegisterButtonClick -> onRegisterButtonClick()
            is LoginEvent.OnGoogleSignInResult -> onSignInResult(event.result)
        }
    }

    private fun onSignInResult(result: LoginResult) = launch {
        if (result.requestCode == RC_SIGN_IN && result.account != null) {
            view.showLoopAnimation()

            val createGoogleUserResult = authSource.createGoogleUser(
                result.account.idToken!!,
                userLocator
            )

            when (createGoogleUserResult) {
                is Result.Value -> onStart()
                is Result.Error -> handleError(createGoogleUserResult.error)
            }
        } else {
            renderErrorState(ERROR_AUTH)
        }
    }

    private fun onAuthButtonClick(username:String, pass: String) = launch {
        view.showLoopAnimation()

        val authResult = authSource.loginUser(username,pass,userLocator)
        when (authResult) {
            is Result.Value -> {
                if (authResult.value) view.startSignInButton(username,pass)
            }
        }

        /*
        val authResult = authSource.getCurrentUser(userLocator)

        when (authResult) {
            is Result.Value -> {
                if (authResult.value == null) view.startSignInFlow()
                else signUserOut()
            }

            is Result.Error -> handleError(authResult.error)
        }*/

    }

    private fun handleError(error: Exception) {

        when (error) {
            is AgroError.NetworkUnavailableException -> renderErrorState(
                ERROR_NETWORK_UNAVAILABLE
            )

            else -> renderErrorState(ERROR_AUTH)
        }
    }

    private suspend fun signUserOut() {
        val signOutResult = authSource.signOutCurrentUser(userLocator)

        when (signOutResult) {
            is Result.Value -> renderNullUser()
            is Result.Error -> renderErrorState(ERROR_AUTH)
        }

    }

    private fun onBackClick() {
        view.startListFeature()
    }

    private fun onRegisterButtonClick(){
        view.onShowActivityRegister()
    }

    private fun onStart() = launch {
        //view.showLoopAnimation()

        val authResult = authSource.getCurrentUser(userLocator)

        when (authResult) {
            is Result.Value -> {
                if (authResult.value == null) renderNullUser()
                else renderActiveUser()
            }

            is Result.Error -> handleError(authResult.error)
        }
    }

    private fun renderActiveUser() {
        view.setStatusDrawable(ANTENNA_FULL)
        view.setAuthButton(SIGN_OUT)
        view.setLoginStatus(SIGNED_IN)
    }

    private fun renderNullUser() {
        view.setStatusDrawable(ANTENNA_EMPTY)
        view.setAuthButton(SIGN_IN)
        view.setLoginStatus(SIGNED_OUT)
    }

    private fun renderErrorState(message: String) {
        view.setStatusDrawable(ANTENNA_EMPTY)
        view.setAuthButton(RETRY)
        view.setLoginStatus(message)
    }

}