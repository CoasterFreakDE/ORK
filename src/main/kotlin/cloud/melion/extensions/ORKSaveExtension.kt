/* (C)2021 */
package cloud.melion.extensions

import cloud.melion.annotations.FieldName
import cloud.melion.annotations.PrimaryKey
import cloud.melion.base.getConstructor
import cloud.melion.base.update
import cloud.melion.interfaces.ITable
import kotlin.reflect.jvm.kotlinProperty

fun <T : ITable> T.save(): T {
	val table = this::class.java
	assert(table.isAnnotationPresent(PrimaryKey::class.java))
	val primaryKeys = table.getAnnotation(PrimaryKey::class.java).keys
	val constructor = getConstructor(table, primaryKeys)

	if (constructor == null) {
		println("No Constructor found for ${table.simpleName}")
		return this
	}

	val sql = StringBuilder("REPLACE INTO ${table.simpleName}(")

	constructor.parameterAnnotations.forEachIndexed { index, annotations ->
		val annotation = annotations[0] as FieldName

		if (index >= constructor.parameterAnnotations.size - 1) {
			sql.append("${annotation.name})")
		} else {
			sql.append("${annotation.name}, ")
		}
	}

	sql.append(" VALUES(")

	constructor.parameterAnnotations.forEachIndexed { index, annotations ->
		val annotation = annotations[0] as FieldName

		val value = table.getDeclaredField(annotation.name).kotlinProperty?.getter?.call(this)
		if (value != null) {
			sql.append("'$value'")
		}

		if (index >= constructor.parameterAnnotations.size - 1) {
			sql.append(")")
		} else {
			sql.append(", ")
		}
	}

	"Output SQL: `$sql`".send()
	update(sql.toString())
	return this
}
