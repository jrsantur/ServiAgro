package pe.japdesar.domain.servicelocator

import pe.japdesar.domain.repository.IAuthRepository

class UserServiceLocator(val authRepository: IAuthRepository)