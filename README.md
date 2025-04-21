# Фрактальное пламя
Цель данного проекта – познакомиться с многопоточностью в Java.
Сделать benchmark работы однопоточного приложения и многопоточного
Полученные картинки можете увидеть в конце данного файла.
Сравнение многопоточной и однопоточной реализации в файле comparison.md

## Сборка и запуск

Для того чтобы собрать проект, и проверить, что все работает корректно, можно
запустить из модального окна IDEA
[Run Anything](https://www.jetbrains.com/help/idea/running-anything.html)
команду:

```shell
mvn clean verify
```

Альтернативно можно в терминале из корня проекта выполнить следующие команды.

Для Unix (Linux, macOS, Cygwin, WSL):

```shell
./mvnw clean verify
```

Для Windows:

```shell
mvnw.cmd clean verify
```

Для окончания сборки потребуется подождать какое-то время, пока maven скачает
все необходимые зависимости, скомпилирует проект и прогонит базовый набор
тестов.

Если вы в процессе сборки получили ошибку:

```shell
Rule 0: org.apache.maven.enforcer.rules.version.RequireJavaVersion failed with message:
JDK version must be at least 22
```

Значит, версия вашего JDK ниже 22.

Если же получили ошибку:

```shell
Rule 1: org.apache.maven.enforcer.rules.version.RequireMavenVersion failed with message:
Maven version should, at least, be 3.8.8
```

Значит, у вас используется версия maven ниже 3.8.8. Такого не должно произойти,
если вы запускаете сборку из IDEA или через `mvnw`-скрипты.

Далее будут перечислены другие полезные команды maven.

Запуск только компиляции основных классов:

```shell
mvn compile
```

Запуск тестов:

```shell
mvn test
```

Запуск линтеров:

```shell
mvn checkstyle:check modernizer:modernizer spotbugs:check pmd:check pmd:cpd-check
```

Вывод дерева зависимостей проекта (полезно при отладке транзитивных
зависимостей):

```shell
mvn dependency:tree
```

Вывод вспомогательной информации о любом плагине (вместо `compiler` можно
подставить интересующий вас плагин):

```shell
mvn help:describe -Dplugin=compiler
```

## Результат работы программы

![img.png](img.png)
![img_1.png](img_1.png)
![img_2.png](img_2.png)
![img_3.png](img_3.png)
![img_4.png](img_4.png)
