package Transaccion


object Main {
  def main(args: Array[String]): Unit = {
    val t1 = Transaccion(1, 101, 500.0, "DEPOSITO")
    val t2 = Transaccion(2, 102, 15000.0, "RETIRO")
    val t3 = Transaccion(3, 103, 8000.0, "DEPOSITO")

    val transacciones = List(t1, t2, t3)

    val comisionesSeguras = t1.procesarLote(transacciones, t1.esTransaccionSegura, t1.comisionEstandar)

    println(comisionesSeguras)
  }
}

