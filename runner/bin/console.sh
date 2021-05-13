#!/bin/sh

#JAVA_HOME=
JAVA_OPTS="-Djava.net.preferIPv4Stack=true -Djava.io.tmpdir=./temp"

APP_HOME=$0
APP_HOME=`dirname ${APP_HOME}`
export APP_HOME=`cd "${APP_HOME}/.."; pwd`
echo APP_HOME=${APP_HOME}
cd ${APP_HOME}

mkdir temp

exec "${JAVA_HOME}/bin/java" ${JAVA_OPTS} -cp "lib/*:conf" com.sic.sysmanage.SysManageApplication 

