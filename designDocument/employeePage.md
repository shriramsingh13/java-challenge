# Show All Employee Details

Get the details such as id, name, salary and department of the all employee 
from the database 

**URL** : `/api/v1/employees`

**Method** : `GET`

**Auth required** : NO

**Permissions required** : None

## Success Response

**Code** : `200 OK`

**Content examples**

For a User with ID 1234 on the local database where that User has saved an
name, salary and department information.

```json
{
    "id": 1234,
    "name": "testUser1",
    "salary": "200000",
    "department": "Information Technology"
}
```

# Show Employee Details by id

Get the details such as id, name, salary and department of the all employee 
from the database 

**URL** : `/api/v1/employees/{id}`

**Method** : `GET`

**Auth required** : NO

**Permissions required** : None

## Success Response

**Code** : `200 OK`

**Content examples**

For a User with ID 4567 on the local database where that User has saved an
name, salary and department information.

```json
{
    "id": 4567,
    "name": "testUser2",
    "salary": "700000",
    "department": "Digital Art"
}
```

# Search Employee Details by id or Name

Get the details such as id, name, salary and department of Top 10 employees
from the database whose username matched or id matched with the search query

**URL** : `/api/v1/searchEmployees/`

**Method** : `POST`

**Auth required** : NO

**Permissions required** : None

**Resquest object example**

**ReqData constraints**

```json
{
    "id": "[must be number]",
    "name": "[plain text]"
}
```

**Data example for search with id**
```json
{
    "id": "1234",
    "name": null
}
```
**Data example for search with Name**
```json
{
    "id": null,
    "name": "testName1"
}
```

## Success Response

**Code** : `200 OK`

**Content examples**

For a User with ID 1234 on the local database where that User has saved an
name, salary and department information.

```json
{
    "id": 1234,
    "name": "testUser1",
    "salary": "700000",
    "department": "Maths"
}
```

Incase no data found for search query then result then empty list is return 
like below and message "No Data Found" will be shown
```json
{
    []
}
```

# Create Employee Details

Create/Insert the details such as id, name, salary and department of a employees
into the database

**URL** : `/api/v1/saveEmployee/`

**Method** : `POST`

**Auth required** : YES

**Permissions required** : YES

**Resquest object example**

**ReqData constraints**

```json
{
    "id": "[must be number]",
    "name": "[plain text]",
    "salary": "[plain text]",
    "department": "[plain text]"
}
```

**Request header must have authorization token**
```json
  headers: {"authorization": "token"  }
```
```text
  Please use "admin_admin" as master token to access this saveEmployee api
```

**Request sample postman request **
```json
{
	"info": {
		"_postman_id": "db3a0e2c-e9f4-46ab-a490-13551ee7ebb0",
		"name": "EmployeeApiDemo",
		"schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json",
		"_exporter_id": "25935803"
	},
	"item": [
		{
			"name": "http://localhost:8080/api/v1/saveEmployee",
			"protocolProfileBehavior": {
				"disabledSystemHeaders": {
					"content-type": true
				}
			},
			"request": {
				"method": "POST",
				"header": [
					{
						"key": "authorization",
						"value": "admin_admin",
						"type": "text"
					},
					{
						"key": "Content-Type",
						"value": "application/json",
						"type": "text"
					}
				],
				"body": {
					"mode": "raw",
					"raw": "{\r\n    \"id\": null,\r\n    \"name\": \"TestName55\",\r\n    \"salary\": 670000,\r\n    \"department\": \"IT\"\r\n}",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": {
					"raw": "http://localhost:8080/api/v1/saveEmployee",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"v1",
						"saveEmployee"
					]
				},
				"description": "Create employee data"
			},
			"response": []
		}
	]
}
```


## Success Response

**Code** : `200 OK`

**Content examples**

On successful creation of employee data response will be true

```json
{
    true
}
```

Incase any issue response will be false
```json
{
    false
}
```

# Update Employee Details By ID

Update the details such as name, salary and department of a employees
into the database by their ID

**URL** : `/api/v1/updateEmployee/`

**Method** : `PUT`

**Auth required** : YES

**Permissions required** : YES

**Resquest object example**

**ReqData constraints**

```json
{
    "id": "[must be number]",
    "name": "[plain text]",
    "salary": "[plain text]",
    "department": "[plain text]"
}
```
```text
   ID is important in this request, its is required to update employee data
```


**Request header must have authorization token**
```json
  headers: {"authorization": "token"  }
```
```text
  Please use "admin_admin" as master token to access this saveEmployee api
```

**Request sample postman request **
```json
{
	"info": {
		"_postman_id": "db3a0e2c-e9f4-46ab-a490-13551ee7ebb0",
		"name": "EmployeeApiDemo",
		"schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json",
		"_exporter_id": "25935803"
	},
	"item": [
		{
			"name": "http://localhost:8080/api/v1/updateEmployee",
			"protocolProfileBehavior": {
				"disabledSystemHeaders": {
					"content-type": true
				}
			},
			"request": {
				"method": "PUT",
				"header": [
					{
						"key": "authorization",
						"value": "admin_admin",
						"type": "text"
					},
					{
						"key": "Content-Type",
						"value": "application/json",
						"type": "text"
					}
				],
				"body": {
					"mode": "raw",
					"raw": "{\r\n    \"id\": 34,\r\n    \"name\": \"TestName34\",\r\n    \"salary\": 970000,\r\n    \"department\": \"IT Support\"\r\n}",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": {
					"raw": "http://localhost:8080/api/v1/updateEmployee",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"v1",
						"updateEmployee"
					]
				},
				"description": "Update employee data by id"
			},
			"response": []
		}
	]
}
```


## Success Response

**Code** : `200 OK`

**Content examples**

On successful update of employee data response will be true

```json
{
    true
}
```

Incase any issue response will be false
```json
{
    false
}
```

# Delete Employee Details ID

Delete the employee details by ID

**URL** : `/api/v1/updateEmployee/`

**Method** : `DELETE`

**Auth required** : YES

**Permissions required** : YES

**Resquest object example**

**ReqData constraints**

```json
{
    "id": "[must be number]"
}
```
```text
   ID is important in this request, its is required to delete employee data
```


**Request header must have authorization token**
```json
  headers: {"authorization": "token"  }
```
```text
  Please use "admin_admin" as master token to access this saveEmployee api
```

**Request sample postman request **
```json
{
	"info": {
		"_postman_id": "db3a0e2c-e9f4-46ab-a490-13551ee7ebb0",
		"name": "EmployeeApiDemo",
		"schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json",
		"_exporter_id": "25935803"
	},
	"item": [
		{
			"name": "http://localhost:8080/api/v1/deleteEmployees",
			"protocolProfileBehavior": {
				"disabledSystemHeaders": {
					"content-type": true
				}
			},
			"request": {
				"method": "DELETE",
				"header": [
					{
						"key": "authorization",
						"value": "admin_admin",
						"type": "text"
					},
					{
						"key": "Content-Type",
						"value": "application/json",
						"type": "text"
					}
				],
				"body": {
					"mode": "raw",
					"raw": "{\r\n    \"id\": 34   \r\n}",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": {
					"raw": "http://localhost:8080/api/v1/updateEmployee",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"v1",
						"deleteEmployees"
					]
				},
				"description": "Update employee data"
			},
			"response": []
		}
	]
}
```


## Success Response

**Code** : `200 OK`

**Content examples**

On successful deleteion of employee data response will be true

```json
{
    true
}
```

Incase any issue response will be false
```json
{
    false
}
```
