package talks.timepersistence

fun main() {
    withDb {
        update("CREATE TABLE testy (created timestamp with time zone)")
        val value = "2021-01-01 12:00:00+05"
            .also { println(it) }
        update("INSERT INTO testy VALUES ('$value')")
        query("SELECT created FROM testy") {
            it.next()
            println(it.getString(1))
        }
        update("DROP TABLE testy")
    }
}
