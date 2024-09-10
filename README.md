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
