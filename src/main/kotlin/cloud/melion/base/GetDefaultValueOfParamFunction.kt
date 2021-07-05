/* (C)2021 */
package cloud.melion.base

import cloud.melion.annotations.FieldName

fun getDefaultValueOfParam(fieldParam: Any): String {
	if (fieldParam is FieldName) {
		if (fieldParam.defaultValue != "NULL") {
			return fieldParam.defaultValue
		} else {
			throw NullPointerException("DefaultValue is null")
		}
	}
	throw NullPointerException("FieldParam is null")
}
