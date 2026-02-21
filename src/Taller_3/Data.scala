package Taller_3

object Data {

  val clientes: List[Cliente] = List(
    Cliente("C1", "Ana Torres", 29, List(2500, 2600, 2550, 2700, 2800, 2900)),
    Cliente("C2", "Luis Pérez", 45, List(4000, 3900, 3800, 3700, 3600, 3500)),
    Cliente("C3", "María Gómez", 34, List(3200, 3300, 3400, 3500, 3600, 3700)),
    Cliente("C4", "Carlos Ruiz", 50, List(5000, 5100, 5200, 5300, 5400, 5500)),
    Cliente("C5", "Laura Martínez", 31, List(2800, 2700, 2600, 2500, 2400, 2300)),
    Cliente("C6", "Andrés Castro", 27, List(2200, 2300, 2400, 2500, 2600, 2700))
  )
  val historialPago = List(
    HistorialPagos("C1",1,500,500,0),
    HistorialPagos("C1",2,500,500,0),
    HistorialPagos("C1",3,450,500,5),
    HistorialPagos("C2",1,800,800,0),
    HistorialPagos("C2",2,600,800,10),
    HistorialPagos("C2",3,500,800,15),
    HistorialPagos("C3",1,700,700,0),
    HistorialPagos("C3",2,700,700,0),
    HistorialPagos("C3",3,700,700,0),
    HistorialPagos("C4",1,1000,1000,0),
    HistorialPagos("C4",2,1000,1000,0),
    HistorialPagos("C4",3,1000,1000,0),
    HistorialPagos("C5",1,600,600,0),
    HistorialPagos("C5",2,400,600,12),
    HistorialPagos("C5",3,300,600,20),
    HistorialPagos("C6",1,450,450,0),
    HistorialPagos("C6",2,450,450,0),
    HistorialPagos("C6",3,450,450,0)
  )

  val movimientos = List(
    MovimientoFinanciero("C1","TARJETA",1200,1),
    MovimientoFinanciero("C1","DEUDA",800,2),
    MovimientoFinanciero("C1","TARJETA",1500,3),
    MovimientoFinanciero("C2","DEUDA",5000,1),
    MovimientoFinanciero("C2","TARJETA",3000,2),
    MovimientoFinanciero("C2","CREDITO",7000,3),
    MovimientoFinanciero("C3","TARJETA",1000,1),
    MovimientoFinanciero("C3","TARJETA",900,2),
    MovimientoFinanciero("C3","DEUDA",800,3),
    MovimientoFinanciero("C4","CREDITO",2000,1),
    MovimientoFinanciero("C4","TARJETA",1500,2),
    MovimientoFinanciero("C4","DEUDA",1000,3),
    MovimientoFinanciero("C5","DEUDA",4000,1),
    MovimientoFinanciero("C5","TARJETA",3500,2),
    MovimientoFinanciero("C5","CREDITO",4500,3),
    MovimientoFinanciero("C6","TARJETA",500,1),
    MovimientoFinanciero("C6","DEUDA",700,2),
    MovimientoFinanciero("C6","TARJETA",600,3)
  )

  val solicitudes = List(
    SolicitudCredito("C1", 10000, 24),
    SolicitudCredito("C2", 20000, 36),
    SolicitudCredito("C3", 15000, 24),
    SolicitudCredito("C4", 25000, 48),
    SolicitudCredito("C5", 18000, 36),
    SolicitudCredito("C6", 8000, 18)
  )

}
