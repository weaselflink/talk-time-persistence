package talks.timepersistence

import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

fun main() {
    val formatter = DateTimeFormatter
        .ofPattern("yyyy-MM-dd HH:mm:ss")
        .withZone(ZoneId.of("UTC"))

    withDb {
        val myTime = Instant.parse("2021-01-01T12:00:03Z")
            .also { println(it) }
        val asString = formatter.format(myTime)

        update("CREATE TABLE testy (my_time timestamp)")
        update("INSERT INTO testy VALUES ('$asString')")
        query("SELECT my_time FROM testy") {
            it.next()
            println(
                ZonedDateTime
                    .parse(it.getString(1), formatter)
                    .toInstant()
            )
        }
        update("DROP TABLE testy")
    }
}
