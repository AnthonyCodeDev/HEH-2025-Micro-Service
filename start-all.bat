@echo off
echo ====================================
echo Starting All Services with Docker...
echo ====================================

docker-compose up -d

echo.
echo ====================================
echo All services are starting!
echo ====================================
echo.
echo Services:
echo - Eureka Server: http://localhost:8761
echo - Product Service: http://localhost:7002
echo - Recommendation Service: http://localhost:7003
echo - Review Service: http://localhost:7004
echo - Product Composite: http://localhost:7001
echo.
echo Infrastructure:
echo - MongoDB: localhost:27017
echo - MySQL: localhost:3306
echo - Kafka: localhost:9092
echo - Zookeeper: localhost:2181
echo.
echo Use 'docker-compose logs -f' to see logs
echo Use 'stop-all.bat' to stop all services
echo ====================================
