import java.time.LocalDate
import scala.collection.mutable
import scala.reflect.internal.util.TriState.True

object BancoData {
  val productos = List(
    Producto("P001", "Tarjeta Crédito Infinite", 0.36),
    Producto("P002", "Tarjeta Crédito Clásica", 0.45),
    Producto("P003", "Tarjeta Crédito Oro", 0.38),
    Producto("P004", "Tarjeta Crédito Platinum", 0.29),
    Producto("P005", "Tarjeta Crédito Black", 0.44),
    Producto("P006", "Tarjeta Crédito LifeMiles", 0.41),
    Producto("P007", "Tarjeta Crédito Signature", 0.44)
  )

  val usuarios = List(
    Usuario("U001", "Carlos Pérez", "Bogotá", List("P001", "P002", "P007")),
    Usuario("U002", "Ana López", "Medellín", List("P001", "P004", "P003", "P006")),
    Usuario("U003", "Juan Lozano", "Cali", List("P001", "P004", "P003", "P006")),
    Usuario("U004", "Enrique Prieto", "Bogotá", List("P002", "P004", "P003", "P005")),
    Usuario("U005", "Manuel Medina", "Cartagena", List("P007", "P005")),
    Usuario("U006", "Maria Gomez", "Pereira", List("P006", "P003")),
    Usuario("U007", "Claudia Gonzalez", "Bogotá", List("P001", "P004", "P003", "P006", "P002")),
    Usuario("U008", "Nicolas Rodriguez", "Medellín", List("P001", "P004", "P003", "P006")),
    Usuario("U009", "Estefania Lemus", "Cali", List()),
    Usuario("U010", "Sandra Vasquez", "Bogotá", List("P006"))
  )

  val transacciones = List(
    Transaccion("T1", "P001", 2500.0, LocalDate.now(), "CREDITO", "Nomina", "U009"),
    Transaccion("T2", "P001", 120.50, LocalDate.now(), "DEBITO", "Cajero", "U001"),
    Transaccion("T3", "P002", 450.0, LocalDate.now(), "DEBITO", "Supermercado", "U003"),
    Transaccion("T4", "P001", 50.0, LocalDate.now(), "DEBITO", "Transporte", "U005"),
    Transaccion("T5", "P003", 1500.0, LocalDate.now(), "CREDITO", "Transferencia", "U006"),
    Transaccion("T6", "P001", 3000.0, LocalDate.now(), "CREDITO", "Nomina", "U001"),
    Transaccion("T7", "P001", 800.0, LocalDate.now(), "DEBITO", "Alquiler", "U007"),
    Transaccion("T8", "P003", -100.0, LocalDate.now(), "DEBITO", "ERROR_SISTEMA", "U002"),
    Transaccion("T9", "P005", 50.0, LocalDate.now(), "DEBITO", "Cajero", "U008"),
    Transaccion("T10", "P001", 20.0, LocalDate.now(), "DEBITO", "Cajero", "U002"),
    Transaccion("T11", "P005", 500.0, LocalDate.now(), "DEBITO", "Cuota_Mes", "U001"),
    Transaccion("T12", "P003", 500.0, LocalDate.now(), "DEBITO", "Cuota_Mes", "U004"),
    Transaccion("T13", "P006", 2000.0, LocalDate.now(), "CREDITO", "Abono_Extra", "U009"),
    Transaccion("T14", "P003", 0.0, LocalDate.now(), "CREDITO", "", "U008"),
    Transaccion("T15", "P006", 500.0, LocalDate.now(), "DEBITO", "Cuota_Mes", "U001"),
    Transaccion("T16", "P002", 6000.0, LocalDate.now(), "DEBITO", "ALTO_RIESGO_LUJO", "U008"),
    Transaccion("T17", "P007", 150.0, LocalDate.now(), "DEBITO", "Restaurante", "U008"),
    Transaccion("T18", "P003", 1200.0, LocalDate.now(), "DEBITO", "Cuota_Mes", "U007"),
    Transaccion("T19", "P007", 45.0, LocalDate.now(), "DEBITO", "Suscripcion", "U005"),
    Transaccion("T20", "P007", 200.0, LocalDate.now(), "CREDITO", "Reembolso", "U004"),
    Transaccion("T21", "P001", 1100.0, LocalDate.now(), "CREDITO", "Nomina", "U008"),
    Transaccion("T22", "P001", 300.0, LocalDate.now(), "DEBITO", "Cajero", "U003"),
    Transaccion("T23", "P002", 15.0, LocalDate.now(), "DEBITO", "Cafe", "U005"),
    Transaccion("T24", "P001", 200.0, LocalDate.now(), "DEBITO", "Servicios", "U003"),
    Transaccion("T25", "P001", 400.0, LocalDate.now(), "CREDITO", "Transferencia", "U003"),
    Transaccion("T26", "P005", 500.0, LocalDate.now(), "DEBITO", "Cuota_Mes", "U004"),
    Transaccion("T27", "P006", 850.0, LocalDate.now(), "DEBITO", "Viajes", "U006"),
    Transaccion("T28", "P004", 12.0, LocalDate.now(), "DEBITO", "Transporte", "U002"),
    Transaccion("T29", "P005", 1100.0, LocalDate.now(), "CREDITO", "Nomina", "U008"),
    Transaccion("T30", "P006", 90.0, LocalDate.now(), "DEBITO", "Farmacia", "U008"),
    Transaccion("T31", "P004", 500.0, LocalDate.now(), "CREDITO", "Venta_Garage", "U002"),
    Transaccion("T32", "P013", 500.0, LocalDate.now(), "DEBITO", "Cuota_Mes", "U003"),
    Transaccion("T33", "P001", 200.0, LocalDate.now(), "DEBITO", "Cajero", "U004"),
    Transaccion("T34", "P002", 1200.0, LocalDate.now(), "DEBITO", "Electronica", "U009"),
    Transaccion("T35", "P001", 100.0, LocalDate.now(), "DEBITO", "Restaurante", "U001"),
    Transaccion("T36", "P003", 500.0, LocalDate.now(), "DEBITO", "Cuota_Mes", "U007"),
    Transaccion("T37", "P002", 30.0, LocalDate.now(), "DEBITO", "Cine", "U006"),
    Transaccion("T38", "P007", 1500.0, LocalDate.now(), "CREDITO", "Nomina", "U011"),
    Transaccion("T39", "P007", 250.0, LocalDate.now(), "DEBITO", "Gimnasio", "U008"),
    Transaccion("T40", "P006", 2000.0, LocalDate.now(), "CREDITO", "Abono_Capital", "U007"),
    Transaccion("T41", "P001", 45.0, LocalDate.now(), "DEBITO", "Transporte", "U004"),
    Transaccion("T42", "P004", 3000.0, LocalDate.now(), "DEBITO", "ALTO_RIESGO_JOYAS", "U008"),
    Transaccion("T43", "P001", 100.0, LocalDate.now(), "DEBITO", "Cajero", "U003"),
    Transaccion("T44", "P004", 500.0, LocalDate.now(), "DEBITO", "Cuota_Mes", "U005"),
    Transaccion("T45", "P002", 150.0, LocalDate.now(), "DEBITO", "Supermercado", "U002"),
    Transaccion("T46", "P001", 1100.0, LocalDate.now(), "CREDITO", "Nomina", "U004"),
    Transaccion("T47", "P005", 10.0, LocalDate.now(), "DEBITO", "App_Store", "U002"),
    Transaccion("T48", "P003", 500.0, LocalDate.now(), "DEBITO", "Cuota_Mes", "U009"),
    Transaccion("T49", "P005", 75.0, LocalDate.now(), "DEBITO", "Restaurante", "U003"),
    Transaccion("T50", "P999", 4000.0, LocalDate.now(), "CREDITO", "ERROR_PRODUCTO", "U012"),
    Transaccion("T51", "P005", 75.0, LocalDate.now(), "DEBITO", "Restaurante", "U003"),
    Transaccion("T52", "P005", 75.0, LocalDate.now(), "DEBITO", "Restaurante", "U003")
  )


  def limpiarDatos(transacciones: List[Transaccion]): List[Transaccion] = {
    val t = transacciones.filter(_.monto>0).distinct.filter(t => usuarios.exists(_.id==t.usuario))
    t
  }

  def normalizacionDatos(transacciones: List[Transaccion]): List[Transaccion] = {
    val t = transacciones.map(trans =>{
      trans match{
        case Transaccion(_, _, m, _, "DEBITO", _, _) if m > 3000 => trans.copy(tipo = "ALTO_RIESGO")
        case Transaccion(_, _, m, _, "CREDITO", _, _) => trans.copy(monto = (m * productos.find(_.idProducto == trans.producto).map(_.tasaInteres).getOrElse(0.0)))
        case _ => trans
      }
      trans.copy(etiqueta = trans.etiqueta.toUpperCase())
    })
    t
  }



  def calculoMovProductos(transacciones: List[Transaccion],productos: List[Producto]): Map[String,Double] ={
    val transLimpias = limpiarDatos(transacciones)
    val transNormalizadas = normalizacionDatos(transLimpias)

    val productoSaldo: mutable.Map[String, Double] = mutable.Map()
    val llenarProductos = productos.map( prod =>{
      productoSaldo(prod.idProducto) = transNormalizadas.filter(prod.idProducto==_.producto).map(_.monto).sum
    })
    productoSaldo.toMap
  }

  def calculoUtilidades(transacciones: List[Transaccion]): Double = {
    val transLimpias = limpiarDatos(transacciones)
    val transNormalizadas = normalizacionDatos(transLimpias)
    transNormalizadas.filter(credito => credito.tipo == "CREDITO" || credito.tipo == "ALTO_RIESGO").map(_.monto).sum
  }

  def calculoGastoClientes(transacciones: List[Transaccion],clientes: List[Usuario]): Map[String,Double]={
    val transLimpias = limpiarDatos(transacciones)
    val transNormalizadas = normalizacionDatos(transLimpias)
    val clientGasto: mutable.Map[String, Double] = mutable.Map()

    val gastoClientes = clientes.map(cliente =>{
      clientGasto(cliente.id)  = transNormalizadas.filter((cliente.id == _.usuario)).map(_.monto).sum
    })
    clientGasto.toMap
  }

  private def saldoReporte(transacciones: List[Transaccion], cliente: String): Double = transacciones.filter( t => {t.usuario == cliente && t.tipo =="ALTO_RIESGO"}).map(_.monto).sum


  def reporte(transacciones: List[Transaccion],clientes: List[Usuario]):List[Usuario]= {
    val transLimpias = limpiarDatos(transacciones)
    val transNormalizadas = normalizacionDatos(transLimpias)

    val usuariosCon3 = clientes.filter(cliente =>{
    transNormalizadas.filter(trans =>{
        cliente.id == trans.usuario && trans.tipo == "ALTO_RIESGO"
      }).length >=3
    })

    usuariosCon3.foreach(u =>{
      ReporteCliente(u.nombre,saldoReporte(transNormalizadas,u.id),true)
    })

    usuariosCon3
  }


}


