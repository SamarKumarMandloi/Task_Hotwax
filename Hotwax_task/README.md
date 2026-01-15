# Hotwax Task: RESTful API Development

## Overview
This project implements a RESTful API for an e-commerce order management system using Java (Spring Boot) and MySQL.

## Technologies
- Java 17
- Spring Boot 3
- Spring Data JPA
- MySQL

## Setup
1. Configure database connection in `src/main/resources/application.properties`.
2. Run `HotwaxTaskApplication`.
3. The application will automatically create tables (`schema.sql`) and insert sample data (`data.sql`).

## API Endpoints
- `POST /orders`: Create Order
- `GET /orders/{id}`: Get Order
- `PUT /orders/{id}`: Update Order
- `DELETE /orders/{id}`: Delete Order
- `POST /orders/{id}/items`: Add Item
- `PUT /orders/{id}/items/{seqId}`: Update Item
- `DELETE /orders/{id}/items/{seqId}`: Delete Item
