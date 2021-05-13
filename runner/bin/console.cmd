@echo off
setlocal enabledelayedexpansion

rem set JAVA_HOME=
set JAVA_OPTS="-Djava.net.preferIPv4Stack=true -Djava.io.tmpdir=./temp"

set BIN_PATH=%~dp0
if "%BIN_PATH:~-1%" == "\" (
  set BIN_PATH=%BIN_PATH:~0,-1%
)
set APP_HOME=%BIN_PATH%\..
echo APP_HOME=%APP_HOME%
cd %APP_HOME%

mkdir temp

"%JAVA_HOME%/bin/java" %JAVA_OPTS% -cp "lib\*;conf" com.sic.sysmanage.SysManageApplication 
