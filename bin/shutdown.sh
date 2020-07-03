#!/bin/bash
echo "shutting down"
BASE_DEPLOY_HOME=$(cd "$(dirname "$0")"/..; pwd)
APP_NAME=$(basename "$BASE_DEPLOY_HOME")
pid=$(ps -ef|grep java|grep "appName=${APP_NAME} "|awk '{print $2}')
if [ -z $pid ]
then
    echo "$APP_NAME process had already been shutdown "
else
    echo "killing pid $pid";
    kill -9  $pid
    echo "$APP_NAME process has been shutdown"
    nowdate=`date "+%Y-%m-%d %X"`
    echo "[pid:$pid] [time:$nowdate] [state:shutdown]" >> $BASE_DEPLOY_HOME/pid
fi