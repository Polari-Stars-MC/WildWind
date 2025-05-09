import java.io.File

fun File.appendFileContent(targetFile: File) {
    targetFile.appendText("\n\n")
    targetFile.appendText(this.readText(Charsets.UTF_8), Charsets.UTF_8)
}

fun Map<String, String>.processFolder(sourceFolder: File, outputFolder: File) {
    if(sourceFolder.exists().not().or(sourceFolder.isDirectory.not())) {
        return
    }

    sourceFolder.walk().filter { it.isFile }.forEach {
        var t = it.absolutePath
        this.forEach { k, v ->
            t = t.replace("\${$k}", v)
        }
        val relativePath = sourceFolder.toPath().relativize(File(t).toPath())

        val targetFile = outputFolder.toPath().resolve(relativePath).toFile()

        targetFile.parentFile.mkdirs()

        if (targetFile.exists()) {
            it.appendFileContent(targetFile)
        } else {
            it.copyTo(targetFile)
        }
    }
}

fun Map<String, String>.mergePath(exportFile: File, rootFile: File, projectFile: File): File {
    exportFile.deleteRecursively()
    this.processFolder(rootFile, exportFile)
    this.processFolder(projectFile, exportFile)
    return exportFile
}