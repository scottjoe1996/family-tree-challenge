

to add a child to the family tree use the command: 
`curl -X POST -H “Content-Type: application/json” \ -d ‘{JSON_BODY}’ \ localhost:8080/person`

JSON_BODY example 
```
{
    "mother": {
        "id": "15519758-639c-4d6b-bd6d-f7cceec20c73",
        "motherId": "15519758-639c-4d6b-bd6d-f7cceec20c73",
        "fatherId": "15519758-639c-4d6b-bd6d-f7cceec20c73",
        "gender": "FEMALE"
    },
    "father": {
        "id": "8be4f2bc-4bf9-4c88-8655-3b5bdfdf623c",
        "motherId": "15519758-639c-4d6b-bd6d-f7cceec20c73",
        "fatherId": "15519758-639c-4d6b-bd6d-f7cceec20c73",
        "gender": "MALE"
    },
    "gender": "MALE"
    
}
```
