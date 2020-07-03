#!/bin/bash
BASE_DEPLOY_HOME=$(cd "$(dirname "$0")"/..; pwd)

if [ -z $1 ];then
    echo "need cluster params!"
    exit 1
fi
#
cluster=$1
APP_NAME=$(basename "$BASE_DEPLOY_HOME")
###根据线上和线下环境设置jvm参数和log日志路径
if [ $1 = "stable" -o $1 = "test" -o $1 = "dev" ]; then
    APP_OPTS=" -Xms512m -Xmx512m -XX:MaxPermSize=128m "
    LOG_HOME="$BASE_DEPLOY_HOME/logs/${APP_NAME}"
    TIMEZONE='GMT+08'
else
    #APP_OPTS=" -Xmx4g -Xms4g -Xmn600m -XX:PermSize=256m -XX:MaxPermSize=256m -Xss256k -verbose:gc -XX:+UseParNewGC -XX:ParallelGCThreads=5 -XX:+DisableExplicitGC -XX:+UseConcMarkSweepGC -XX:+CMSPermGenSweepingEnabled  -XX:+CMSClassUnloadingEnabled -XX:+CMSParallelRemarkEnabled -XX:+UseCMSCompactAtFullCollection -XX:+UseCompressedOops  -XX:+PrintGCDateStamps -XX:+PrintGCDetails -XX:+PrintGCApplicationConcurrentTime -XX:+PrintGCApplicationStoppedTime  "
    #APP_OPTS=" -Xmx8g -Xms8g -Xmn3g -XX:PermSize=256m -XX:MaxPermSize=256m -Xss256k -verbose:gc -XX:+UseParNewGC -XX:ParallelGCThreads=10 -XX:+DisableExplicitGC -XX:+UseConcMarkSweepGC -XX:+CMSPermGenSweepingEnabled  -XX:+CMSClassUnloadingEnabled -XX:+CMSParallelRemarkEnabled -XX:+UseCMSCompactAtFullCollection -XX:+UseCompressedOops  -XX:+PrintGCDateStamps -XX:+PrintGCDetails -XX:+PrintGCApplicationConcurrentTime -XX:+PrintGCApplicationStoppedTime  "
    APP_OPTS=" -Xmx2g -Xms2g -Xmn600m -XX:MetaspaceSize=256m -XX:MaxMetaspaceSize=256m -Xss256k -verbose:gc -XX:+UseParNewGC -XX:ParallelGCThreads=10 -XX:+DisableExplicitGC -XX:+UseConcMarkSweepGC -XX:+CMSPermGenSweepingEnabled  -XX:+CMSClassUnloadingEnabled -XX:+CMSParallelRemarkEnabled -XX:+UseCMSCompactAtFullCollection -XX:+UseCompressedOops  -XX:+PrintGCDateStamps -XX:+PrintGCDetails -XX:+PrintGCApplicationConcurrentTime -XX:+PrintGCApplicationStoppedTime  "
    LOG_HOME="$BASE_DEPLOY_HOME/logs/${APP_NAME}"
    TIMEZONE='GMT+08'
fi

##创建log目录
if [ ! -d "$LOG_HOME" ]; then
    mkdir -p "$LOG_HOME"
fi
STD_LOG=$LOG_HOME/deploy-out.log
ERR_LOG=$LOG_HOME/deploy-err.log

DEBUG_PORT=80801
. $BASE_DEPLOY_HOME/bin/base.sh

##启动服务
nohup $JAVA WebApplication 1>>$STD_LOG 2>>$ERR_LOG &

sleep 7s

pid=$(ps -ef|grep java|grep "appName=${APP_NAME} "|awk '{print $2}')
if [ -z $pid ]
then
    echo "$APP_NAME start failed !!!"
else
    echo "$APP_NAME process start success !!!"
    echo "new pid is $pid"
    nowdate=`date "+%Y-%m-%d %X"`
    echo "[pid:$pid] [time:$nowdate] [state:start]" >> $BASE_DEPLOY_HOME/pid
fi