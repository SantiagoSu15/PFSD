package Taller_3

import scala.annotation.tailrec

case class Verificacion() {

  @tailrec
  final def calcularDeudaTotal(movimientos: List[MovimientoFinanciero], acum: Double = 0.0): Double =
    movimientos match {
      case Nil       => acum
      case x :: xs   => calcularDeudaTotal(xs, acum + x.monto)
    }

  @tailrec
  final def contarPagosAtrasados(historial: List[HistorialPagos], acum: Int = 0): Int =
    historial match {
      case Nil     => acum
      case x :: xs => contarPagosAtrasados(xs, if (x.diasAtraso > 0) acum + 1 else acum)
    }

  @tailrec
  final def calcularPromedioIngresos(ingresos: List[Double], suma: Double = 0.0, n: Int = 0): Double =
    ingresos match {
      case Nil     => if (n == 0) 0.0 else suma / n
      case x :: xs => calcularPromedioIngresos(xs, suma + x, n + 1)
    }

  @tailrec
  final def mesesConsecutivosMora(historial: List[HistorialPagos], contador: Int = 0): Int =
    historial match {
      case Nil     => contador
      case x :: xs =>
        if (x.diasAtraso > 0) mesesConsecutivosMora(xs, contador + 1)
        else                   contador
    }

  def validarClienteExiste(id: String, mapaClientes: Map[String, Cliente]): Either[String, Cliente] =
    mapaClientes.get(id).toRight(s"Cliente no encontrado")

  def validarHistorialSuficiente(id: String, historial: List[HistorialPagos]): Either[String, List[HistorialPagos]] = {
    val h = historial.filter(_.clienteId == id)
    if (h.isEmpty) Left(s"Cliente no tiene historial ")
    else Right(h)
  }

  def validarIngresosSuficientes(cliente: Cliente): Either[String, Double] = {
    val promedio = calcularPromedioIngresos(cliente.ingresosMensuales)
    if (promedio <= 0) Left(s"Cliente no tiene ingresos ")
    else Right(promedio)
  }

  def validarMontoSolicitud(solicitud: SolicitudCredito, promedioIngreso: Double): Either[String, SolicitudCredito] = {
    val cuotaEstimada = solicitud.montoSolicitado / solicitud.plazoMeses
    if (cuotaEstimada > promedioIngreso * 0.40)
      Left(s"Cuota supera el 40% del ingreso ")
    else Right(solicitud)
  }

  def validarSolicitud(solicitud: SolicitudCredito, mapaClientes: Map[String, Cliente], historialTotal: List[HistorialPagos]): Either[String, (Cliente, List[HistorialPagos], Double)] =
    for {
      cliente   <- validarClienteExiste(solicitud.clienteId, mapaClientes)
      historial <- validarHistorialSuficiente(solicitud.clienteId, historialTotal)
      promedio  <- validarIngresosSuficientes(cliente)
      _         <- validarMontoSolicitud(solicitud, promedio)
    } yield (cliente, historial, promedio)

  def ventanasEnMora(historial: List[HistorialPagos], ventana: Int = 3): Int =
    historial.sortBy(_.mes)
      .sliding(ventana)
      .count(w => w.forall(_.diasAtraso > 0))

  def historialReciente(historial: List[HistorialPagos], meses: Int = 3): List[HistorialPagos] = {
    val ordenado = historial.sortBy(_.mes)
    ordenado.slice(ordenado.length - meses, ordenado.length)
  }

  def tieneAlgunAtraso(historial: List[HistorialPagos]): Boolean =
    historial.exists(_.diasAtraso > 0)

  def proporcionPagosATiempo(historial: List[HistorialPagos]): Double =
    if (historial.isEmpty) 0.0
    else {
      val aTiempo = historial.count(_.diasAtraso == 0)
      aTiempo.toDouble / historial.length
    }

  def construirMapaClientes(clientes: List[Cliente]): Map[String, Cliente] =
    clientes.map(c => c.id -> c).toMap

  def construirMapaHistorial(historial: List[HistorialPagos]): Map[String, List[HistorialPagos]] =
    historial.groupBy(_.clienteId)

  def calcularPuntaje(cliente: Cliente, historial: List[HistorialPagos], movimientosCliente: List[MovimientoFinanciero], solicitud: SolicitudCredito): (Double) = {
    var puntaje = 100.0

    val propATiempo      = proporcionPagosATiempo(historial)
    val pp = (1 - propATiempo) * 30.0
    puntaje -= pp

    val reciente   = historialReciente(historial, 3)
    val mesesMora  = mesesConsecutivosMora(reciente.sortBy(-_.mes))
    val penMora    = mesesMora * 7.0
    puntaje -= penMora

    val deudaTotal    = calcularDeudaTotal(movimientosCliente)
    val promIngreso   = calcularPromedioIngresos(cliente.ingresosMensuales)
    val ratioDeuda    = if (promIngreso > 0) deudaTotal / promIngreso else 99.0
    val penDeuda      = Math.min(ratioDeuda * 5.0, 20.0)
    puntaje -= penDeuda

    val ventanasMora = ventanasEnMora(reciente)
    val penReciente  = ventanasMora * 5.0
    puntaje -= penReciente

    if (!tieneAlgunAtraso(historial)) {
      puntaje += 5.0
    }

    (Math.max(0.0, Math.min(100.0, puntaje)))
  }

  def clasificarRiesgo(puntaje: Double): String = puntaje match {
    case p if p >= 75 => "Bajo"
    case p if p >= 50 => "Medio"
    case _            => "Alto"
  }

  def tomarDecision(puntaje: Double): (Boolean, String) = puntaje match {
    case p if p >= 75 => (true,  "APROBADO")
    case p if p >= 50 => (false, "EN REVISIÓN")
    case _            => (false, "RECHAZADO")
  }



  def evaluarSolicitud(solicitud: SolicitudCredito, mapaClientes: Map[String, Cliente], historialTotal: List[HistorialPagos], movimientosTotal: List[MovimientoFinanciero]): ResultadoEvaluacion = {

    validarSolicitud(solicitud, mapaClientes, historialTotal) match {

      case Left(error) =>
        ResultadoEvaluacion(
          clienteId    = solicitud.clienteId,
          puntaje      = 0.0,
          nivelRiesgo  = "Alto",
          aprobado     = false,
        )

      case Right((cliente, historial, _)) =>
        val movCliente     = movimientosTotal.filter(_.clienteId == cliente.id)
        val (puntaje) = calcularPuntaje(cliente, historial, movCliente, solicitud)
        val riesgo         = clasificarRiesgo(puntaje)
        val (aprobado, dec)= tomarDecision(puntaje)

        ResultadoEvaluacion(
          clienteId   = cliente.id,
          puntaje     = puntaje,
          nivelRiesgo = riesgo,
          aprobado    = aprobado
        )
    }
  }

}
