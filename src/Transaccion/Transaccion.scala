package Transaccion

case class Transaccion(id: Int,cuentaId: Int, monto:Double,tipo: String  ) {



  def esTransaccionSegura(monto : Double):Boolean = {
    (monto > 0 && monto < 10000)
  }

  def calcularComision(banco: String, tasa: Double)(monto: Double): Double = {
    tasa * monto
  }


  def comisionEstandar: (Double => Double) = {
    calcularComision("generico", 0.02)
  }

  def comisionPremium: (Double => Double) = {
    calcularComision("vip", 0.005)
  }


  def procesarLote(transacciones : List[Transaccion],filtrado: Double => Boolean,transformacion : Double => Double ): List[Double] = {
    val nuevaTrans =  transacciones.filter(t=> filtrado(t.monto)).map(t=> transformacion(t.monto))
    nuevaTrans
  }

  def mensaje(tipo:String): String = {
      val t =  "Tu tipo es"
      tipo match{
        case "DEPOSITO" => s"$t, $tipo"
        case "RETIRO" => s"$t, $tipo"
        case "TRANSFERENCIA" => s"$t, $tipo"
        case _ => "No existe tu tipo"
      }
  }
}



