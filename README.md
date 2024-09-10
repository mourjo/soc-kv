# soc-kv

Start dependencies using

```bash 
docker compose up
```

Start the service using

```bash 
./mvnw clean compile exec:java  -Dexec.mainClass="soc.kv.web.Launcher"
```

## Using the service

```bash 
curl -s -XPUT localhost:8818/current_location/amsterdam | jq .
{
  "id": "5",
  "key": "current_location",
  "value": "amsterdam",
  "createdAt": "2024-09-09T16:56:45.416102+02:00",
  "updatedAt": "2024-09-09T16:56:45.416102+02:00"
}
```

```bash 
curl -s -XGET localhost:8818/current_location | jq .
{
  "id": "5",
  "key": "current_location",
  "value": "amsterdam",
  "createdAt": "2024-09-09T16:56:45.416102+02:00",
  "updatedAt": "2024-09-09T16:56:45.416102+02:00"
}
```


```bash
curl -s -XGET localhost:8818/current_location/history | jq .
{
  "entries": [
    {
      "key": "current_location",
      "value": "kolkata",
      "loggedAt": "2024-09-10T08:14:43.357701+02:00"
    },
    {
      "key": "current_location",
      "value": "india",
      "loggedAt": "2024-09-10T08:01:17.713349+02:00"
    },
    {
      "key": "current_location",
      "value": "amsterdam",
      "loggedAt": "2024-09-10T07:56:10.197624+02:00"
    }
  ]
}
```
