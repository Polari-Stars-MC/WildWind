import java.io.File

fun File.appendFileContent(targetFile: File) {
    targetFile.appendText("\n\n")
    targetFile.appendText(this.readText(Charsets.UTF_8), Charsets.UTF_8)
}

fun File.discrepancy(targetFile: File) {
    if(this.exists().not().or(this.isDirectory.not())) {
        return
    }
    targetFile.mkdirs()
    this.walk().filter { it.isFile }.forEach {
        val relativePath =this.toPath().relativize(it.toPath())
        val path = targetFile.toPath().resolve(relativePath).toFile()

        if (path.exists()) {
            it.deleteRecursively()
        } else {
            path.parentFile.mkdirs()
            it.copyTo(path)
        }
    }
}

fun Map<String, String>.processFolder(sourceFolder: File, outputFolder: File, isAppend: Boolean = true) {
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
        if(targetFile.exists().not()) {
            it.copyTo(targetFile)
        } else if(isAppend) {
            it.appendFileContent(targetFile)
        }
    }
}

fun Map<String, String>.mergePath(exportFile: File, rootFile: File, projectFile: File): File {
    exportFile.deleteRecursively()
    this.processFolder(rootFile, exportFile)
    this.processFolder(projectFile, exportFile)
    return exportFile
}