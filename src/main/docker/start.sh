#!/bin/sh

# 创建必要的文件夹
mkdir -p /usr/local/projects/imags-lib-all/{imags-lib-backend,mysql-db,redis-cache}

# 根据 SERVICE 环境变量启动不同服务
case "$SERVICE" in
  app)
    cd /usr/local/projects/imags-lib-all/imags-lib-backend
    exec java -jar $JAVA_OPTS ./imags-lib.jar
    ;;

  mysql)
    cd /usr/local/projects/imags-lib-all/mysql-db
    exec docker-entrypoint.sh mysqld
    ;;

  redis)
    cd /usr/local/projects/imags-lib-all/redis-cache
    exec redis-server
    ;;

  *)
    echo "❌ Unknown service type. Please set SERVICE=app, SERVICE=mysql, or SERVICE=redis."
    exit 1
    ;;
esac
