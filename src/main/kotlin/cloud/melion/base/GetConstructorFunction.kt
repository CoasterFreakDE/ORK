/* (C)2021 */
package cloud.melion.base

fun getConstructor(table: Class<*>, primaryKeys: Array<out String>) =
	table.constructors.find { constructor ->
		primaryKeys.all {
			it in
					constructor.parameterAnnotations.map { parameter ->
						if (parameter.isNotEmpty<Annotation?>()) getNameOfParam(parameter[0]) else "No"
					}
		}
	}
