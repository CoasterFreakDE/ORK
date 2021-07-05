/* (C)2021 */
package cloud.melion.base

import cloud.melion.MySQL
import java.sql.ResultSet

fun query(sql: String, result: (ResultSet) -> Unit) = MySQL.onQuery(sql, result)
