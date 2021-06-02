/* (C)2021 */
package cloud.melion

import cloud.melion.data.ConnectionSettings
import cloud.melion.extensions.send
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.SQLException

object MySQL {
  private var conn: Connection? = null
  fun connect(connectionSettings: ConnectionSettings) {
    conn = null
    try {
      var url =
          "jdbc:${connectionSettings.driver}://${connectionSettings.host}:${connectionSettings.port}/${connectionSettings.database}?autoReconnect=${connectionSettings.autoReconnect}&user=${connectionSettings.username}&password=${connectionSettings.password}"
      if (connectionSettings.driver == "sqlite") {
        url = "jdbc:sqlite:${connectionSettings.host}"
      }
      "Trying to connect to ($url).".send()
      conn = DriverManager.getConnection(url)
      "Connection established.".send()
    } catch (e: Exception) {
      "Connection failed.".send()
      listOf(*e.stackTrace).forEach { stackTraceElement: StackTraceElement ->
        println(stackTraceElement.toString())
      }
    }
  }

  fun isConnected(): Boolean = conn?.isClosed?.not() ?: false

  fun disconnect() {
    try {
      if (conn != null) {
        conn!!.close()
        "Connection closed.".send()
      }
    } catch (e: SQLException) {
      e.printStackTrace()
    }
  }

  fun onUpdate(sql: String) {
    try {
      val stmt = conn!!.createStatement()
      stmt.execute(sql)
    } catch (e: Exception) {
      e.printStackTrace()
    }
  }

  fun onQuery(sql: String, result: (ResultSet) -> Unit) {
    try {
      val stmt = conn!!.createStatement()
      val qry = stmt.executeQuery(sql)
      qry?.let { result.invoke(it) }
    } catch (e: Exception) {
      e.printStackTrace()
    }
  }
}
