
object Main {
  def main(args: Array[String]): Unit = {
    val t = BancoData.limpiarDatos(BancoData.transacciones)
    val t2 = BancoData.normalizacionDatos(t)
    val t3 = BancoData.calculoMovProductos(t2,BancoData.productos)
    val utilidades = BancoData.calculoUtilidades(t2)
    val gastoClientes = BancoData.calculoGastoClientes(t2,BancoData.usuarios)
    gastoClientes.foreach(println)
    //println(utilidades)
    //t3.foreach(println)
    //t2.foreach(println)
  }
}

