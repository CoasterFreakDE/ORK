/* (C)2021 */
package cloud.melion.annotations

@Target(
	AnnotationTarget.TYPE_PARAMETER,
	AnnotationTarget.VALUE_PARAMETER,
	AnnotationTarget.CONSTRUCTOR,
	AnnotationTarget.FIELD
)
annotation class FieldName(
	val name: String, val defaultValue: String = "NULL", val type: String = "DEFAULT"
)
