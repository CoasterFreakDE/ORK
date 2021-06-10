/* (C)2021 */
package cloud.melion.testdata

import cloud.melion.annotations.FieldName
import cloud.melion.annotations.PrimaryKey
import cloud.melion.interfaces.ITable
import java.util.*

@PrimaryKey("uuid")
data class ValidTables(
    @FieldName("uuid")
    var uuid: UUID = UUID.randomUUID()
) : ITable

@PrimaryKey("uuid")
data class ValidTableWithMultipleFields(
    @FieldName("uuid")
    val uuid: UUID = UUID.randomUUID(),
    @FieldName("name", "Peter")
    val name: String = "Peter"
) : ITable

@PrimaryKey("name", "gamegroup")
data class ValidTableWithMultipleKeys(
    @FieldName("name", "Peter")
    val name: String = "Peter",
    @FieldName("gamegroup", "Orks")
    val gamegroup: String = "Orks",
    @FieldName("xp", "0")
    val xp: Int = 0,
) : ITable
