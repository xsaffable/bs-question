#!/bin/bash

BASE_RUN_HOME=$(cd "$(dirname "$0")"/.; pwd)

function start() {
#    machine=$(cat ./.deploy/service.cluster.txt)
#    service_name=$(cat ./.deploy/service.service_name.txt);
#
#    if [ -z $machine ];then
#        echo "service.cluster.txt not exist "
#        exit 1
#    fi
#    if [ -z $machine ];then
#        echo "service.service_name.txt not exist "
#        exit 1
#    fi
#
#    node=$machine-$service_name
#
#    cluster=`grep ${node} nodes.properties|cut -d'=' -f2`
    cluster=prod
    echo "start cluster:$cluster"
    sh $BASE_RUN_HOME/bin/shutdown.sh
    sleep 2s
    sh $BASE_RUN_HOME/bin/startup.sh $cluster
    sleep 2s
}

function stop() {
    sh $BASE_RUN_HOME/bin/shutdown.sh
}

action=$1
case $action in
    "start" )
        start
        ;;
    "stop" )
        stop
        ;;
    * )
        echo "unknown command"
        exit 1
        ;;
esac