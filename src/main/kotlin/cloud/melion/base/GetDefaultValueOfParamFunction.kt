/* (C)2021 */
package cloud.melion.base

import cloud.melion.annotations.FieldName

fun getDefaultValueOfParam(fieldParam: Any): String {
	if (fieldParam is FieldName) {
		if (fieldParam.defaultValue == "NULL") {
			throw NullPointerException("DefaultValue is null")
		} else {
			return fieldParam.defaultValue
		}
	}
	throw NullPointerException("FieldParam is null")
}
