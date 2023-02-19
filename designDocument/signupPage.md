# SignUp Page

**URL** : `/api/v1/signup/`

**Method** : `POST`

**Auth required** : NO

**Data constraints**

```json
{
    "username": "[unique username]",
    "password": "[password in plain text or alphanumeric]"
}
```

**Data example**

```json
{
    "username": "uniquename",
    "password": "uniquepass"
}
```

## Success Response

**Code** : `200 OK`

**Content example**

```json
{
    "non_field_success_massage": [

        "SignUp successful"
    ]
}
```

## Error Response

**Condition** : If 'username' is not unique.

**Code** : `200 `

**Content** :

```json
{
    "non_field_errors": [
        "SignUp failed"
    ]
}

**Code** : `400/500/404/UNKNOWN`

**Content** :

```json
{
    "non_field_errors": [
        "SignUp failed"
    ]
}
```
