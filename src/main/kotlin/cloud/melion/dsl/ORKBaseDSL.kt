/* (C)2021 */
package cloud.melion.dsl

import cloud.melion.MySQL
import cloud.melion.builder.ConnectionSettingsBuilder

fun connect(settings: ConnectionSettingsBuilder.() -> Unit) {
  MySQL.connect(ConnectionSettingsBuilder().apply(settings).build())
}
