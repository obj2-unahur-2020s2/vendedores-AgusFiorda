package ar.edu.unahur.obj2.vendedores

import io.kotest.assertions.eq.eq
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.core.spec.style.stringSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe

class VendedorTest : DescribeSpec({
  //ciudades y provincias
  val misiones = Provincia(1300000)
  val sanIgnacio = Ciudad(misiones)
  val entreRios= Provincia(10000000)
  val diamante= Ciudad(entreRios)
  val santaFe= Provincia(1100000)
  val rosario= Ciudad(santaFe)
  val amstrong=Ciudad(santaFe)
  val rafaela=Ciudad(santaFe)
  val buenosAires = Provincia(20000000)
  val chivilcoy = Ciudad(buenosAires)
  val bragado = Ciudad(buenosAires)
  val lobos = Ciudad(buenosAires)
  val pergamino = Ciudad(buenosAires)
  val zarate = Ciudad(buenosAires)
  val obera = Ciudad(misiones)
  //certificaciones
  val cert1= Certificacion(true,20)
  val cert2= Certificacion(true,10)
  val cert3= Certificacion(true,5)
  val cert4= Certificacion(true,10)
  val cert5= Certificacion(false,5)
  val cert6= Certificacion(false,30)
  val cert7= Certificacion(false,15)


  //centros
  val centro1= CentrosDistribucion(rosario)


  //vendedor fijo
  val vendedorFijo = VendedorFijo(obera)
  val vendedorFijo2 = VendedorFijo(lobos)
  val vendedorFijo3 = VendedorFijo(rafaela)
  //vendedor Viajante
  val viajante = Viajante(listOf(misiones))



  //comercio Corresponsal
  val comercioCorresponsal1= ComercioCorresponsal(listOf(bragado,chivilcoy,zarate,pergamino,lobos))

  val comercioCorresponsal2= ComercioCorresponsal(listOf(amstrong,rosario,rafaela,diamante))
  val comercioCorresponsal3= ComercioCorresponsal(listOf(amstrong,diamante,zarate))


  vendedorFijo.agregarCertificacion(cert1)
  vendedorFijo2.agregarCertificacion(cert2)
  vendedorFijo3.agregarCertificacion(cert3)

  viajante.agregarCertificacion(cert4)
  viajante.agregarCertificacion(cert5)

  comercioCorresponsal1.agregarCertificacion(cert6)
  comercioCorresponsal2.agregarCertificacion(cert7)

  centro1.agregarVendedor(vendedorFijo)
  centro1.agregarVendedor(viajante)
  centro1.agregarVendedor(comercioCorresponsal1)


  describe("CentroDistribucion"){

    describe("vendedorEstrella"){
      centro1.vendedorEstrella().shouldBe(comercioCorresponsal1)
    }
    describe("puede cubrir"){
      centro1.puedeCubrir(obera).shouldBeTrue()
      centro1.puedeCubrir(amstrong).shouldBeFalse()
    }
    describe("vendedores genericos"){
      centro1.vendedoresGenericosRegistrados().shouldBe(listOf(viajante,comercioCorresponsal1))
    }
    describe("esRobusto"){
      centro1.esRobusto().shouldBeFalse()
    }
  }




  describe("Vendedor fijo") {

    describe("puedeTrabajarEn") {
      it("su ciudad de origen") {
        vendedorFijo.puedeTrabajarEn(obera).shouldBeTrue()
      }
      it("otra ciudad") {
        vendedorFijo.puedeTrabajarEn(sanIgnacio).shouldBeFalse()
      }
      it("Es influyente"){
        vendedorFijo.esInfluyente().shouldBeFalse()
      }
    }
  }

  describe("Viajante") {
    val cordoba = Provincia(11000000)
    val villaDolores = Ciudad(cordoba)

    describe("puedeTrabajarEn") {
      it("una ciudad que pertenece a una provincia habilitada") {
        viajante.puedeTrabajarEn(sanIgnacio).shouldBeTrue()
      }
      it("una ciudad que no pertenece a una provincia habilitada") {
        viajante.puedeTrabajarEn(villaDolores).shouldBeFalse()
      }
      it("Es  influyente"){
        viajante.esInfluyente().shouldBeFalse()
      }
    }
  }
  describe("comercioCorresponsal") {

    describe("puede Trabajar") {
      it("una ciudad que pertenece a una provincia") {
        comercioCorresponsal1.puedeTrabajarEn(bragado).shouldBeTrue()
      }

    }
    describe("Es influyente"){
      it("es influyente porque tiene 5 ciudades"){
        comercioCorresponsal1.esInfluyente().shouldBeTrue()
      }
    }
    describe("comercioCoresponsal2 es influyete"){
      it("no es influyente"){
        comercioCorresponsal2.esInfluyente().shouldBeFalse()
      }
    }
    describe("comercioCoresponsal3 es influyete"){
      it("es influyente"){
        comercioCorresponsal3.esInfluyente().shouldBeTrue()
      }
    }


  }
})
