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
            .printIt()
        val asString = formatter.format(myTime)
            .printIt()
        println()

        update("CREATE TABLE testy (my_time timestamp)")
        update("INSERT INTO testy VALUES ('$myTime')")
        update("INSERT INTO testy VALUES ('$asString')")
        query("SELECT my_time FROM testy") { rs ->
            while (rs.next()) {
                ZonedDateTime
                    .parse(rs.getString(1), formatter)
                    .toInstant()
                    .printIt()
            }
        }
        update("DROP TABLE testy")
    }
}
