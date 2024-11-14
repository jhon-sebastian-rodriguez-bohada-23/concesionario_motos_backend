package com.example.concesionario_motos_backend
import kotlin.system.exitProcess

fun main() {
    var opcion: Int

    // Lista de productos de motos con precio
    val motos = listOf(
        Producto("Yamaha", 45000000.0),
        Producto("Kawasaki", 80000000.0),
        Producto("Suzuki", 77000000.0)
    )

    // Lista de modelos de motos (sin precio)
    val modelos = listOf(
        Producto("MT-09"),
        Producto("Ninja ZX-10R"),
        Producto("GSX-R1000R")
    )

    // Lista de propósitos de motos (sin precio)
    val propositos = listOf(
        Producto("Ciudad"),
        Producto("Deportiva"),
        Producto("Off-road")
    )

    // Lista de productos de cascos
    val cascos = listOf(
        Producto("LS2 FF324 Valiant", 1400000.0),
        Producto("HJC RPHA 11 Pro", 1800000.0),
        Producto("Shoei RF-1400", 2200000.0)
    )

    // Lista de productos de guantes
    val guantes = listOf(
        Producto("Scorpion EXO-TECH", 180000.0),
        Producto("Alpinestars GP Pro R3", 450000.0),
        Producto("REVIT! Sand 4", 300000.0)
    )

    // Lista de productos de botas
    val botas = listOf(
        Producto("Dainese Torque 3 Out", 1800000.0),
        Producto("Sidi Crossfire 3 SRS", 2400000.0),
        Producto("Fox Racing Instinct", 2000000.0)
    )

    // Lista de productos de repuestos
    val repuestos = listOf(
        Producto("Filtro de Aire", 30000.0),
        Producto("Batería de Moto", 100000.0),
        Producto("Pastillas de Freno", 40000.0)
    )

    // Lista de productos de seguros
    val seguros = listOf(
        Producto("Seguro SOAT", 200000.0),
        Producto("Seguro de vida", 150000.0),
        Producto("Seguro todo riesgo", 800000.0)
    )

    // Carro de compras
    val carroDeCompras = mutableListOf<Producto>()
    var enCarrito = false

    do {
        println("Menu Principal:")
        println("1. Motos")
        println("2. Boutique")
        println("3. Repuestos")
        println("4. Seguros")
        println("5. Facturar")
        println("0. Salir")
        print("Seleccione una opción: ")

        opcion = readLine()?.toIntOrNull() ?: -1

        when (opcion) {
            1 -> {
                // Submenú Motos
                var opcionMotos: Int
                do {
                    println("Submenú Motos:")
                    println("1. Marca")
                    println("2. Propósito")
                    println("3. Modelo")
                    println("0. Volver al menú principal")
                    print("Seleccione una opción: ")

                    opcionMotos = readLine()?.toIntOrNull() ?: -1

                    when (opcionMotos) {
                        1 -> mostrarProductos(motos, carroDeCompras, "Marca", enCarrito) // Mostrar productos de motos
                        2 -> mostrarProductos(propositos, carroDeCompras, "Propósito", enCarrito) // Mostrar productos de propósito
                        3 -> mostrarProductos(modelos, carroDeCompras, "Modelo", enCarrito) // Mostrar modelos de motos
                        0 -> println("Volviendo al menú principal...")
                        else -> println("Opción inválida, por favor intente nuevamente.")
                    }
                } while (opcionMotos != 0)
            }
            2 -> {
                // Submenú Boutique
                var opcionBoutique: Int
                do {
                    println("Submenú Boutique:")
                    println("1. Cascos")
                    println("2. Guantes")
                    println("3. Botas")
                    println("0. Volver al menú principal")
                    print("Seleccione una opción: ")

                    opcionBoutique = readLine()?.toIntOrNull() ?: -1

                    when (opcionBoutique) {
                        1 -> mostrarProductos(cascos, carroDeCompras, "Cascos", enCarrito)
                        2 -> mostrarProductos(guantes, carroDeCompras, "Guantes", enCarrito)
                        3 -> mostrarProductos(botas, carroDeCompras, "Botas", enCarrito)
                        0 -> println("Volviendo al menú principal...")
                        else -> println("Opción inválida, por favor intente nuevamente.")
                    }
                } while (opcionBoutique != 0)
            }
            3 -> mostrarProductos(repuestos, carroDeCompras, "Repuestos", enCarrito)
            4 -> mostrarProductos(seguros, carroDeCompras, "Seguros", enCarrito)
            5 -> facturar(carroDeCompras)
            0 -> println("Saliendo del sistema...")
            else -> println("Opción inválida, por favor intente nuevamente.")
        }
    } while (opcion != 0)
}

fun mostrarProductos(productos: List<Producto>, carroDeCompras: MutableList<Producto>, tipoProducto: String, enCarrito: Boolean) {
    println("Productos de $tipoProducto:")
    productos.forEachIndexed { index, producto ->
        if (tipoProducto == "Modelo" || tipoProducto == "Propósito") {
            // Muestra solo el nombre del producto sin el precio
            println("${index + 1}. ${producto.nombre}")
        } else {
            // Muestra el nombre y el precio del producto
            println("${index + 1}. ${producto.nombre} - \$${"%.2f".format(producto.precio)}")
        }
    }
    println("0. Volver al menú anterior")

    print("Seleccione una opción: ")
    val seleccion = readLine()?.toIntOrNull() ?: -1
    if (seleccion in 1..productos.size) {
        val productoSeleccionado = productos[seleccion - 1]
        carroDeCompras.add(productoSeleccionado)
        println("El producto ${productoSeleccionado.nombre} ha sido agregado al carrito.")

        var opcionNavegacion: Int
        do {
            println("¿Qué desea hacer ahora?")
            if (enCarrito) {
                println("1. Ir a facturación")
            } else {
                println("1. Ir al carrito de compras")
            }
            println("2. Volver al menú anterior")
            print("Seleccione una opción: ")
            opcionNavegacion = readLine()?.toIntOrNull() ?: -1

            when (opcionNavegacion) {
                1 -> {
                    if (enCarrito) {
                        facturar(carroDeCompras)
                    } else {
                        mostrarCarrito(carroDeCompras)
                    }
                }
                2 -> {
                    println("Volviendo al menú anterior...")
                    return
                }
                else -> println("Opción inválida, por favor intente nuevamente.")
            }
        } while (opcionNavegacion != 2)
    } else if (seleccion != 0) {
        println("Opción inválida, por favor intente nuevamente.")
    }
}

fun mostrarCarrito(carroDeCompras: MutableList<Producto>) {
    println("Contenido de su carrito de compras:")
    var total = 0.0
    carroDeCompras.forEach { producto ->
        println("${producto.nombre} - \$${"%.2f".format(producto.precio)}")
        total += producto.precio
    }
    println("Total a pagar: \$${"%.2f".format(total)}")
    println("¿Qué desea hacer ahora?")
    println("1. Ir a facturación")
    println("2. Volver al menú anterior")
    print("Seleccione una opción: ")
    val opcion = readLine()?.toIntOrNull() ?: -1

    when (opcion) {
        1 -> facturar(carroDeCompras)
        2 -> println("Volviendo al menú anterior...")
        else -> println("Opción inválida, por favor intente nuevamente.")
    }
}

fun facturar(carroDeCompras: MutableList<Producto>) {
    if (carroDeCompras.isEmpty()) {
        println("Su carrito está vacío. No hay nada para facturar.")
        return
    }

    println("Facturación:")
    var totalFactura = 0.0
    carroDeCompras.forEach { producto ->
        println("${producto.nombre} - \$${"%.2f".format(producto.precio)}")
        totalFactura += producto.precio
    }
    println("Total a pagar: \$${"%.2f".format(totalFactura)}")

    var metodoPago: Int
    do {
        println("Seleccione el método de pago:")
        println("1. Tarjeta débito")
        println("2. Tarjeta crédito")
        println("3. Pago ON-line PSE")
        println("0. Volver al menú principal")
        print("Seleccione una opción: ")
        metodoPago = readLine()?.toIntOrNull() ?: -1

        when (metodoPago) {
            1 -> {
                println("Has seleccionado el método de pago: Tarjeta débito")
                println("Procesando pago... ¡Gracias por su compra!")
                carroDeCompras.clear()
                exitProcess(0)  // Cierra el programa
            }
            2 -> {
                println("Has seleccionado el método de pago: Tarjeta crédito")
                println("Procesando pago... ¡Gracias por su compra!")
                carroDeCompras.clear()
                exitProcess(0)  // Cierra el programa
            }
            3 -> {
                println("Has seleccionado el método de pago: Pago ON-line PSE")
                println("Procesando pago... ¡Gracias por su compra!")
                carroDeCompras.clear()
                exitProcess(0)  // Cierra el programa
            }
            0 -> println("Volviendo al menú principal...")
            else -> println("Opción inválida, por favor intente nuevamente.")
        }
    } while (metodoPago != 0)
}

data class Producto(val nombre: String, val precio: Double = 0.0)

