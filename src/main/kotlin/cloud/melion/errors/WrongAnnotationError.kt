/* (C)2021 */
package cloud.melion.errors

class WrongAnnotationError(tableName: String) : Error("Wrong Annotations found in $tableName")
