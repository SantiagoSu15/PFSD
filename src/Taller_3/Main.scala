package Taller_3

object Main {
  def main(args: Array[String])  = {
    val verificacion =  Verificacion()
    val mapaClientes  = verificacion.construirMapaClientes(Data.clientes)
    val mapaHistorial = verificacion.construirMapaHistorial(Data.historialPago)
    println(s"Clientes : ${mapaClientes.keys.mkString(", ")}")
    println(s"historial: ${mapaHistorial.keys.mkString(", ")}")

    val resultados = Data.solicitudes.map { sol =>
      verificacion.evaluarSolicitud(sol, mapaClientes, Data.historialPago, Data.movimientos)
    }
    resultados.foreach { r =>
      val cliente = mapaClientes.get(r.clienteId).map(_.nombre).getOrElse("Desconocido")
      println(s"\n  Cliente : ${r.clienteId} – $cliente")
      println(s"  Puntaje : ${r.puntaje.formatted("%.2f")} / 100")
      println(s"  Riesgo  : ${r.nivelRiesgo}")
    }
  }
}
