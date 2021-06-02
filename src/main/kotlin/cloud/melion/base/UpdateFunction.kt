/* (C)2021 */
package cloud.melion.base

import cloud.melion.MySQL

fun update(sql: String) {
  MySQL.onUpdate(sql)
}
