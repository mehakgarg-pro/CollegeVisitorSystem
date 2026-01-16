@echo off
title College Visitor Entry System
color 0B

echo ========================================
echo    COLLEGE VISITOR ENTRY SYSTEM
echo ========================================
echo.

echo Step 1: Cleaning previous compilation...
if exist bin rmdir /s /q bin
mkdir bin >nul 2>nul

echo Step 2: Compiling source files...
echo.

javac -d bin -cp ".;lib\mysql-connector-j-9.5.0.jar" src\model\Visitor.java
if %ERRORLEVEL% NEQ 0 goto :error

javac -d bin -cp "bin" src\db\DBConnection.java
if %ERRORLEVEL% NEQ 0 goto :error

javac -d bin -cp "bin;lib\mysql-connector-j-9.5.0.jar" src\dao\VisitorDAO.java
if %ERRORLEVEL% NEQ 0 goto :error

javac -d bin -cp "bin;lib\mysql-connector-j-9.5.0.jar" src\ui\SplashScreen.java
if %ERRORLEVEL% NEQ 0 goto :error

javac -d bin -cp "bin;lib/mysql-connector-j-9.5.0.jar" src\ui\VisitorForm.java
if %ERRORLEVEL% NEQ 0 goto :error

javac -d bin -cp "bin;lib/mysql-connector-j-9.5.0.jar" src\Main.java
if %ERRORLEVEL% NEQ 0 goto :error

echo.
echo ========================================
echo    COMPILATION SUCCESSFUL!
echo ========================================
echo.
echo Step 3: Starting application...
echo.

java -cp "bin;lib/mysql-connector-j-9.5.0.jar" Main

echo.
echo ========================================
echo    APPLICATION CLOSED
echo ========================================
pause
exit /b 0

:error
echo.
echo ========================================
echo    COMPILATION FAILED!
echo ========================================
echo Please check the error messages above.
echo.
pause
exit /b 1
