import java.time.LocalDate

case class Transaccion(id: String, producto: String, monto: Double, fecha: LocalDate, tipo: String, etiqueta: String, usuario:String)

