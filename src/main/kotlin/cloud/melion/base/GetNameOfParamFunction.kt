/* (C)2021 */
package cloud.melion.base

import cloud.melion.annotations.FieldName

fun getNameOfParam(fieldParam: Any): String {
  if (fieldParam is FieldName) {
    return fieldParam.name
  }
  println("${fieldParam::class.java.simpleName} is not a FieldName")
  return "None"
}
