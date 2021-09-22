package pe.juabsa.domain.error

sealed class AgroError : Exception() {

    object LocalIOException: AgroError()
    object RemoteIOException: AgroError()
    object NetworkUnavailableException: AgroError()
    object AuthError: AgroError()
    object TransactionIOException : AgroError()
}

const val ERROR_UPDATE_FAILED = "Update operation unsuccessful."
const val ERROR_DELETE_FAILED = "Delete operation unsuccessful."
