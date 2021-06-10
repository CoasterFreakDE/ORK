/* (C)2021 */
package cloud.melion.utils

import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import java.math.BigDecimal
import java.sql.ResultSet
import java.util.*

object ObjectMapper {
  val gson = Gson()

  fun mapResultSet(rs: ResultSet): JsonArray {
    val jArray = JsonArray()
    var jsonObject: JsonObject? = null
    val rsmd = rs.metaData
    val columnCount = rsmd.columnCount
    while (rs.next()) {
      jsonObject = JsonObject()
      for (index in 1..columnCount) {
        val column = rsmd.getColumnName(index)
        when (val value = rs.getObject(column)
        ) {
          null -> jsonObject.addProperty(column, "")
          is Int -> jsonObject.addProperty(column, value)
          is String -> jsonObject.addProperty(column, value)
          is Boolean -> jsonObject.addProperty(column, value)
          is Date -> jsonObject.addProperty(column, value.time)
          is Long -> jsonObject.addProperty(column, value)
          is Double -> jsonObject.addProperty(column, value)
          is Float -> jsonObject.addProperty(column, value)
          is BigDecimal -> jsonObject.addProperty(column, value)
          is Byte -> jsonObject.addProperty(column, value)
          else -> throw IllegalArgumentException("Unmappable object type: " + value.javaClass)
        }
      }
      jArray.add(jsonObject)
    }
    return jArray
  }

  inline fun <reified T> map(rs: ResultSet): List<T> {
    val json = mapResultSet(rs)
    val objects = mutableListOf<T>()
    for (dto in json) {
      val element = gson.fromJson(dto, T::class.java)
      objects.add(element)
    }
    return objects
  }
}
