import java.io.File

fun main() {
    val nomeArquivo = "saida_usuario.txt"
    val linhas = mutableListOf<String>()

    println("Digite várias linhas de texto. Digite 'SAIR' em uma linha vazia para terminar.")

    while (true) {
        val linha = readLine()
        if (linha != null && linha.equals("SAIR", ignoreCase = true)) {
            break
        }
        if (linha != null) {
            linhas.add(linha)
        }
    }

    try {
        File(nomeArquivo).writeText(linhas.joinToString("\n"))
        println("\nTexto salvo com sucesso em '$nomeArquivo'.")
    } catch (e: Exception) {
        println("Erro ao salvar o arquivo: ${e.message}")
    }

    try {
        val conteudoLido = File(nomeArquivo).readText()
        println("\n--- Conteúdo do Arquivo '$nomeArquivo' ---")
        println(conteudoLido)
        println("------------------------------------")
    } catch (e: Exception) {
        println("Erro ao ler o arquivo: ${e.message}")
    }
}
