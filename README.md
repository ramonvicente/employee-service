# Employee Service
This project is an application that handle with employee information of a company.

## SetUp and Run
in order to run the application, use the terminal to run docker-compose.yml to set up database and kafka then run the application with gradle: 
- ```cd docker```
- ```docker-compose up -d```
- ```./gradlew bootRun```

## Endpoints
- For information about the endpoints access the swagger documentation: http://localhost:8080/swagger-ui/index.html

### create employee
```
curl --location 'http://localhost:8080/api/v1/employees' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0IiwiaWF0IjoxNjg4NTkxMjczLCJleHAiOjE2ODg2Nzc2NzN9.zqRQE03hdfUCl5FaryZhWJvspcm-aIVsS7s4dTgBc0A' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email": "email@test.com",
    "firstName": "firstName",
    "lastName": "lastname",
    "birthday": "1992-02-02",
    "hobbies": ["hobby1"]
}'
```

### get all employees
```
curl --location 'http://localhost:8080/api/v1/employees'
```

### get employee
```
curl --location 'http://localhost:8080/api/v1/employees/832b938e-b193-416a-9f33-776b266f86c1'
```

### update employee
```
curl --location --request PUT 'http://localhost:8080/api/v1/employees/fc416b3f-005f-4b27-93c4-9be9720ea86c' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0IiwiaWF0IjoxNjg2MzM4Nzk0LCJleHAiOjE2ODY0MjUxOTR9.1-MolpIjGDhkvZ52HaVTpFn_ih7lsSzYvZFDLpcNdVw' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email": "email@test.com",
    "firstName": "firstName",
    "lastName": "lastname",
    "birthday": "1992-12-12",
    "hobbies": ["hobby5", "other"]
}'
```

### Delete employee
```
curl --location --request DELETE 'http://localhost:8080/api/v1/employees/27a6583f-bd97-4af6-81a4-ba2158f648a5' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0IiwiaWF0IjoxNjg4NTkxMjczLCJleHAiOjE2ODg2Nzc2NzN9.zqRQE03hdfUCl5FaryZhWJvspcm-aIVsS7s4dTgBc0A'
```
