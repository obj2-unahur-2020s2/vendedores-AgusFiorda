package ar.edu.unahur.obj2.vendedores

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue

class VendedorTest : DescribeSpec({
  val misiones = Provincia(1300000)
  val sanIgnacio = Ciudad(misiones)

  describe("Vendedor fijo") {
    val obera = Ciudad(misiones)
    val vendedorFijo = VendedorFijo(obera)

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
    val viajante = Viajante(listOf(misiones))

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
    /*
  -Chivilcoy, Bragado, Lobos, Pergamino y Zárate: es influyente, se cumple la condición de 5 ciudades.
  -Rosario (Santa Fe), Rafaela (Santa Fe), San Francisco (Córdoba), y Diamante (Entre Ríos): es infuyente,
  se cumple la condición de 3 provincias.
  -Rosario, Rafaela, Amstrong (Santa Fe) y Diamante: no es influyente, son 4 ciudades y 2 provincias,
   no cumple ninguna de las condiciones.
   */
    val buenosAires = Provincia(20000000)
    val chivilcoy = Ciudad(buenosAires)
    val bragado = Ciudad(buenosAires)
    val lobos = Ciudad(buenosAires)
    val pergamino = Ciudad(buenosAires)
    val zarate = Ciudad(buenosAires)
    val comercioCorresponsal1= ComercioCorresponsal(listOf(bragado,chivilcoy,zarate,pergamino,lobos))
    val entreRios= Provincia(10000000)
    val diamante= Ciudad(entreRios)
    val santaFe= Provincia(1100000)
    val rosario= Ciudad(santaFe)
    val amstrong=Ciudad(santaFe)
    val rafaela=Ciudad(santaFe)
    val comercioCorresponsal2= ComercioCorresponsal(listOf(amstrong,rosario,rafaela,diamante))
    val comercioCorresponsal3= ComercioCorresponsal(listOf(amstrong,diamante,zarate))

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
