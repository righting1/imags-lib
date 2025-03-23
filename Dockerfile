# 使用 OpenJDK 8 作为基础镜像
FROM openjdk:8-jre

# 镜像维护者信息（推荐使用 LABEL）
LABEL maintainer="righting"

# 设置时区为 Asia/Shanghai
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

# 将 Maven 构建的 JAR 包复制到容器内
COPY target/imags-lib-1.0-SNAPSHOT.jar /app.jar

# 暴露 8123 端口
EXPOSE 8123

# 容器启动命令，支持通过 JAVA_OPTS 传递 JVM 参数
ENTRYPOINT ["sh", "-c", "java -jar $JAVA_OPTS /app.jar"]
