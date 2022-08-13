Базовый путь к файлу задаётся следующим образом : ".\airports.dat". То есть считается, что файл с данными находится в той же папке, что и jar файл.
Путь можно изменить, передав необходимый параметр при запуске jar файла.
последовательность команд для запуска приложения:
mvn clean package

java -Xmx7m -jar airports-search-1.0 [номер столбца] [путь к файлу]

Буду считать, что : 
	значения внутри колонок уникальны.
	файл находится в кодировке UTF-8.


Решение было выполнено при помощи префиксного дерева, благодаря этому скорость поиска быстрее O(n).
Для работы со строками из файла не используется String, все символы строки хранятся в стурктурах данных, которые очищаются по необходимости. Благодаря этому программа не выходит за заданные рамки по памяти.