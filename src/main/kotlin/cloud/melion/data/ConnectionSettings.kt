/* (C)2021 */
package cloud.melion.data

data class ConnectionSettings(
    val host: String,
    val database: String,
    val username: String,
    val password: String,
    val driver: String = "mysql",
    val port: Int = 3306,
    val autoReconnect: Boolean = true)
