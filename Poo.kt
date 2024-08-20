// Análisis de clases
class Cargo(
    val nombre: String,
    val nivelJerarquico: Int,
    val dependencia: String
)

open class Persona(
    val nombreCompleto: String,
    val documentoIdentidad: String,
    val sexo: String,
    var correoElectronico: String,
    var direccionCorrespondencia: String,
    var telefono: String
)

class Empleado(
    nombreCompleto: String,
    documentoIdentidad: String,
    sexo: String,
    correoElectronico: String,
    direccionCorrespondencia: String,
    telefono: String,
    val salario: Double,
    val anioIngreso: Int,
    val cargo: Cargo,
    val subordinados: MutableList<Empleado> = mutableListOf()
) : Persona(nombreCompleto, documentoIdentidad, sexo, correoElectronico, direccionCorrespondencia, telefono)

class Cliente(
    nombreCompleto: String,
    documentoIdentidad: String,
    sexo: String,
    correoElectronico: String,
    direccionCorrespondencia: String,
    telefono: String
) : Persona(nombreCompleto, documentoIdentidad, sexo, correoElectronico, direccionCorrespondencia, telefono)

val empleados: MutableList<Empleado> = mutableListOf()
val clientes: MutableList<Cliente> = mutableListOf()

// Métodos CRUD Empleado
fun addEmpleado(empleado: Empleado) {
    val existeEmpleado = empleados.any { it.documentoIdentidad == empleado.documentoIdentidad }
    if (!existeEmpleado) {
        empleados.add(empleado)
        println("Se agregó el empleado")
    } else {
        println("El empleado ya se encuentra registrado")
    }
}

fun lookUpEmpleado(documentoIdentidad: String): Empleado? {
    return empleados.find { it.documentoIdentidad == documentoIdentidad }
}

fun updateEmpleado(documentoIdentidad: String, empleadoActualizado: Empleado): Boolean {
    val index = empleados.indexOfFirst { it.documentoIdentidad == documentoIdentidad }
    return if (index != -1) {
        empleados[index] = empleadoActualizado
        true
    } else {
        false
    }
}

fun deleteEmpleado(documentoIdentidad: String) {
   empleados.removeIf { it.documentoIdentidad == documentoIdentidad }
}

// Métodos CRUD Cliente
fun addCliente(cliente: Cliente) {
    val existeCliente = clientes.any { it.documentoIdentidad == cliente.documentoIdentidad }
    if (!existeCliente) {
        clientes.add(cliente)
        println("Se agregó el cliente")
    } else {
        println("El cliente ya se encuentra registrado")
    }
}

fun lookUpCliente(documentoIdentidad: String): Cliente? {
    return clientes.find { it.documentoIdentidad == documentoIdentidad }
}

fun updateCliente(documentoIdentidad: String, clienteActualizado: Cliente): Boolean {
    val index = clientes.indexOfFirst { it.documentoIdentidad == documentoIdentidad }
    return if (index != -1) {
        clientes[index] = clienteActualizado
        true
    } else {
        false
    }
}

fun deleteCliente(documentoIdentidad: String) {
    clientes.removeIf { it.documentoIdentidad == documentoIdentidad }
}

// Obtener el valor de la nómina de toda la empresa y la nómina por cada dependencia
fun obtenerNominaTotalEmpresa(): Double {
    return empleados.sumOf { it.salario }
}

// Nómina por dependencia
fun obtenerNominaPorDependencia(): Map<String, Double> {
    return empleados.groupBy { it.cargo.dependencia }.mapValues { it.value.sumOf { empleado -> empleado.salario } }
}

// Obtener el porcentaje de clientes según su sexo
fun obtenerPorcentajeClientesPorSexo() {
    val totalClientes = clientes.size
    if (totalClientes > 0) {
        val cantidadMasculinos = clientes.count { it.sexo == "Masculino" }
        val cantidadFemeninos = clientes.count { it.sexo == "Femenino" }
        val porcentajeMasculinos = (cantidadMasculinos.toDouble() / totalClientes) * 100
        val porcentajeFemeninos = (cantidadFemeninos.toDouble() / totalClientes) * 100
        println("Masculino: $porcentajeMasculinos%")
        println("Femenino: $porcentajeFemeninos%")
    } else {
        println("No hay clientes en la lista.")
    }
}

// Obtener la cantidad de empleados según el nombre del cargo
fun obtenerCantidadEmpleadosPorCargo(): Map<String, Int> {
    return empleados.groupBy { it.cargo.nombre }.mapValues { it.value.size }
}

// Obtener el empleado que lleva más tiempo en la empresa e indicar a qué dependencia pertenece
fun obtenerEmpleadoConTiempo(): Map<String, Any>? {
    val empleadoMasAntiguo = empleados.minByOrNull { it.anioIngreso }
    return empleadoMasAntiguo?.run {
        mapOf(
            "Dependencia" to cargo.dependencia,
            "Año de Ingreso" to anioIngreso
        )
    }
}