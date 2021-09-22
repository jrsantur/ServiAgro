package pe.juabsa.domain.interactor

import pe.juabsa.domain.domainmodel.User
import pe.juabsa.domain.domainmodel.Result
import pe.juabsa.domain.servicelocator.UserServiceLocator

class AuthSource {

    suspend fun getCurrentUser(locator: UserServiceLocator):
            Result<Exception, User?> = locator.authRepository.getCurrentUser()

    suspend fun deleteCurrentUser(locator: UserServiceLocator):
            Result<Exception, Boolean> = locator.authRepository.deleteCurrentUser()

    suspend fun signOutCurrentUser(locator: UserServiceLocator):
            Result<Exception, Unit> = locator.authRepository.signOutCurrentUser()

    suspend fun createGoogleUser(idToken: String, locator: UserServiceLocator):
            Result<Exception, Unit> = locator.authRepository.createGoogleUser(idToken)

}