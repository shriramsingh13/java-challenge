# Login Page

**URL** : `/api/v1/login/`

**Method** : `POST`

**Auth required** : NO

**Data constraints**

```json
{
    "username": "[valid username]",
    "password": "[password in plain text or numeric]"
}
```

**Data example**

```json
{
    "username": "testuser1",
    "password": "testpass1"
}
```

## Success Response

**Code** : `200 OK` 

**Content example**

```json
{
    "token": "testuser1_testpass1"
}
```
**Redirect to employee page**

## Error Response

**Condition** : If 'username' and 'password' combination is wrong.

**Code** : `400/500/404/UNKNOWN`

**Content** :

```json
{
    "non_field_errors": [
        "login failed"
    ]
}
```
