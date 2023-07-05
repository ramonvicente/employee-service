# Employee Service
This project is an application that handle with employee information of a company.

## SetUp

## Endpoints
- For information about the endpoints access the swagger documentation: http://localhost:8080/swagger-ui/index.html

### create employee
```
curl --location 'http://localhost:8080/api/v1/employees' \
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
curl --location --request PUT 'http://localhost:8080/api/v1/employees/832b938e-b193-416a-9f33-776b266f86c1' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email": "email@test.com",
    "firstName": "firstName",
    "lastName": "lastname",
    "birthday": "1992-12-12",
    "hobbies": ["hobby1", "other"]
}'
```

### Delete employee
```
curl --location --request DELETE 'http://localhost:8080/api/v1/employees/832b938e-b193-416a-9f33-776b266f86c1'
```

## Comments

