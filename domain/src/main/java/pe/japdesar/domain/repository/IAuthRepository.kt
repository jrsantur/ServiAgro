package pe.japdesar.domain.repository

import pe.japdesar.domain.domainmodel.User
import pe.japdesar.domain.domainmodel.Result

interface IAuthRepository {

    suspend fun getCurrentUser(): Result<Exception, User?>

    suspend fun signOutCurrentUser(): Result<Exception, Unit>

    suspend fun deleteCurrentUser(): Result<Exception, Boolean>

    suspend fun createGoogleUser(idToken: String): Result<Exception, Unit>

}