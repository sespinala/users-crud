@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  users-crud startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Add default JVM options here. You can also use JAVA_OPTS and USERS_CRUD_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto init

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto init

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:init
@rem Get command-line arguments, handling Windows variants

if not "%OS%" == "Windows_NT" goto win9xME_args

:win9xME_args
@rem Slurp the command line arguments.
set CMD_LINE_ARGS=
set _SKIP=2

:win9xME_args_slurp
if "x%~1" == "x" goto execute

set CMD_LINE_ARGS=%*

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\users-crud-1.0.0-SNAPSHOT.jar;%APP_HOME%\lib\vertx-lang-kotlin-coroutines-3.8.1.jar;%APP_HOME%\lib\kmongo-async-3.11.0.jar;%APP_HOME%\lib\kmongo-coroutine-3.11.0.jar;%APP_HOME%\lib\kmongo-coroutine-core-3.11.0.jar;%APP_HOME%\lib\kotlinx-coroutines-reactive-1.2.2.jar;%APP_HOME%\lib\kotlinx-coroutines-core-1.3.0-gradle.jar;%APP_HOME%\lib\vertx-web-api-contract-3.5.4.jar;%APP_HOME%\lib\kmongo-3.11.0.jar;%APP_HOME%\lib\vertx-web-3.8.1.jar;%APP_HOME%\lib\vertx-lang-kotlin-3.8.1.jar;%APP_HOME%\lib\kotlin-stdlib-jdk8-1.3.30.jar;%APP_HOME%\lib\klaxon-5.0.1.jar;%APP_HOME%\lib\vertx-web-common-3.8.1.jar;%APP_HOME%\lib\vertx-auth-common-3.8.1.jar;%APP_HOME%\lib\vertx-core-3.8.1.jar;%APP_HOME%\lib\kmongo-async-core-3.11.0.jar;%APP_HOME%\lib\kmongo-jackson-mapping-3.11.0.jar;%APP_HOME%\lib\kmongo-core-3.11.0.jar;%APP_HOME%\lib\kotlin-stdlib-jdk7-1.3.30.jar;%APP_HOME%\lib\kmongo-async-shared-3.11.0.jar;%APP_HOME%\lib\kmongo-property-3.11.0.jar;%APP_HOME%\lib\kmongo-shared-3.11.0.jar;%APP_HOME%\lib\kmongo-id-jackson-3.11.0.jar;%APP_HOME%\lib\jackson-module-loader-0.1.0.jar;%APP_HOME%\lib\jackson-module-kotlin-2.9.9.jar;%APP_HOME%\lib\kmongo-id-3.11.0.jar;%APP_HOME%\lib\kreflect-1.0.0.jar;%APP_HOME%\lib\kotlin-reflect-1.3.41.jar;%APP_HOME%\lib\kmongo-data-3.11.0.jar;%APP_HOME%\lib\kotlin-stdlib-1.3.50.jar;%APP_HOME%\lib\kotlin-stdlib-common-1.3.50.jar;%APP_HOME%\lib\json-schema-validator-0.1.13.jar;%APP_HOME%\lib\swagger-parser-2.0.0-rc3.jar;%APP_HOME%\lib\swagger-parser-v2-converter-2.0.0-rc3.jar;%APP_HOME%\lib\swagger-parser-v3-2.0.0-rc3.jar;%APP_HOME%\lib\swagger-core-2.0.0-rc4.jar;%APP_HOME%\lib\swagger-compat-spec-parser-1.0.34.jar;%APP_HOME%\lib\swagger-parser-1.0.34.jar;%APP_HOME%\lib\swagger-core-1.5.18.jar;%APP_HOME%\lib\jackson-dataformat-yaml-2.9.6.jar;%APP_HOME%\lib\vertx-bridge-common-3.8.1.jar;%APP_HOME%\lib\netty-handler-proxy-4.1.39.Final.jar;%APP_HOME%\lib\netty-codec-http2-4.1.39.Final.jar;%APP_HOME%\lib\netty-codec-http-4.1.39.Final.jar;%APP_HOME%\lib\netty-handler-4.1.39.Final.jar;%APP_HOME%\lib\netty-resolver-dns-4.1.39.Final.jar;%APP_HOME%\lib\netty-codec-socks-4.1.39.Final.jar;%APP_HOME%\lib\netty-codec-dns-4.1.39.Final.jar;%APP_HOME%\lib\netty-codec-4.1.39.Final.jar;%APP_HOME%\lib\netty-transport-4.1.39.Final.jar;%APP_HOME%\lib\netty-buffer-4.1.39.Final.jar;%APP_HOME%\lib\netty-resolver-4.1.39.Final.jar;%APP_HOME%\lib\netty-common-4.1.39.Final.jar;%APP_HOME%\lib\json-patch-1.6.jar;%APP_HOME%\lib\json-schema-validator-2.2.8.jar;%APP_HOME%\lib\json-schema-core-1.2.8.jar;%APP_HOME%\lib\jackson-coreutils-1.8.jar;%APP_HOME%\lib\jackson-databind-2.9.9.3.jar;%APP_HOME%\lib\jackson-core-2.9.9.jar;%APP_HOME%\lib\bson4jackson-2.9.2.jar;%APP_HOME%\lib\annotations-13.0.jar;%APP_HOME%\lib\slf4j-ext-1.7.25.jar;%APP_HOME%\lib\swagger-models-1.5.18.jar;%APP_HOME%\lib\slf4j-api-1.7.25.jar;%APP_HOME%\lib\commons-lang3-3.7.jar;%APP_HOME%\lib\commons-io-2.4.jar;%APP_HOME%\lib\snakeyaml-1.18.jar;%APP_HOME%\lib\mongodb-driver-3.11.0.jar;%APP_HOME%\lib\mongodb-driver-reactivestreams-1.12.0.jar;%APP_HOME%\lib\swagger-parser-core-2.0.0-rc3.jar;%APP_HOME%\lib\swagger-models-2.0.0-rc4.jar;%APP_HOME%\lib\jackson-annotations-2.9.3.jar;%APP_HOME%\lib\reactive-streams-1.0.2.jar;%APP_HOME%\lib\mongodb-driver-async-3.11.0.jar;%APP_HOME%\lib\mongodb-driver-core-3.11.0.jar;%APP_HOME%\lib\bson-3.11.0.jar;%APP_HOME%\lib\httpclient-4.5.2.jar;%APP_HOME%\lib\swagger-annotations-2.0.0-rc4.jar;%APP_HOME%\lib\validation-api-1.1.0.Final.jar;%APP_HOME%\lib\uri-template-0.9.jar;%APP_HOME%\lib\guava-20.0.jar;%APP_HOME%\lib\mailapi-1.4.3.jar;%APP_HOME%\lib\joda-time-2.9.7.jar;%APP_HOME%\lib\libphonenumber-8.0.0.jar;%APP_HOME%\lib\msg-simple-1.1.jar;%APP_HOME%\lib\btf-1.2.jar;%APP_HOME%\lib\jsr305-3.0.1.jar;%APP_HOME%\lib\jopt-simple-5.0.3.jar;%APP_HOME%\lib\httpcore-4.4.4.jar;%APP_HOME%\lib\commons-logging-1.2.jar;%APP_HOME%\lib\commons-codec-1.9.jar;%APP_HOME%\lib\swagger-annotations-1.5.18.jar;%APP_HOME%\lib\rhino-1.7R4.jar;%APP_HOME%\lib\activation-1.1.jar

@rem Execute users-crud
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %USERS_CRUD_OPTS%  -classpath "%CLASSPATH%" io.vertx.core.Launcher %CMD_LINE_ARGS%

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable USERS_CRUD_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%USERS_CRUD_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
