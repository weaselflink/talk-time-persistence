package talks.timepersistence

import java.lang.reflect.Modifier
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.OffsetDateTime
import java.time.ZonedDateTime
import java.util.Date

fun main() {
    listOf<Class<*>>(
        Date::class.java,
        LocalDate::class.java,
        LocalTime::class.java,
        LocalDateTime::class.java,
        OffsetDateTime::class.java,
        ZonedDateTime::class.java,
        Instant::class.java,
    ).forEach { aClass ->
        println(aClass.name)
        aClass.realFields()
            .forEach {
                println("\t${it.name}: ${it.type.name}")
            }
        println()
    }
}

private fun <T> Class<T>.realFields() =
    declaredFields
        .filter {
            !Modifier.isStatic(it.modifiers)
        }
