package talks.timepersistence

import org.testcontainers.containers.PostgreSQLContainer
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet

class CustomSQLContainer : PostgreSQLContainer<CustomSQLContainer>("postgres:11.11-alpine") {
    init {
        withDatabaseName("demo")
        withUsername("demo")
        withPassword("demo")
        withCommand("postgres")
    }
}

fun withDb(block: Connection.() -> Unit) {
    val sqlContainer = CustomSQLContainer()
        .also { it.start() }
    DriverManager.getConnection(sqlContainer.jdbcUrl, "demo", "demo")
        .use(block)
    sqlContainer.stop()
}

fun Connection.update(sql: String): Unit =
    createStatement()
        .also { statement ->
            statement.executeUpdate(sql)
        }
        .close()

fun Connection.query(sql: String, block: (ResultSet) -> Unit = {}) =
    createStatement()
        .let { statement ->
            statement.executeQuery(sql)
        }
        .also(block)
        .close()
