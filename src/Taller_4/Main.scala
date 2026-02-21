package Taller_4

import scala.Console.println


object Main {
  def main(args: Array[String]): Unit = {

    var mapa = Map(1->"Tarjeta",2->"Prestamo",3->"Cuenta")

    println(Option(mapa.get(4)).flatMap(x=> x.flatMap(y=> Option(y))))


  }
}
