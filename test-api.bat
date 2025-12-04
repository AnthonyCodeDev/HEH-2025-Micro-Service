@echo off
echo ====================================
echo Test de la couche de persistance
echo ====================================
echo.

echo 1. Creation d'un produit...
curl -X POST http://localhost:7002/product ^
  -H "Content-Type: application/json" ^
  -d "{\"productId\":1,\"name\":\"Laptop Dell\",\"weight\":2000}"
echo.
echo.

timeout /t 2 /nobreak >nul

echo 2. Creation d'une recommendation...
curl -X POST http://localhost:7003/recommendations ^
  -H "Content-Type: application/json" ^
  -d "{\"productId\":1,\"recommendationId\":1,\"author\":\"Alice\",\"rate\":5,\"content\":\"Excellent produit\"}"
echo.
echo.

timeout /t 2 /nobreak >nul

echo 3. Creation d'un review...
curl -X POST http://localhost:7004/review ^
  -H "Content-Type: application/json" ^
  -d "{\"productId\":1,\"reviewId\":1,\"author\":\"Bob\",\"subject\":\"Tres bon\",\"content\":\"Je suis tres satisfait\"}"
echo.
echo.

timeout /t 2 /nobreak >nul

echo 4. Recuperation du produit...
curl http://localhost:7002/product/1
echo.
echo.

echo 5. Recuperation des recommendations...
curl http://localhost:7003/recommendations/1
echo.
echo.

echo 6. Recuperation des reviews...
curl "http://localhost:7004/review?productId=1"
echo.
echo.

echo 7. Recuperation du produit composite...
curl http://localhost:7001/product-composite/1
echo.
echo.

echo ====================================
echo Tests termines!
echo ====================================
