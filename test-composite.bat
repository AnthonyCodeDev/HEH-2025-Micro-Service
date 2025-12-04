@echo off
echo ====================================
echo Test du service composite complet
echo ====================================
echo.

echo 1. Creation d'un produit composite...
curl -X POST http://localhost:7001/product-composite ^
  -H "Content-Type: application/json" ^
  -d "{\"product\":{\"productId\":2,\"name\":\"MacBook Pro\",\"weight\":1800},\"reviews\":[{\"productId\":2,\"reviewId\":1,\"author\":\"Jean\",\"subject\":\"Super\",\"content\":\"Excellent ordinateur\"}],\"recommendations\":[{\"productId\":2,\"recommendationId\":1,\"author\":\"Marie\",\"rate\":5,\"content\":\"Je recommande fortement\"}]}"
echo.
echo.

timeout /t 3 /nobreak >nul

echo 2. Recuperation du produit composite...
curl http://localhost:7001/product-composite/2
echo.
echo.

timeout /t 2 /nobreak >nul

echo 3. Suppression du produit composite...
curl -X DELETE http://localhost:7001/product-composite/2
echo.
echo.

timeout /t 2 /nobreak >nul

echo 4. Verification de la suppression (devrait retourner une erreur)...
curl http://localhost:7001/product-composite/2
echo.
echo.

echo ====================================
echo Tests du composite termines!
echo ====================================
