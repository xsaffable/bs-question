#!/bin/bash
#Parameters:
#JVM_OPTS DEBUG_PORT
if [ ! -d "$JAVA_HOME" ]; then
  echo "not set JAVA_HOME in Environment!"
  # JAVA_HOME=/usr/local/java
  JAVA_HOME=/home/jdk
fi

APP_CLASS_PATH=$BASE_DEPLOY_HOME/classes
BASE_APP_HOME=$BASE_DEPLOY_HOME/lib

echo `date` >> $STD_LOG
echo `date` >> $ERR_LOG

APP_LIB="$BASE_APP_HOME"
APP_LIB_JARS=`ls $APP_LIB|grep .jar|awk '{print "'$APP_LIB'/"$0}'|tr "\n" ":"`

EXT_LIB="$JAVA_HOME/jre/lib/ext"
EXT_LIB_JARS=`ls $EXT_LIB|grep .jar|awk '{print "'$EXT_LIB'/"$0}'|tr "\n" ":"`

BASE_CLASS_PATH=$APP_CLASS_PATH:$APP_LIB_JARS:$EXT_LIB_JARS

DEBUG_PORT=9526
#JAVA_DEBUG_OPT=" -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=$DEBUG_PORT,server=y,suspend=n"
JAVA_DEBUG_OPT=""
JAVA_OPTS=" -DappName=$APP_NAME $APP_OPTS -Djava.awt.headless=true -Dspring.profiles.active=$cluster -Djava.net.preferIPv4Stack=true -Duser.timezone=$TIMEZONE -cp $BASE_CLASS_PATH "

JAVA="$JAVA_HOME/bin/java $JAVA_OPTS $JAVA_DEBUG_OPT"
export JAVA_HOME JAVA
