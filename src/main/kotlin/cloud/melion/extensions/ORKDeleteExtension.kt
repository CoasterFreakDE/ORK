/* (C)2021 */
package cloud.melion.extensions

import cloud.melion.annotations.APIObject
import cloud.melion.annotations.PrimaryKey
import cloud.melion.base.getConstructor
import cloud.melion.base.update
import cloud.melion.interfaces.ITable
import kotlin.reflect.jvm.kotlinProperty

@APIObject
fun <T : ITable> T.delete(): T {
	val table = this::class.java
	assert(table.isAnnotationPresent(PrimaryKey::class.java))
	val primaryKeys = table.getAnnotation(PrimaryKey::class.java).keys
	val constructor = getConstructor(table, primaryKeys)

	if (constructor == null) {
		println("No Constructor found for ${table.simpleName}")
		return this
	}

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
	update(sql.toString())
	return this
}
