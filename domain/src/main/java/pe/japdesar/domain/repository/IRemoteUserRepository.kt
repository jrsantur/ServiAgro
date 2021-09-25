package pe.japdesar.domain.repository

import pe.japdesar.domain.domainmodel.User
import pe.japdesar.domain.domainmodel.Result

interface IRemoteUserRepository {

    suspend fun getUser(username:String):Result<Exception, List<User>>

    suspend fun updateNote(user: User):Result<Exception, Unit>

}