open class ArmaDeFuego(
    private val nombre: String,
    protected var municion: Int,
    protected val municionARestar: Int,
    private val tipoDeMunicion: String,
    private val radio: String,
    private val danio: Int
) {
    init {
        require(radio == "Pequeño" || radio == "Amplio")
        require(municion > 0)
    }

    open fun dispara(): Int {
        if (municion >= municionARestar)
            municion -= municionARestar
        return municion
    }

    fun recarga(c: Int): Int {
        municion += c
        return municion
    }

    override fun toString(): String {
        return "El Arma $nombre le queda un numero de $municion de tipo $tipoDeMunicion con un radio $radio y un daño $danio "
    }

}

interface DispararDefault {
    var municion: Int
    var municionARestar: Int
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

class Casa(override var municion: Int, override var municionARestar: Int) : DispararDefault

class Coche(override var municion: Int, override var municionARestar: Int) : DispararDefault

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
    val pistola = Pistola("Five-Seven", 10, 2, 1, "Calibre 50")
    val bazooka = Bazooka("RPG", 10, 100, 1, "Misil")
    val rifle = Rifle("Commando", 6, 7, 1)
    val casita = Casa(12, 2)
    val bmw = Coche(50, 1)
    val lista = listOf(pistola, bazooka, rifle)
    val mapaArmas = mutableMapOf<String, ArmaDeFuego>()
    //Programa que recorre un mapa y dispara cada valor.
    for (i in 0 until 6) {
        mapaArmas["$i"] = lista.random()
    }

    mapaArmas.mapValues { it ->
        it.value.also { it.dispara(); println(it) }
    }
    repeat(200) { print('*') }
    //Fin del programa

    println()
    println("Interfaz añadida")
    //CASA
    println("Municion de la casa " + casita.municion)
    casita.dispara()
    println("Municion despues de disparar " + casita.municion)
    //BMW
    println("Disparo del BMW municion ${bmw.municion}")
    bmw.dispara()
    println("Municion despues de disparar ${bmw.municion} y recarga")
    bmw.recarga(200)
    println(bmw.municion)


}

