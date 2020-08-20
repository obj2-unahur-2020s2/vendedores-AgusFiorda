package ar.edu.unahur.obj2.vendedores

class CentrosDistribucion(val ciudad: Ciudad){
    val vendedores = mutableListOf<Vendedor>()

    fun agregarVendedor(vendedor: Vendedor) {
        if(vendedores.contains(vendedor)){
            print(message = ("ERROR..Ya esta registrado ese vendedor!"))
        }

        vendedores.add(vendedor)
    }


    fun vendedorEstrella() = vendedores.maxBy { vend->vend.puntajeCertificaciones() }


	fun puedeCubrir(ciudad: Ciudad): Boolean{
		return vendedores.any{vend->vend.puedeTrabajarEn(ciudad)}
	}

	fun vendedoresGenericosRegistrados(): List<Vendedor> {
		return vendedores.filter{vend->vend.otrasCertificaciones()>0}
	}

	fun esRobusto(): Boolean{
		return vendedores.count{vend->vend.esFirme()}>=3
	}



}