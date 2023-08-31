data class Usuario(val nome: String)

enum class Nivel {
    BASICO,
    INTERMEDIARIO,
    DIFICIL
}

data class ConteudoEducacional(
    val id: Int,
    val nome: String,
    val nivel: Nivel,
    val duracao: Int = 30,
    var finalizado: Boolean = false
)

data class Formacao(
    val id: Int,
    val nome: String,
    val nivel: Nivel,
    var conteudos: List<ConteudoEducacional>,
    val finalizado: Boolean = false
) {
    val inscritos = mutableListOf<Usuario>()

    fun matricular(usuario: Usuario) {
        if (usuario.nome.isBlank()) {
            throw IllegalArgumentException("Nome do usuário não pode ser vazio")
        } else if (inscritos.contains(usuario)) {
            throw IllegalArgumentException("${usuario.nome} já está matriculado(a) nesta formação.")
        }
        inscritos.add(usuario)
        println("Parabéns ${usuario.nome}, você foi matriculado(a) com sucesso na formação $nome")
    }

    fun listarInscritos() {
        if (inscritos.isEmpty()) {
            println("Não há inscritos na formação $nome.")
        } else {
            println("Lista de Inscritos na Formação $nome:")
            for (inscrito in inscritos) {
                println(inscrito.nome)
            }
        }
    }
}

fun validarMatricula(listaUsuarios: List<Usuario>, formacao: Formacao) {
    for (usuario in listaUsuarios) {
        try {
            formacao.matricular(usuario)
        } catch (e: IllegalArgumentException) {
            println("Erro ao matricular: ${e.message}")
        }
    }
}

fun main() {
    val usuarios = listOf(Usuario("Rodrigo"), Usuario(""))

    val listaConteudoAndroid = listOf(
        ConteudoEducacional(0, "Conhecendo o Kotlin", Nivel.BASICO, 5),
        ConteudoEducacional(1, "Introdução a prática à linguagem Kotlin", Nivel.BASICO, 12),
        ConteudoEducacional(2, "Estruturas de controle", Nivel.BASICO, 20),
    )
    val listaConteudoIOS = listOf(
        ConteudoEducacional(0, "Conhecendo o Swift", Nivel.BASICO, 3),
        ConteudoEducacional(1, "Introdução a prática à linguagem Swift", Nivel.BASICO, 10),
        ConteudoEducacional(2, "Estruturas de controle", Nivel.BASICO, 5),
    )

    val formacaoAndroid = Formacao(0, "Fundamentos Android", Nivel.BASICO, listaConteudoAndroid)
    val formacaoIOS = Formacao(1, "Fundamentos IOS", Nivel.BASICO, listaConteudoIOS)

    println(formacaoAndroid)
    println(formacaoIOS)

    validarMatricula(usuarios, formacaoAndroid)
    validarMatricula(usuarios, formacaoAndroid)

    formacaoAndroid.listarInscritos()
    formacaoIOS.listarInscritos()
}