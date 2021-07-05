/* (C)2021 */
package cloud.melion

import cloud.melion.dsl.connect
import org.junit.jupiter.api.io.TempDir
import java.nio.file.Path
import kotlin.test.Test

class ConnectToDatabaseTest {

	@Test
	fun `Testing if Database connection is valid`(@TempDir dir: Path) {
		val path = dir.resolve("test.db")
		connect {
			host { "$path" }
			driver { "sqlite" }
		}

		assert(MySQL.isConnected())
		MySQL.disconnect()
		assert(MySQL.isConnected().not())
	}
}
