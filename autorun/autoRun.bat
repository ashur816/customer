@echo off
cls
:start
cd ..
set APP_HOME=%cd%
copy %APP_HOME%\target\customer-1.0.jar %APP_HOME%\autorun\customer-1.0.jar
 java -Dfile.encoding=utf-8 -jar %APP_HOME%\autorun\customer-1.0.jar %c%
goto :start