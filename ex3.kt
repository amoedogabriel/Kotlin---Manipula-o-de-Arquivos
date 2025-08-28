import java.io.File

fun criarEstruturaDeDiretorios(basePath: String) {
    val baseDir = File(basePath)
    val fotosDir = File(baseDir, "fotos")
    val documentosDir = File(baseDir, "documentos")

    try {
        baseDir.mkdir()
        fotosDir.mkdir()
        documentosDir.mkdir()
        println("Estrutura de diretórios criada em '$basePath'.")
    } catch (e: SecurityException) {
        println("Erro de permissão ao criar diretórios: ${e.message}")
    }
}

fun listarEAdicionarArquivo(basePath: String) {
    val baseDir = File(basePath)
    if (!baseDir.exists() || !baseDir.isDirectory) {
        println("O diretório base '$basePath' não existe.")
        return
    }

    println("\n--- Subdiretórios em '$basePath' ---")
    val subdiretorios = baseDir.listFiles { file -> file.isDirectory }
    subdiretorios?.forEach { dir -> println(dir.name) }
    println("---------------------------------")

    try {
        val arquivoNotas = File(basePath, "documentos/notas.txt")
        arquivoNotas.createNewFile()
        println("Arquivo 'notas.txt' criado em '/documentos'.")
    } catch (e: Exception) {
        println("Erro ao criar arquivo: ${e.message}")
    }
}

fun excluirDiretorioRecursivamente(path: String) {
    val diretorio = File(path)
    if (diretorio.exists()) {
        val sucesso = diretorio.deleteRecursively()
        if (sucesso) {
            println("Diretório '$path' e todo o seu conteúdo foram excluídos.")
        } else {
            println("Falha ao excluir o diretório '$path'.")
        }
    } else {
        println("O diretório '$path' não existe para ser excluído.")
    }
}

fun main() {
    val caminhoBase = "backup"

    println("--- Passo 1: Criando estrutura ---")
    criarEstruturaDeDiretorios(caminhoBase)

    println("\n--- Passo 2: Listando e adicionando arquivo ---")
    listarEAdicionarArquivo(caminhoBase)

    println("\n--- Passo 3: Excluindo a estrutura ---")
    excluirDiretorioRecursivamente(caminhoBase)
}
