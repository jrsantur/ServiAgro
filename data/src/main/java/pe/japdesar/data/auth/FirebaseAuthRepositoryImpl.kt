package pe.japdesar.data.auth

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.google.android.gms.tasks.Tasks
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import pe.japdesar.data.awaitTaskCompletable
import pe.japdesar.domain.repository.IAuthRepository
import pe.japdesar.domain.domainmodel.Result
import pe.japdesar.domain.domainmodel.User

class FirebaseAuthRepositoryImpl(val auth: FirebaseAuth = FirebaseAuth.getInstance()) : IAuthRepository {

    override suspend fun createGoogleUser(idToken: String):
            Result<Exception, Unit> = withContext(Dispatchers.IO) {
        try {
            val credential = GoogleAuthProvider.getCredential(idToken, null)
            awaitTaskCompletable(auth.signInWithCredential(credential))

            Tasks.await(auth.signInWithCredential(credential))

            Result.build { Unit }
        } catch (e: Exception) {
            Result.build { throw e }
        }
    }


    override suspend fun signOutCurrentUser(): Result<Exception, Unit> {
        return Result.build {
            auth.signOut()
        }
    }


    override suspend fun deleteCurrentUser(): Result<Exception, Boolean> {
        return try {
            //val user = auth.currentUser ?: throw SpaceNotesError.AuthError

            //awaitTaskCompletable(user.delete())
            Result.build { true }
        } catch (exception: Exception) {
            Result.build { throw exception }
        }
    }

    override suspend fun signIn(username: String, passowrd: String): Result<Exception, Boolean> {
        var saveisSuccessful: Boolean = false

        auth.signInWithEmailAndPassword(username,passowrd)
            .addOnCompleteListener(){ task ->
                saveisSuccessful = task.isSuccessful
            }
        return Result.build { saveisSuccessful }
    }

    override suspend fun getCurrentUser(): Result<Exception, User?> {
        val firebaseUser = auth.currentUser

        if (firebaseUser == null) return Result.build { null }
        else return Result.build {
            User(
                firebaseUser.uid,
                firebaseUser.displayName ?: "",
                firebaseUser.photoUrl.toString()
            )
        }
    }


}