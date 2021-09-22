package pe.juabsa.domain.servicelocator

import pe.juabsa.domain.repository.IAuthRepository

class UserServiceLocator(val authRepository: IAuthRepository)