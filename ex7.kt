import java.io.File

fun main() {
    val arquivoEntrada = "poema.txt"
    val arquivoSaida = "frequencia_palavras.txt"

    val texto = """
        No meio do caminho tinha uma pedra
        tinha uma pedra no meio do caminho
        tinha uma pedra
        no meio do caminho tinha uma pedra.
    """.trimIndent()

    File(arquivoEntrada).writeText(texto)
    println("✅ Arquivo de entrada '$arquivoEntrada' criado.")

    val contagem = mutableMapOf<String, Int>()

    File(arquivoEntrada).readText()
        .lowercase()
        .replace(Regex("[^a-záàâãéèêíïóôõöúçñ\\s]"), "")
        .split(Regex("\\s+"))
        .filter { it.isNotBlank() }
        .forEach { palavra ->
            contagem[palavra] = contagem.getOrDefault(palavra, 0) + 1
        }

    val resultado = contagem.entries
        .sortedByDescending { it.value }
        .joinToString("\n") { "${it.key}: ${it.value}" }

    File(arquivoSaida).writeText(resultado)
    println("✅ Frequência de palavras salva em '$arquivoSaida'.")

    println("\n--- Resultado da Frequência ---")
    println(resultado)
    println("-------------------------------")
}
