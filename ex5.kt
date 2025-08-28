import java.io.File

data class Usuario(val nome: String, val email: String, val idade: Int)

const val NOME_ARQUIVO_CSV = "usuarios.csv"

fun adicionarUsuario() {
    print("Digite o nome: ")
    val nome = readLine() ?: ""
    print("Digite o email: ")
    val email = readLine() ?: ""
    print("Digite a idade: ")
    val idade = readLine()?.toIntOrNull() ?: 0

    if (nome.isNotBlank() && email.isNotBlank() && idade > 0) {
        val linhaCsv = "$nome,$email,$idade\n"
        File(NOME_ARQUIVO_CSV).appendText(linhaCsv)
        println("Usuário adicionado com sucesso!")
    } else {
        println("Dados inválidos. O usuário não foi adicionado.")
    }
}

fun listarUsuarios() {
    val arquivo = File(NOME_ARQUIVO_CSV)
    if (!arquivo.exists()) {
        println("Nenhum usuário cadastrado.")
        return
    }

    println("\n--- Lista de Usuários ---")
    arquivo.forEachLine { linha ->
        val dados = linha.split(',')
        if (dados.size == 3) {
            println("Nome: ${dados[0]}, Email: ${dados[1]}, Idade: ${dados[2]}")
        }
    }
    println("-------------------------")
}

fun buscarUsuario() {
    print("Digite o email do usuário a ser buscado: ")
    val emailBusca = readLine() ?: ""
    val arquivo = File(NOME_ARQUIVO_CSV)
    var encontrado = false

    if (arquivo.exists()) {
        arquivo.forEachLine { linha ->
            val dados = linha.split(',')
            if (dados.size == 3 && dados[1].equals(emailBusca, ignoreCase = true)) {
                println("\n--- Usuário Encontrado ---")
                println("Nome: ${dados[0]}, Email: ${dados[1]}, Idade: ${dados[2]}")
                println("--------------------------")
                encontrado = true
                return@forEachLine
            }
        }
    }

    if (!encontrado) {
        println("Usuário com o email '$emailBusca' não encontrado.")
    }
}

fun main() {
    while (true) {
        println("\n--- Menu ---")
        println("1. Adicionar Usuário")
        println("2. Listar Usuários")
        println("3. Buscar Usuário por Email")
        println("4. Sair")
        print("Escolha uma opção: ")

        when (readLine()?.toIntOrNull()) {
            1 -> adicionarUsuario()
            2 -> listarUsuarios()
            3 -> buscarUsuario()
            4 -> {
                println("Saindo...")
                return
            }
            else -> println("Opção inválida. Tente novamente.")
        }
    }
}
