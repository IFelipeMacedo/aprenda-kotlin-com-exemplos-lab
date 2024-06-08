import java.util.*

enum class Nivel { BASICO, INTERMEDIARIO, AVANCADO }

data class Usuario(val nome: String) {
    val matricula: String = gerarMatricula()

    companion object {
        private val matriculasExistente = mutableSetOf<String>()

        private fun gerarMatricula(): String {
            val random = Random()
            var matricula: String
            do {
                val sb = StringBuilder()
                repeat(5) {
                    sb.append(random.nextInt(10)) 
                }
                matricula = sb.toString()
            } while (matriculasExistente.contains(matricula))

            matriculasExistente.add(matricula)
            return matricula
        }
    }
}

data class ConteudoEducacional(val nome: String, val duracao: Int = 60)

data class Formacao(val nome: String, val nivel: Nivel, val conteudos: List<ConteudoEducacional>) {
    private val inscritos = mutableListOf<Usuario>()

    fun matricular(usuario: Usuario) {
        inscritos.add(usuario)
        println("Usuário ${usuario.nome} (ID: ${usuario.matricula}) matriculado com sucesso na formação $nome.")
    }

    fun exibirInscritos() {
        println("Usuários matriculados na formação $nome: ")
        for (usuario in inscritos) {
            println("Nome: ${usuario} / Matrícula: ${usuario.matricula}")
        }
    }
}

fun main() {
    // Conteúdos educacionais
    val kotlinBasico = ConteudoEducacional("Kotlin Básico", 120)
    val kotlinIntermediario = ConteudoEducacional("Kotlin Intermediário", 180)
    val kotlinAvancado = ConteudoEducacional("Kotlin Avançado", 240)

    // Formação
    val formacaoKotlin = Formacao("Formação Kotlin Developer", Nivel.AVANCADO, listOf(kotlinBasico, kotlinIntermediario, kotlinAvancado))

    // Criando usuários
    val usuario1 = Usuario(nome = "João")
    val usuario2 = Usuario(nome = "Maria")
    val usuario3 = Usuario(nome = "Pedro")

    // Matriculando
    formacaoKotlin.matricular(usuario1)
    formacaoKotlin.matricular(usuario2)
    formacaoKotlin.matricular(usuario3)

    // Exibindo
    formacaoKotlin.exibirInscritos()
}
