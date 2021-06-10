/* (C)2021 */
package cloud.melion

import cloud.melion.base.createTable
import cloud.melion.dsl.connect
import cloud.melion.extensions.save
import cloud.melion.extensions.search
import cloud.melion.extensions.select
import cloud.melion.testdata.*
import java.nio.file.Path
import java.util.*
import kotlin.test.assertEquals
import org.junit.jupiter.api.*
import org.junit.jupiter.api.io.TempDir

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SearchTest {

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
  fun `Testing SimpleSearch by PrimaryKey`() {
    createTable(ValidTables::class.java)
    val uuid = UUID.randomUUID()
    assertDoesNotThrow { ValidTables(uuid).save() }

    val result = search<ValidTables>(mapOf("uuid" to "$uuid"))

    if (result.isPresent) {
      val tables = result.get()
      assertEquals(1, tables.size, "Too many/less tables")
      for (table in tables) {
        assertEquals(uuid, table.uuid)
      }
    }
  }

  @Test
  fun `Testing SimpleSearch with more values`() {
    createTable(ValidTableWithMultipleKeys::class.java)
    assertDoesNotThrow { ValidTableWithMultipleKeys("Peter", "Hello", 95).save() }
    assertDoesNotThrow { ValidTableWithMultipleKeys("Gunther", "Hello", 18).save() }
    assertDoesNotThrow { ValidTableWithMultipleKeys("Wayne", "Ork", 0).save() }
    assertDoesNotThrow { ValidTableWithMultipleKeys("Johnson", "Dto", 286).save() }
    assertDoesNotThrow { ValidTableWithMultipleKeys("Isolde", "Melion", 143).save() }

    val result =
        search<ValidTableWithMultipleKeys>(mapOf("name" to "Isolde", "gamegroup" to "Melion"))
    if (result.isPresent) {
      val tables = result.get()
      assertEquals(1, tables.size, "Too many/less tables")
      for (table in tables) {
        assertEquals("Isolde", table.name)
        assertEquals("Melion", table.gamegroup)
        assertEquals(143, table.xp)
      }
    }

    val result2 = search<ValidTableWithMultipleKeys>(mapOf("gamegroup" to "Hello"))
    if (result2.isPresent) {
      val tables = result2.get()
      assertEquals(2, tables.size, "Too many/less tables")
      for (table in tables) {
        assertEquals("Hello", table.gamegroup)
      }
    }

    select<ValidTables> { uuid = UUID.randomUUID() }
  }
}
