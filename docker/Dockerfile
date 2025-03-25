# 使用 OpenJDK 8 作为基础镜像
#FROM openjdk:8-jre
#
## 镜像维护者信息（推荐使用 LABEL）
#LABEL maintainer="righting"
#
## 设置时区为 Asia/Shanghai
#RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
#
## 将 Maven 构建的 JAR 包复制到容器内
#COPY target/imags-lib-1.0-SNAPSHOT.jar /imags-lib.jar
#
## 暴露 8123 端口
#EXPOSE 8123
#
## 容器启动命令，支持通过 JAVA_OPTS 传递 JVM 参数
#ENTRYPOINT ["sh", "-c", "java -jar $JAVA_OPTS /imags-lib.jar"]



FROM openjdk:8-jre

# 镜像维护者信息
LABEL maintainer="righting"

# 设置时区为 Asia/Shanghai
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

# 创建并进入 /usr/local/projects/imags-lib-all/ 目录
WORKDIR /usr/local/projects/imags-lib-all/

# 复制 JAR 包到指定目录
#RUN mkdir -p imags-lib-backend mysql-db redis-cache

#COPY ./target/imags-lib-1.0-SNAPSHOT.jar imags-lib-backend/imags-lib.jar
COPY target/imags-lib-1.0-SNAPSHOT.jar /imags-lib.jar

# 暴露应用、MySQL、Redis 端口
EXPOSE 8123 3307 6380
#EXPOSE 8123

# 启动脚本（无需额外创建文件）
# ENTRYPOINT ["sh", "-c", "
#   mkdir -p /usr/local/projects/imags-lib-all/{imags-lib-backend,mysql-db,redis-cache} && \
#   if [ \"$SERVICE\" = 'app' ]; then
#     cd /usr/local/projects/imags-lib-all/imags-lib-backend && \
#     java -jar $JAVA_OPTS ./imags-lib.jar;
#   elif [ \"$SERVICE\" = 'mysql' ]; then
#     cd /usr/local/projects/imags-lib-all/mysql-db && \
#     docker-entrypoint.sh mysqld;
#   elif [ \"$SERVICE\" = 'redis' ]; then
#     cd /usr/local/projects/imags-lib-all/redis-cache && \
#     redis-server;
#   else
#     echo 'Unknown service type. Please set SERVICE=app, SERVICE=mysql, or SERVICE=redis';
#     exit 1;
#   fi
# "
# ]
ENTRYPOINT ["start.sh"]
