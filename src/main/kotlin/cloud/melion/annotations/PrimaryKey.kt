/* (C)2021 */
package cloud.melion.annotations

@Target(AnnotationTarget.CLASS)
annotation class PrimaryKey(vararg val keys: String)
