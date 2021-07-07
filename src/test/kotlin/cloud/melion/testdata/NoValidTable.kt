/* (C)2021 */
package cloud.melion.testdata

import cloud.melion.annotations.FieldName
import cloud.melion.annotations.PrimaryKey
import cloud.melion.interfaces.ITable
import java.util.*

class NoValidTable : ITable

@PrimaryKey("nope")
class NoValidTableWithPrimaryKeys : ITable

@PrimaryKey("uuid")
data class NoValidTableWithoutDefaultValue(
	@FieldName("uuid")
	var uuid: UUID
) : ITable

@PrimaryKey("nope")
class NoValidTableWithWrongFieldNames(
	@FieldName("nope")
	val nope: String,
	val whatIsThis: String
) : ITable

annotation class TestAnnotation

@PrimaryKey("nope")
class NoValidTableWithWrongAnnotation(
	@FieldName("nope")
	val nope: String,
	@TestAnnotation val whatIsThis: String
) : ITable
