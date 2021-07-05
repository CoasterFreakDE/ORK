/* (C)2021 */
package cloud.melion.builder

import cloud.melion.annotations.APIObject
import cloud.melion.data.ConnectionSettings

class ConnectionSettingsBuilder {
	private var host: String = ""
	private var database: String = ""
	private var username: String = ""
	private var password: String = ""
	private var driver: String = "mysql"
	private var port: Int = 3306
	private var autoReconnect: Boolean = true

	@APIObject
	fun host(lambda: () -> String) {
		this.host = lambda()
	}

	@APIObject
	fun database(lambda: () -> String) {
		this.database = lambda()
		println(this.database)
	}

	@APIObject
	fun username(lambda: () -> String) {
		this.username = lambda()
	}

	@APIObject
	fun password(lambda: () -> String) {
		this.password = lambda()
	}

	@APIObject
	fun driver(lambda: () -> String) {
		this.driver = lambda()
	}

	@APIObject
	fun port(lambda: () -> Int) {
		this.port = lambda()
	}

	@APIObject
	fun autoReconnect(lambda: () -> Boolean) {
		this.autoReconnect = lambda()
	}

	@APIObject
	fun build() = ConnectionSettings(host, database, username, password, driver, port, autoReconnect)
}
