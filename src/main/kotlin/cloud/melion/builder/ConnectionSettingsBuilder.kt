/* (C)2021 */
package cloud.melion.builder

import cloud.melion.data.ConnectionSettings

class ConnectionSettingsBuilder {
	private var host: String = ""
	private var database: String = ""
	private var username: String = ""
	private var password: String = ""
	private var driver: String = "mysql"
	private var port: Int = 3306
	private var autoReconnect: Boolean = true

	fun host(lambda: () -> String) {
		this.host = lambda()
	}

	fun database(lambda: () -> String) {
		this.database = lambda()
		println(this.database)
	}

	fun username(lambda: () -> String) {
		this.username = lambda()
	}

	fun password(lambda: () -> String) {
		this.password = lambda()
	}

	fun driver(lambda: () -> String) {
		this.driver = lambda()
	}

	fun port(lambda: () -> Int) {
		this.port = lambda()
	}

	fun autoReconnect(lambda: () -> Boolean) {
		this.autoReconnect = lambda()
	}

	fun build() = ConnectionSettings(host, database, username, password, driver, port, autoReconnect)
}
