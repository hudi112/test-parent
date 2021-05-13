#!/bin/sh
usage="Usage: sys-manage-daemon.sh (start | stop)"
start_stop=$1

APP_HOME=$0
APP_HOME=`dirname ${APP_HOME}`
export APP_HOME=`cd "${APP_HOME}/.."; pwd`
echo APP_HOME=${APP_HOME}
cd ${APP_HOME}

PID_HOME="pid"
pid=$PID_HOME/sys-manage.pid

case $start_stop in

  (start)
    [ -w "logs" ] || mkdir -p logs
    [ -w "$PID_HOME" ] || mkdir -p "$PID_HOME"
    if [ -f $pid ]; then
      if kill -0 `cat $pid` > /dev/null 2>&1; then
        echo sys-manage running as process `cat $pid`. Stop it first.
        exit 1
      fi
    fi
    
    echo starting sys-manage.
    #nohup java -jar lib/*.jar > logs/sys-manage.log 2>&1 < /dev/null &
    nohup bin/console.sh > logs/sys-manage.out 2>&1 < /dev/null &
    echo $! > $pid
    sleep 5
    if ! ps -p $! > /dev/null ; then
      echo "Start sys-manage failed."
      exit 1
    fi
  ;;

  (stop)
    if [ -f $pid ]; then
      target_pid=`cat $pid`
      if kill -0 $target_pid > /dev/null 2>&1; then
        echo Stopping sys-manage.
        kill $target_pid
        sleep 10
        if kill -0 $target_pid > /dev/null 2>&1; then
          echo sys-manage did not stop gracefully after 10 secdons. killing with -9.
          kill -9 $target_pid
        fi
      else
        echo no sys-manage to stop.
      fi
      rm -f $pid
    else
      echo no sys-manage to stop.
    fi
  ;;

  (*)
     echo $usage
     exit 1
  ;;

esac

