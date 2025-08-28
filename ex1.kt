import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.io.IOException

@Serializable
data class ConfiguracaoUsuario(
    val nome: String,
    val idioma: String,
    val tema: String
)

fun lerEImprimirConfiguracoes(nomeArquivo: String = "config.json") {
    val arquivo = File(nomeArquivo)
    if (!arquivo.exists()) {
        println("Erro: O arquivo '$nomeArquivo' não foi encontrado.")
        return
    }

    try {
        val jsonString = arquivo.readText()
        val configLida = Json.decodeFromString<ConfiguracaoUsuario>(jsonString)

        println("\n--- Configurações Lidas do Arquivo ---")
        println("👤 Nome: ${configLida.nome}")
        println("🌐 Idioma: ${configLida.idioma}")
        println("🎨 Tema: ${configLida.tema}")
        println("------------------------------------")

    } catch (e: Exception) {
        println("❌ Ocorreu um erro ao ler ou decodificar o arquivo: ${e.message}")
    }
}

fun main() {
    val config = ConfiguracaoUsuario(
        nome = "Carlos Silva",
        idioma = "pt-BR",
        tema = "escuro"
    )

    val json = Json { prettyPrint = true }

    try {
        val jsonString = json.encodeToString(config)
        val nomeArquivo = "config.json"
        File(nomeArquivo).writeText(jsonString)

        println("✅ Configurações salvas com sucesso no arquivo '$nomeArquivo'.")
        println("\nConteúdo do arquivo gerado:")
        println(jsonString)

        lerEImprimirConfiguracoes(nomeArquivo)

    } catch (e: IOException) {
        println("❌ Ocorreu um erro ao salvar o arquivo: ${e.message}")
    }
}