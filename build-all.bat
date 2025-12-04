@echo off
echo ====================================
echo Building All Services...
echo ====================================

echo Building all services with Gradle...
call gradlew.bat clean build -x test

echo.
echo Building Docker images...
docker-compose build

echo.
echo ====================================
echo Build completed successfully!
echo ====================================
echo ====================================
