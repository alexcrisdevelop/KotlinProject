package project.kotlin.com.kotlinproject.domain.commands

/**
 * Domain layer
 * */

/**
 * These commands execute an operation and return an object of the class specified
in its generic type. Remember that every function in Kotlin returns a value. Interfaces in Kotlin are more potent than Java (before Java 8) because they can
contain code.
 * */

public interface Command<out T> {
     suspend fun execute(): T
}
