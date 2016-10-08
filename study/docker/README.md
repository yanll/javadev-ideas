# Docker學習筆記



Root用户启动Docker：
service docker start/stop



查看镜像：
docker images

启动Mysql：
docker run --name docker_mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -d mysql:5.7
docker run --name docker_mysql_i -p 3301:3306 -e MYSQL_ROOT_PASSWORD=root -d mysql:5.7
docker run --name docker_mysql_ii -p 3302:3306 -e MYSQL_ROOT_PASSWORD=root -d mysql:5.7
docker run --name docker_mysql_iii -p 3303:3306 -e MYSQL_ROOT_PASSWORD=root -d mysql:5.7
docker run --name docker_mysql_iv -p 3304:3306 -e MYSQL_ROOT_PASSWORD=root -d mysql:5.7
docker run --name docker_mysql_v -p 3305:3306 -e MYSQL_ROOT_PASSWORD=root -d mysql:5.7


查看容器PID
docker inspect --format "{{ .State.Pid }}" 容器ID

进入容器：
nsenter --target $PID --mount --uts --ipc --net --pid


下载.bashrc_docker，并将内容放到 .bashrc 中。

$ wget -P ~ https://github.com/yeasy/docker_practice/raw/master/_local/.bashrc_docker;
$ echo "[ -f ~/.bashrc_docker ] && . ~/.bashrc_docker" >> ~/.bashrc; source ~/.bashrc

例如 docker-pid 可以获取某个容器的 PID；而 docker-enter 可以进入容器或直接在容器内执行命令。

$ echo $(docker-pid <container>)
$ docker-enter <container> ls


删除一个容器：docker rm 容器ID
删除一个镜像：docker rmi 镜像ID





启动OS镜像：
docker run -i -t -v /usr/local/software/:/mnt/software/ centos:7.2.1511 /bin/bash




redis 启动：
docker run --name docker_dao_redis_master_init -p -t -i -d 6379:6379 dao-redis:master-init 
docker run --name docker_redis -p -t -i -d 6379:6379 redis:latest

mongodb 启动：
docker run --name docker_mongo -p -t -i -d 27017:27017 mongo:latest






啓動容器
docker run --name docker_zookeeper -p 21818:2181 -d jplock/zookeeper:3.4.6

进入容器
docker exec -it docker_zookeeper /bin/bash

