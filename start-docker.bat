@echo off
echo ====================================
echo Starting Docker Compose...
echo ====================================

docker-compose up --build -d

echo ====================================
echo Services are starting...
echo ====================================
echo.
echo You can check the status with: docker-compose ps
echo You can view logs with: docker-compose logs -f [service-name]
echo.
echo Services:
echo - Product Service: http://localhost:7002
echo - Recommendation Service: http://localhost:7003
echo - Review Service: http://localhost:7004
echo - Product Composite Service: http://localhost:7001
echo - Kafka: localhost:9092
echo - Zookeeper: localhost:2181
echo.
