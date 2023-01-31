package day7

def input = new java.io.File("./input.txt").text

def fileSystem = buildFileSystem(input)
def dirs = findDir(fileSystem.getRoot())

assert dirs.sum { it.totalSize } == 1513699

static List<Dir> findDir(Dir rootDir) {
    def result = []
    rootDir.getSubDirectories().each {
        if (it.getTotalSize() <= 100000) {
            result.add(it)
        }

        if (it.subDirectories.size() > 0) {
            result.addAll(findDir(it))
        }
    }
    result
}

static FileSystem buildFileSystem(String input) {
    def fileSystem = new FileSystem()
    def commands = parseCommands(input)

    commands.each { processCommand(it, fileSystem) }
    fileSystem
}

static List<List<String>> parseCommands(String input) {
    def currentCommand = []
    def commands = []
    input.eachLine {
        if (it.startsWith("\$")) {
            if (!currentCommand.isEmpty()) {
                commands.add(new ArrayList(currentCommand))
                currentCommand.clear()
            }
            currentCommand.add(it)

        } else {
            currentCommand.add(it)
        }
    }
    commands.add(new ArrayList(currentCommand))
    commands
}


static void processCommand(List<String> command, FileSystem fileSystem) {
    switch (command.get(0) as String) {
        case { it.startsWith("\$ cd") }:
            processCd(command, fileSystem)
            break
        case { it.contains("ls") }:
            processLs(command, fileSystem)
            break
        default:
            throw new Exception("Unsupported command")
    }

}

static void processCd(List<String> command, FileSystem fileSystem) {
    def nextDir = command.get(0).split("cd ")[1].trim()

    switch (nextDir) {
        case "/":
            fileSystem.setCurrentDir(fileSystem.root)
            break
        case "..":
            fileSystem.currentDir = fileSystem.currentDir.previousDir
            break
        default:
            fileSystem.currentDir = fileSystem.currentDir.subDirectories.find { it.name == nextDir }
    }
}

static void processLs(List<String> command, FileSystem fileSystem) {
    command.tail().each {
        if (it.startsWith("dir")) {
            def dirName = it.split("dir")[1].trim()

            def dir = new Dir(dirName)
            dir.previousDir = fileSystem.currentDir
            if (!fileSystem.currentDir.subDirectories.contains(dir)) {
                fileSystem.currentDir.subDirectories.add(dir)
            }
        } else {
            def fileSplit = it.split(" ")
            def file = new File(fileSplit[1], fileSplit[0] as long)
            fileSystem.currentDir.files.add(file)
        }
    }
}