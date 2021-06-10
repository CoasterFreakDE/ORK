/* (C)2021 */
package cloud.melion.base

import cloud.melion.MySQL.onUpdate
import cloud.melion.annotations.FieldName
import cloud.melion.annotations.PrimaryKey
import cloud.melion.errors.NoConstructorFoundError
import cloud.melion.errors.NoPrimaryKeyFoundError
import cloud.melion.errors.WrongAnnotaionError
import cloud.melion.extensions.send

fun createTable(vararg tables: Class<*>) {
  tables.forEach { table ->
    if (!table.isAnnotationPresent(PrimaryKey::class.java))
        throw NoPrimaryKeyFoundError(table.simpleName)

    val primaryKeys = table.getAnnotation(PrimaryKey::class.java).keys
    val constructor =
        getConstructor(table, primaryKeys) ?: throw NoConstructorFoundError(table.simpleName)

    val sql = StringBuilder("CREATE TABLE IF NOT EXISTS ${table.simpleName}(")

    constructor.parameters.forEachIndexed { index, parameter ->
      if (constructor.parameterAnnotations[index].isEmpty())
          throw WrongAnnotaionError(table.simpleName)

      val annotation =
          constructor.parameterAnnotations[index].find { annotation ->
            annotation is FieldName
          } as FieldName?
              ?: throw WrongAnnotaionError(table.simpleName)

      sql.append("${getNameOfParam(annotation)} ${getSQLTypeOrDefault(annotation, parameter.type)}")
      try {
        val defaultValue = getDefaultValueOfParam(annotation)
        sql.append(" DEFAULT '$defaultValue'")
      } catch (npe: NullPointerException) {}
      sql.append(", ")
    }
    sql.append("PRIMARY KEY(")
    primaryKeys.forEachIndexed { index, key ->
      if (index >= primaryKeys.size - 1) {
        sql.append("$key))")
      } else {
        sql.append("$key, ")
      }
    }

    "Output SQL: `$sql`".send()
    onUpdate(sql.toString())
  }
}
