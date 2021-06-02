/* (C)2021 */
package cloud.melion.testdata

import cloud.melion.annotations.FieldName
import cloud.melion.annotations.PrimaryKey
import cloud.melion.interfaces.Table

class NoValidTable : Table

@PrimaryKey("nope")
class NoValidTableWithPrimaryKeys : Table

@PrimaryKey("nope")
class NoValidTableWithWrongFieldNames(
    @FieldName("nope")
    val nope: String,
    val whatIsThis: String
) : Table

annotation class TestAnnotation

@PrimaryKey("nope")
class NoValidTableWithWrongAnnotation(
    @FieldName("nope")
    val nope: String,
    @TestAnnotation
    val whatIsThis: String
) : Table
