open class ArmaDeFuego(
    private val nombre: String,
    final override var municion: Int,
    override val municionARestar: Int,
    override val tipoDeMunicion: String,
    final override val radio: String,
    override val danio: Int

) : DispararDefault {
    init {
        require(radio == "Pequeño" || radio == "Amplio"){-1}
        require(municion > 0)
    }


    override fun toString(): String {
        return "El Arma $nombre le queda un numero de $municion de tipo $tipoDeMunicion con un radio $radio y un daño $danio "
    }

}

interface DispararDefault {
    var municion: Int
    val municionARestar: Int
    val tipoDeMunicion: String
    val radio: String
    val danio: Int
    fun dispara(): Int {
        if (municion >= municionARestar)
            municion -= municionARestar
        return municion


    }

    fun recarga(c: Int): Int {
        municion += c
        return municion
    }
}

class Casa(
    val nombre: String,
    override var municion: Int, override var municionARestar: Int,
    override val tipoDeMunicion: String,
    override val radio: String,
    override val danio: Int
) : DispararDefault {
    init {
        require(radio == "Pequeño" || radio == "Amplio"){-1}
        require(municion > 0)
    }
    override fun toString(): String {
        return "La Casa $nombre le queda un numero de $municion de tipo $tipoDeMunicion con un radio y un daño $danio"
    }
}
    class Coche(
        val nombre: String,
        override var municion: Int, override var municionARestar: Int,
        override val tipoDeMunicion: String,
        override val radio: String,
        override val danio: Int
    ) : DispararDefault {
        init {
            require(radio == "Pequeño" || radio == "Amplio"){-1}
            require(municion > 0)
        }
        override fun toString(): String {
            return "El Coche $nombre le queda un numero de $municion de tipo $tipoDeMunicion con un radio $radio y un daño $danio"
        }
    }
        class Bocadillo(
            val nombre: String,
            override var municion: Int, override var municionARestar: Int,
            override val tipoDeMunicion: String,
            override val radio: String,
            override val danio: Int
        ) : DispararDefault {
            init {
                require(radio == "Pequeño" || radio == "Amplio"){-1}
                require(municion > 0)
            }
            override fun toString(): String {
                return "El Bocadillo $nombre le queda un numero de $municion de tipo $tipoDeMunicion con un radio $radio y un daño $danio"

            }
        }

        class Pistola(
            nombre: String,
            municion: Int,
            danio: Int,
            municionARestar: Int,
            tipoDeMunicion: String,
            radio: String = "Pequeño"
        ) : ArmaDeFuego(nombre, municion, municionARestar, tipoDeMunicion, radio, danio) {
            override fun dispara(): Int {
                if (municion >= municionARestar)
                    municion -= municionARestar
                return municion
            }
        }

        class Rifle(
            nombre: String,
            municion: Int,
            danio: Int,
            municionARestar: Int,
            tipoDeMunicion: String = ".20",
            radio: String = "Pequeño"

        ) : ArmaDeFuego(nombre, municion, municionARestar, tipoDeMunicion, radio, danio) {
            override fun dispara(): Int {
                if (municion >= municionARestar * 2)
                    municion -= municionARestar * 2
                return municion
            }
        }

        class Bazooka(
            nombre: String,
            municion: Int,
            danio: Int,
            municionARestar: Int,
            tipoDeMunicion: String = "Misil",
            radio: String = "Amplio"

        ) : ArmaDeFuego(nombre, municion, municionARestar, tipoDeMunicion, radio, danio) {
            override fun dispara(): Int {
                if (municion >= municionARestar * 3)
                    municion -= municionARestar * 3
                return municion
            }
        }


        fun main() {
            //Variables
            val pistola = Pistola("Five-Seven", 10, 2, 1, "Calibre 50",)
            val bazooka = Bazooka("RPG", 10, 100, 1, "Misil")
            val rifle = Rifle("Commando", 16, 7, 1)
            val casita = Casa("Pepe", 12, 6, "Cristales", "Pequeño", 8)
            val bmw = Coche("Bmw", 8, 2, "Ruedas", "Pequeño", 9)
            val chorizo = Bocadillo("Chorizo", 21, 1, "Chorizo", "Pequeño", 5)
            val lista = listOf(pistola, bazooka, rifle, casita, bmw)
            val mapaArmas = mutableMapOf<String, DispararDefault>()
            //Programa que recorre un mapa y dispara cada valor.
            for (i in 0 until 6) {
                mapaArmas["$i"] = lista.random()
            }

            mapaArmas.mapValues { it ->
                it.value.also { it.dispara(); println(it) }
            }
            repeat(200) { print('*') }
            //Fin del programa

}

