FROM openjdk:17-jdk-slim

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем скомпилированный jar файл (если у вас его нет, используйте команду сборки вашего приложения)
COPY target/bankservice-0.0.1-SNAPSHOT.jar bankservice.jar

# Открываем порт 8080 (если приложение на нем работает)
EXPOSE 8443

# Запускаем приложение
ENTRYPOINT ["java", "-jar", "bankservice.jar"]