import java.io.File
import java.io.IOException
import java.util.Arrays

fun salvarCabecalhoBinario(nomeArquivo: String, cabecalho: ByteArray) {
    try {
        File(nomeArquivo).writeBytes(cabecalho)
        println("Cabeçalho binário salvo em '$nomeArquivo'.")
    } catch (e: IOException) {
        println("Erro ao salvar o arquivo binário: ${e.message}")
    }
}

fun verificarCabecalhoBinario(nomeArquivo: String, cabecalhoEsperado: ByteArray): Boolean {
    val arquivo = File(nomeArquivo)
    if (!arquivo.exists()) {
        println("Arquivo '$nomeArquivo' não encontrado.")
        return false
    }

    try {
        val bytesLidos = arquivo.readBytes()
        if (bytesLidos.size >= cabecalhoEsperado.size) {
            val cabecalhoLido = bytesLidos.copyOfRange(0, cabecalhoEsperado.size)
            return Arrays.equals(cabecalhoLido, cabecalhoEsperado)
        }
    } catch (e: IOException) {
        println("Erro ao ler o arquivo binário: ${e.message}")
    }
    return false
}

fun main() {
    val nomeDoArquivo = "app.dat"
    val cabecalho = byteArrayOf(0x4B, 0x4F, 0x54, 0x4C, 0x4E) // "KOTLN"

    salvarCabecalhoBinario(nomeDoArquivo, cabecalho)

    val sucesso = verificarCabecalhoBinario(nomeDoArquivo, cabecalho)

    if (sucesso) {
        println("Verificação bem-sucedida: O cabeçalho do arquivo corresponde ao esperado.")
    } else {
        println("Falha na verificação: O cabeçalho do arquivo não corresponde.")
    }
}
