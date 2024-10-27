Домашнее задание. Docker
Формулировка
В рамках первого домашнего задания, требуется для приложения TODO List собрать backend и frontend части и запустить их в docker.

1) Приложение реализует простейший TODO list:

2) Backend написан на Kotlin + Spring, frontend написан на Typescript + React/Redux.

3) Backend запускается на порту 8080, профиль docker.

4) Backend для хранения данных использует PostgreSQL 13.

5) Frontend мапится наружу на порт 3000, запросы к backend пробрасываются

через proxy_pass http://backend-service:8080. 

Требования
1) Для успешного выполнения задания нужно установить на host машину:
git;
OpenJDK 11;
Docker

2) Код backend'а и frontend'а хранится в отдельных репозиториях, они подключаются через Git Modules.

3) Нужно реализовать двухэтапную сборку приложений, сборку контейнеров описать в файлах backend.Dockerfile и frontend.Dockerfile.

4) В файле test.sh дописать необходимые шаги:
createNetworks;
createVolume;
runPostgres;
runBackend;
runFrontend.

5) Для хранения данных в Postgres нужно создать Volume.

6) Внешний маппинг портов:
backend 8080:8080;
frontend 3000:80

7) Нужно создать две разных сети (driver=bridge):
для взаимодействия между backend и PostgreSQL;
для взаимодействия backend и frontend (для этой сети указать alias для контейнера backend backend-service, т.к. nginx обращается через proxy_pass к http://backend-service:8080).

8) Docker compose использовать нельзя, все ресурсы описываются через docker.

9) Контейнеры называть backend, frontend, postgres.

10) В результате реализации всех описанных выше шагов, должна быть возможность работать TODO list с localhost:3000, т.е. можно открыть страницу в браузере и проверить работу.

11) Для автоматизированной проверки работоспособности выполняется запрос из контейнера frontend в контейнер backend по имени сервиса.

Пояснения
1) Для сборки затяните backend-todo-list и backend-todo-list с помощью команды git submodule update --init --recursive.

2) Backend нужно запустить с профилем docker. Для этого требуется внутрь контейнера пробросить переменную среды SPRING_PROFILES_ACTIVE=docker.

3) Для очистки ресурсов можно использовать cleanup.sh, он удаляет контейнеры, сети, volumes.

4) Для backend нужно в Postgres создать БД todo_list и пользователя program:test. Здесь можно использовать два варианта решения:

Можно создать пользователя и БД с помощью переменных среды POSTGRES_* при старте контейнера Postgres. Это рабочий вариант, но созданный пользователь будет иметь права SUPERUSER, что плохо с точки зрения безопасности.
Обычно для работы с приложением создают отдельного пользователя. В образе Postgres есть возможность использовать скрипты инициализации для первого страта контейнера (блок Initialization scripts в документации).  В backend есть пример такого запуска контейнера Postgres с помощью docker-compose.yml: при старте контейнера создается пользователь с правами SUPERUSER, а в 10-create-user-and-db.sql создается отдельная БД и пользователь для нее. Это нужно, чтобы программа работала с пользователем, ограниченным в правах.
Внимание! Задание для самопроверки. Выполните задачу, напишите в поле удалось ли вам ее выполнить и нажмите кнопку "отправить". Ответ будет засчитан автоматически. После этого вернитесь к прохождению курса и ознакомьтесь с видеоразбором задания.
