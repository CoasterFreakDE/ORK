/* (C)2021 */
package cloud.melion.errors

class NoPrimaryKeyFoundError(tableName: String) : Error("No PrimaryKey found for $tableName")
