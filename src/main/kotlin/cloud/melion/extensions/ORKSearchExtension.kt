/* (C)2021 */
package cloud.melion.extensions

import cloud.melion.MySQL
import cloud.melion.interfaces.ITable
import cloud.melion.utils.ObjectMapper
import java.util.*
import kotlin.reflect.jvm.ExperimentalReflectionOnLambdas
import kotlin.reflect.jvm.reflect

inline fun <reified T : ITable> search(fields: Map<String, Any>): Optional<List<T>> {
  val table = T::class.java
  val sql = StringBuilder("SELECT * FROM ${table.simpleName} ")

  if (fields.isNotEmpty()) {
    sql.append("WHERE ")
  }

  var index = 1
  fields.forEach { (key, value) ->
    sql.append("$key = '$value'")
    if (index < fields.size) {
      index++
      sql.append(" AND ")
    }
  }

  "Output SQL: `$sql`".send()
  val resultSet = MySQL.onQuery(sql.toString()) ?: return Optional.empty()
  val list = ObjectMapper.map<T>(resultSet)
  return Optional.of(list)
}


@OptIn(ExperimentalReflectionOnLambdas::class)
inline fun <reified T : ITable> select(noinline dsl: T.() -> Unit): Optional<List<T>> {
  val table = T::class.java.getConstructor().newInstance().apply(dsl)
  table.toString().send()

  return Optional.empty()
}