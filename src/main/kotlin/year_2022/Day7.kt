package year_2022

class Day7: Day(7) {

    override fun solvePart1(input: List<String>) =
        setupDisk(input)
            .getSumOfFoldersAtMost(100000)

    private fun setupDisk(input: List<String>): Folder {
        val root = Folder("/")
        var folder = root
        getCommandsWithResults(input).forEach { commandWithResults ->
            val commandLine = commandWithResults.first()
            val commandWithParams = commandLine.split(" ").drop(1)
            val command = commandWithParams.first()
            val parameters = commandWithParams.drop(1)
            val result = commandWithResults.drop(1)
            folder = when (command) {
                "cd" -> folder.cd(parameters)
                "ls" -> folder.ls(result)
                else -> throw Exception("Unsupported command $command $parameters")
            }
        }
        return root
    }

    private fun getCommandsWithResults(input: List<String>): List<List<String>> {
        val commandsWithResults = mutableListOf<MutableList<String>>()
        input.forEach { line ->
            if (line.startsWith("$")) {
                commandsWithResults.add(mutableListOf(line))
            } else {
                commandsWithResults.last().add(line)
            }
        }
        return commandsWithResults
    }

    override fun solvePart2(input: List<String>): Int {
        val root = setupDisk(input)
        val unusedSpace = 70000000 - root.getSize()
        val neededSpace = 30000000 - unusedSpace
        return root.getSizeOfSmallestFolderWithAtLeast(neededSpace)
            ?: throw Exception("No folder big enough found.")
    }

    class Folder(
        name: String,
        private val parent: Folder? = null,
    ): File(name, 0) {

        private val files: MutableList<File> = mutableListOf()
        private val root: Folder by lazy {
            var root: Folder = this
            while (root.parent != null) {
                root = root.parent!!
            }
            root
        }

        fun cd(parameters: List<String>): Folder {
            return when (val folderName = parameters.first()) {
                "/" -> root
                ".." -> parent ?: this
                else -> {
                    val folder = Folder(folderName, this)
                    files.add(folder)
                    folder
                }
            }
        }

        fun ls(result: List<String>): Folder {
            val foundFiles = result.map { line ->
                val (sizeOrDir, fileName) = line.split(" ")
                if (sizeOrDir == "dir") {
                    Folder(fileName, this)
                } else {
                    File(fileName, sizeOrDir.toInt())
                }
            }
            files.addAll(foundFiles)
            return this
        }

        override fun print(depth: Int) {
            println("${getIdent(depth)}- $name (dir)")
            files.forEach {it.print(depth + 1) }
        }

        fun getSumOfFoldersAtMost(maximumSize: Int): Int {
            val sumOfChildren = files.filterIsInstance<Folder>()
                .sumOf { it.getSumOfFoldersAtMost(maximumSize) }
            val ownSize = getSize()
            return if (ownSize <= maximumSize) {
                sumOfChildren + ownSize
            } else {
                sumOfChildren
            }
        }

        @Suppress("ConvertCallChainIntoSequence")
        fun getSizeOfSmallestFolderWithAtLeast(minimumSize: Int): Int? =
            files.filterIsInstance<Folder>()
                .mapNotNull { it.getSizeOfSmallestFolderWithAtLeast(minimumSize) }
                .toMutableList()
                .plus(getSize())
                .filter { it > minimumSize }
                .minOrNull()

        override fun getSize() =
            files.sumOf { it.getSize() }
    }

    open class File(
        val name: String,
        private val size: Int,
    ) {
        open fun getSize() = size
        open fun print(depth: Int) {
            println("${getIdent(depth)}- $name (file, size=$size)")
        }

        protected fun getIdent(depth: Int) =
            (0 .. depth).joinToString("") { "  " }
    }
}
