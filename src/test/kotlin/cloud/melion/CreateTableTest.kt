/* (C)2021 */
package cloud.melion

import cloud.melion.base.createTable
import cloud.melion.dsl.connect
import cloud.melion.errors.NoConstructorFoundError
import cloud.melion.errors.NoPrimaryKeyFoundError
import cloud.melion.errors.WrongAnnotaionError
import cloud.melion.extensions.save
import cloud.melion.testdata.*
import java.nio.file.Path
import org.junit.jupiter.api.*
import org.junit.jupiter.api.io.TempDir

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CreateTableTest {

  @BeforeAll
  fun connect(@TempDir dir: Path) {
    val path = dir.resolve("test.db")
    connect {
      host { "$path" }
      driver { "sqlite" }
    }
  }

  @AfterAll
  fun disconnect() {
    MySQL.disconnect()
  }

  @Test
  fun `Testing if Table is created`() {
    createTable(ValidTables::class.java)
    assertDoesNotThrow { ValidTables().save() }
  }

  @Test
  fun `Testing if Table with multiple Fields is created`() {
    createTable(ValidTableWithMultipleFields::class.java)
    assertDoesNotThrow { ValidTableWithMultipleFields().save() }
  }

  @Test
  fun `Testing if Table with multiple Keys is created`() {
    createTable(ValidTableWithMultipleKeys::class.java)
    assertDoesNotThrow { ValidTableWithMultipleKeys().save() }
  }

  @Test
  fun `Testing if wrong Table with wrong fieldnames is not created`() {
    assertThrows<WrongAnnotaionError> { createTable(NoValidTableWithWrongFieldNames::class.java) }
  }

  @Test
  fun `Testing if wrong Table with wrong annotations is not created`() {
    assertThrows<WrongAnnotaionError> { createTable(NoValidTableWithWrongAnnotation::class.java) }
  }

  @Test
  fun `Testing if wrong Table with primary keys is not created`() {
    assertThrows<NoConstructorFoundError> { createTable(NoValidTableWithPrimaryKeys::class.java) }
  }

  @Test
  fun `Testing if wrong Table is not created`() {
    assertThrows<NoPrimaryKeyFoundError> { createTable(NoValidTable::class.java) }
  }
}
