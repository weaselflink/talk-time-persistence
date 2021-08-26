package talks.timepersistence

import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

fun main() {
    val formatter = DateTimeFormatter
        .ofPattern("yyyy-MM-dd HH:mm:ss")

    withDb {
        val myTime = ZonedDateTime
            .parse("2021-01-01T12:00:03+11:00[Australia/Sydney]")
            .println()
        val asString = formatter.format(myTime)
            .println()
        println()

        update("CREATE TABLE testy (my_time timestamp, my_zone varchar)")
        update("INSERT INTO testy VALUES ('$asString', '${myTime.zone}')")
        query("SELECT my_time, my_zone FROM testy") { rs ->
            while (rs.next()) {
                LocalDateTime
                    .parse(rs.getString(1), formatter)
                    .atZone(ZoneId.of(rs.getString(2)))
                    .println()
            }
        }
        update("DROP TABLE testy")
    }
}
