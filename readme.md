# Task Tracker
1.	РЕГИСТРАЦИИ НОВОГО ПОЛЬЗОВАТЕЛЯ
2.	АВТОРИЗАЦИЯ ПОЛЬЗОВАТЕЛЯ
3.	ВОЗМОЖНОСТЬ ДОБАВЛЕНИЯ, ПРОСМОТРА, ИЗМЕНЕНИЯ, УДАЛЕНИЯ ЗАДАЧИ В TO-DO LIST
4.	ВОЗМОЖНОСТЬ ПРИКРИПЛЕНИЕ ФАЙЛОВ К ЗАДАЧАМ.

## Используемые технологии
- Java 11
- spring boot web mvc, jpa, security
- lombok
- docker
- maven

### Схема БД 
![схема бд для тестового задания.jpg](file.png)

#### Сборка проекта и запуск
происходит через команду docker compose up

#### Endpoints task-tracker

Task
-------------------
Создание таски
POST /task Task task
Получение таски
GET /task/{id}
Получение всех тасок
GET /tasks
Изменение таски 
PATCH /task/{id} Task task
Удаление таски
DELETE /task/{id}

TodoList
---------------------------------
Создание туду лист
POST /todolist TodoList todolist
Получение туду с тасками
GET /todolist/{id} TodoList with Task
Получение всех туду с тасками
GET /todolists
Удаление туду листа по id
DELETE /todolist/{id}

User
-------------------------------------
Регистрация пользователся
POST /user/register
Удаление пользователя админом
DELETE /admin/user/{id}
Отключение пользователя самим пользователем
PATCH /user/{id}/disabled
Отключение пользователя админом
PATCH /admin/user/id/disabled
Изменение подльзователя админом
PATCH /admin/user/{id} User user
Изменение пользователя самим владельцем
PATCH /user/{id} User user
Авторизация пользователя
POST /user/auhtorization User user session id

File
-------------------------------------------
Добавление файла к задаче
POST /file/task/{id}
Получение файла 
GET /file/task/{id}
Удаление файла из задачи
DELETE /file/task/{id}
