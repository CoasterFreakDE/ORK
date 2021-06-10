/* (C)2021 */
package cloud.melion.extensions

import cloud.melion.annotations.PrimaryKey
import cloud.melion.base.getConstructor
import cloud.melion.interfaces.ITable
import kotlin.reflect.jvm.kotlinProperty

fun <T : ITable> T.delete(): T {
  val table = this::class.java
  assert(table.isAnnotationPresent(PrimaryKey::class.java))
  val primaryKeys = table.getAnnotation(PrimaryKey::class.java).keys
  val constructor = getConstructor(table, primaryKeys)

  if (constructor == null) {
    println("No Constructor found for ${table.simpleName}")
    return this
  }

  println("Deleting ${table.simpleName}")
  val sql = StringBuilder("DELETE FROM ${table.simpleName} WHERE ")

  primaryKeys.forEachIndexed { index, key ->
    sql.append("$key = ")

    val value = table.getDeclaredField(key).kotlinProperty?.getter?.call(this)
    if (value != null) {
      sql.append("'$value'")
    }

    if (index < primaryKeys.size - 1) {
      sql.append(", ")
    }
  }

  "Output SQL: `$sql`".send()
  return this
}
