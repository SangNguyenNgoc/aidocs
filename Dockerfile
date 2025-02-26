# Dùng GraalVM với Maven để build
FROM maven:3.8.4-openjdk-17 AS builder
WORKDIR /app

# Copy mã nguồn và cấu hình Maven
COPY pom.xml .
COPY src ./src

# Cài Maven wrapper (nếu cần) và build JAR
RUN gu install native-image && \
    mvn clean package -DskipTests

# Tạo image runtime nhẹ
FROM ghcr.io/graalvm/graalvm-ce:21 AS runner
WORKDIR /app

# Copy JAR đã build
COPY --from=builder /app/target/*.jar app.jar

# Chạy ứng dụng với GraalVM
ENTRYPOINT ["java", "-jar", "app.jar"]
