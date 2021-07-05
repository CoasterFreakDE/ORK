/* (C)2021 */
package cloud.melion.base

import cloud.melion.MySQL
import cloud.melion.annotations.APIObject
import java.sql.ResultSet

@APIObject
fun query(sql: String, result: (ResultSet) -> Unit) = MySQL.onQuery(sql, result)
