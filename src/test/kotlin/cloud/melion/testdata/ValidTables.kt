/* (C)2021 */
package cloud.melion.testdata

import cloud.melion.annotations.FieldName
import cloud.melion.annotations.PrimaryKey
import cloud.melion.interfaces.Table
import java.util.*

@PrimaryKey("uuid")
data class ValidTables(
    @FieldName("uuid")
    val uuid: UUID = UUID.randomUUID()
) : Table

@PrimaryKey("uuid")
data class ValidTableWithMultipleFields(
    @FieldName("uuid")
    val uuid: UUID = UUID.randomUUID(),
    @FieldName("name", "Peter")
    val name: String = "Peter"
) : Table

@PrimaryKey("name", "gamegroup")
data class ValidTableWithMultipleKeys(
    @FieldName("name", "Peter")
    val name: String = "Peter",
    @FieldName("gamegroup", "Orks")
    val gamegroup: String = "Orks",
    @FieldName("xp", "0")
    val xp: Int = 0,
) : Table
