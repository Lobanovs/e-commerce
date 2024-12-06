# **Микросервисное приложение для электронной коммерции**

## **Обзор проекта**

Этот проект представляет собой комплексное приложение **Микросервисы электронной коммерции**, построенное с использованием Java Spring Boot. Оно реализует архитектуру микросервисов для управления различными аспектами системы электронной коммерции, такими как управление продуктами, обработка заказов, управление клиентами и обработка платежей.

---

## **Архитектура**

1. **Микросервисы**:
    - **Сервис клиентов**: Управляет информацией и профилями клиентов.
    - **Сервис заказов**: Обрабатывает создание заказов, покупку продуктов и инициацию платежей.
    - **Сервис продуктов**: Управляет инвентарем и операциями с продуктами.
    - **Сервис платежей**: Обрабатывает платежи и подтверждает транзакции.
    - **Сервис уведомлений**: Отправляет уведомления о подтверждении заказа и обновлениях.
    - **Сервер конфигурации**: Обеспечивает централизованную конфигурацию всех сервисов.
    - **Сервер обнаружения**: Позволяет регистрировать и находить сервисы с использованием Eureka.
    - **API-шлюз**: Выполняет роль точки входа для маршрутизации и обеспечения безопасности.

2. **База данных**:
    - MongoDB используется для хранения данных в таких сервисах, как Клиенты и Продукты.

3. **Асинхронное взаимодействие**:
    - Apache Kafka используется для асинхронной коммуникации между сервисами.

4. **Контейнеризация**:
    - Docker используется для контейнеризации микросервисов, управляемых с помощью `docker-compose`.

---

## **Функционал**

- **Управление клиентами**: CRUD-операции с профилями клиентов.
- **Обработка заказов**: Создание заказов, покупка продуктов и инициация платежей.
- **Управление продуктами**: Управление инвентарем и деталями продуктов.
- **Интеграция платежей**: Поддержка различных методов оплаты.
- **Уведомления**: Уведомления о статусе заказов с использованием Kafka.
- **Обнаружение сервисов**: Регистрация и обнаружение сервисов с использованием Eureka.
- **Централизованная конфигурация**: Сервер конфигурации для консистентных настроек сервисов.

---

## **Используемые технологии**

- **Бэкенд**:
  - Java Spring Boot
  - Spring Cloud (Eureka, Config Server, Gateway)
  - Apache Kafka
  - MongoDB
- **DevOps**:
  - Docker
  - Docker Compose

---

## **Инструкция по запуску**

### **Требования**
- **Java 17** 
- **Docker** и **Docker Compose**

### **Шаги для локального запуска**
1. Клонируйте репозиторий:
   ```bash
   git clone https://github.com/Lobanovs/Cosmoport.git
   ```
## Запуск микросервисов

### Шаги запуска

1. Запустите микросервисы в следующем порядке через вашу IDE:
   - **Config Server**
   - **Discovery Server**
   - **Customer Service**
   - **Product Service**
   - **Order Service**
   - **Payment Service**
   - **Notification Service**
   - **Gateway**

---

2. После запуска всех сервисов доступ к email-сообщениям можно получить по адресу:
   ```bash
    http://localhost:1080
   ```
---

3. Для создания заказа используйте эндпоинт Order Service. После успешного создания заказа можно проверить уведомление через email.

---

## Основные эндпоинты

### **Customer Service**
- `POST /api/v1/customers`: Создать нового клиента.
- `GET /api/v1/customers/{id}`: Получить информацию о клиенте.

### **Product Service**
- `POST /api/v1/products`: Добавить новый продукт.
- `POST /api/v1/products/purchase`: Купить продукт.
- `GET /api/v1/products`: Получить список продуктов.

### **Order Service**
- `POST /api/v1/orders`: Создать новый заказ.
- `GET /api/v1/orders`: Получить список заказов.

### **Payment Service**
- `POST /api/v1/payments`: Инициировать платеж.

---

## Технологии

- **Java 17**: Основной язык разработки.
- **Spring Boot**: Быстрая разработка микросервисов.
- **PostgreSQL**: Хранение данных.
- **Kafka**: Асинхронная коммуникация между сервисами.
- **Docker**: Контейнеризация приложения.


## Пример использования приложения
создание пользователя: 
![image](https://github.com/user-attachments/assets/1a061aa2-1417-4e73-bc58-5e4032290131)

---

создание заказа:
![image](https://github.com/user-attachments/assets/2d9b35ee-19e1-4fcf-a538-e71a0d6eed0a)

---

получение информации о заказе на email по порту localhost:1080 : 

![image](https://github.com/user-attachments/assets/8042183d-d304-4b27-a362-f4d49378364b)

---

![image](https://github.com/user-attachments/assets/f53b9805-5988-4af3-82ae-847aef209682)


