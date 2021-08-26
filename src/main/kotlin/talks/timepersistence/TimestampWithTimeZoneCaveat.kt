package talks.timepersistence

fun main() {
    withDb {
        update("CREATE TABLE testy (created timestamp with time zone)")
        val value = "2021-01-01 12:00:00+05"
            .println()
        update("INSERT INTO testy VALUES ('$value')")
        query("SELECT created FROM testy") {
            it.next()
            it.getString(1).println()
        }
        update("DROP TABLE testy")
    }
}
