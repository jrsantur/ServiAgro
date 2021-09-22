package pe.juabsa.serviagro.common

import kotlinx.coroutines.Job
import pe.juabsa.domain.DispatcherProvider

/**
 * Why use a base class? To both share implementation (properties and functions), and enforce a contract (interface) for all listener classes
 */
abstract class BaseLogic(val dispatcher: DispatcherProvider) {

    protected lateinit var jobTracker: Job
}