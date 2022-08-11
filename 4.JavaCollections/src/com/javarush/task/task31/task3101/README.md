1. The main() method receives two parameters.
The first is path - the path to the directory, the second is resultFileAbsolutePath - the name (full path) of the existing file that will contain the result.
2. Rename resultFileAbsolutePath to allFilesContent.txt (use FileUtils.renameFile() method, and, if needed, FileUtils.isExist()).
3. For each file in the path directory and in all its subdirectories, do the following:
If the file is NOT more than 50 bytes long, write its contents to allFilesContent.txt.
After each file body write "\n".
All files have a txt extension.
Use "/" as a path separator.
Use the File(String pathname) constructor to create files.

1. На вход метода main() подаются два параметра.
Первый - path - путь к директории, второй - resultFileAbsolutePath - имя (полный путь) существующего файла, который будет содержать результат.
2. Переименовать resultFileAbsolutePath в allFilesContent.txt (используй метод FileUtils.renameFile(), и, если понадобится, FileUtils.isExist()).
3. Для каждого файла в директории path и в ее всех вложенных поддиректориях выполнить следующее:
Если у файла длина в байтах НЕ больше 50, то записать его содержимое в allFilesContent.txt.
После каждого тела файла записать "\n".
Все файлы имеют расширение txt.
В качестве разделителя пути используй "/".
Для создания файлов используй конструктор File(String pathname).
