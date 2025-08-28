import java.io.File
import java.text.SimpleDateFormat
import java.util.Date

fun criarArquivosDeExemplo(diretorio: File) {
    diretorio.mkdirs()
    File(diretorio, "documento1.txt").writeText("Conteúdo do doc 1")
    File(diretorio, "imagem.jpg").writeBytes(byteArrayOf(1, 2, 3))
    File(diretorio, "planilha.csv").writeText("a,b,c")
    println("✅ Arquivos de exemplo criados em '${diretorio.path}'.")
}

fun executarBackup(origem: File, destino: File) {
    if (!origem.exists()) {
        println("❌ Diretório de origem não existe.")
        return
    }

    val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
    val backupDir = File(destino, "backup_$timestamp")
    backupDir.mkdirs()

    try {
        origem.listFiles()?.forEach { arquivo ->
            arquivo.copyTo(File(backupDir, arquivo.name), overwrite = true)
        }
        println("✅ Backup realizado com sucesso em '${backupDir.path}'.")
    } catch (e: Exception) {
        println("❌ Erro ao realizar backup: ${e.message}")
    }
}

fun limparBackupsAntigos(destino: File, manter: Int) {
    val backups = destino.listFiles { file -> file.isDirectory && file.name.startsWith("backup_") }
        ?.sortedBy { it.name }

    if (backups != null && backups.size > manter) {
        val paraExcluir = backups.take(backups.size - manter)
        println("\n--- Limpando backups antigos ---")
        paraExcluir.forEach { dir ->
            if (dir.deleteRecursively()) {
                println("🗑️ Backup '${dir.name}' excluído.")
            } else {
                println("❌ Falha ao excluir '${dir.name}'.")
            }
        }
    }
}

fun main() {
    val diretorioOrigem = File("meus_arquivos")
    val diretorioDestino = File("meus_backups")

    
    diretorioOrigem.deleteRecursively()
    criarArquivosDeExemplo(diretorioOrigem)

  
    repeat(7) {
        println("\n--- Executando Backup #${it + 1} ---")
        executarBackup(diretorioOrigem, diretorioDestino)
        Thread.sleep(1000) // Pausa para garantir timestamps diferentes
    }

 
    limparBackupsAntigos(diretorioDestino, 5)

    println("\n--- Backups restantes ---")
    diretorioDestino.listFiles()?.forEach {
        println(it.name)
    }
}
