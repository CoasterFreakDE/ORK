/* (C)2021 */
package cloud.melion.base

import cloud.melion.annotations.FieldName
import java.util.*

private fun getSQLType(type: Class<*>) =
	when (type) {
		String::class.java -> "VARCHAR(255)"
		Int::class.java -> "INT(11)"
		Long::class.java -> "BIGINT(19)"
		UUID::class.java -> "VARCHAR(255)"
		else -> "VARCHAR(255)"
	}

fun getSQLTypeOrDefault(fieldParam: Any, type: Class<*>): String {
	if (fieldParam is FieldName) {
		if (fieldParam.type != "DEFAULT") {
			return fieldParam.type
		}
	}
	return getSQLType(type)
}
