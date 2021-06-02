/* (C)2021 */
package cloud.melion.errors

class NoConstructorFoundError(tableName: String) : Error("No Constructor found for $tableName")
