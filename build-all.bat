@echo off
echo ====================================
echo Building all microservices...
echo ====================================

cd product-service
echo Building product-service...
call gradlew.bat clean build -x test
cd ..

cd recommendation-service
echo Building recommendation-service...
call gradlew.bat clean build -x test
cd ..

cd review-service
echo Building review-service...
call gradlew.bat clean build -x test
cd ..

cd product-composite-service
echo Building product-composite-service...
call gradlew.bat clean build -x test
cd ..

echo ====================================
echo All services built successfully!
echo ====================================
