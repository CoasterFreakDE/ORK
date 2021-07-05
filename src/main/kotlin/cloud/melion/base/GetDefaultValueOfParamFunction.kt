/* (C)2021 */
package cloud.melion.base

import cloud.melion.annotations.FieldName

fun getDefaultValueOfParam(fieldParam: Any) =
	if (fieldParam is FieldName) {
		if (fieldParam.defaultValue != "NULL") {
			fieldParam.defaultValue
		} else {
			throw NullPointerException("DefaultValue is null")
		}
	} else
		throw NullPointerException("FieldParam is null")

