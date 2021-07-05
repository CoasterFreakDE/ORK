/* (C)2021 */
package cloud.melion.errors

class WrongAnnotaionError(tableName: String) : Error("Wrong Annotations found in $tableName")
