@echo off
echo Starting API Verification...

echo.
echo 1. Creating Order...
curl -X POST http://localhost:8080/orders ^
-H "Content-Type: application/json" ^
-d "{\"orderDate\": \"2023-10-27\", \"customerId\": 1, \"shippingContactMechId\": 1, \"billingContactMechId\": 2, \"orderItems\": [{\"productId\": 1, \"quantity\": 2, \"status\": \"CREATED\"}, {\"productId\": 2, \"quantity\": 1, \"status\": \"CREATED\"}]}"
echo.

echo.
echo 2. Retrieving Order 1...
curl -X GET http://localhost:8080/orders/1
echo.

echo.
echo 3. Updating Order Item (Order 1, Seq 2) - Change Quantity of Jeans to 2...
curl -X PUT http://localhost:8080/orders/1/items/2 ^
-H "Content-Type: application/json" ^
-d "{\"quantity\": 2, \"status\": \"UPDATED\"}"
echo.

echo.
echo 4. Adding Order Item - Sneakers...
curl -X POST http://localhost:8080/orders/1/items ^
-H "Content-Type: application/json" ^
-d "{\"productId\": 3, \"quantity\": 1, \"status\": \"ADDED\"}"
echo.

echo.
echo 5. Deleting Order Item (Order 1, Seq 1) - T-Shirt...
curl -X DELETE http://localhost:8080/orders/1/items/1
echo.

echo.
echo 6. Deleting Order 1...
curl -X DELETE http://localhost:8080/orders/1
echo.

echo.
echo Verification Completed.
pause
