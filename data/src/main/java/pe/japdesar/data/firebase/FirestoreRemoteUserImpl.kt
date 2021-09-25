package pe.japdesar.data.firebase

import com.google.firebase.firestore.FirebaseFirestore
import pe.japdesar.domain.domainmodel.Result
import pe.japdesar.domain.domainmodel.User
import pe.japdesar.domain.repository.IRemoteUserRepository

class FirestoreRemoteUserImpl (val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()) : IRemoteUserRepository {
    override suspend fun getUser(username: String): Result<Exception, List<User>> {
        TODO("Not yet implemented")
    }

    override suspend fun updateNote(user: User): Result<Exception, Unit> {
        TODO("Not yet implemented")
    }
}