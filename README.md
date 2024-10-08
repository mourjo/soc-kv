# soc-kv
This is a repository that implements the following functionalities. 

- It is a KV store
- Store string values against keys
- Persist key-values in the database
- History tracking of previous values
- Text-based search of keys


This repository is an example of how to separate concerns between controller, services and repositories - similar to 
[Presetation-Domain-Data layering](https://martinfowler.com/bliki/PresentationDomainDataLayering.html)

![](src/test/resources/presentation-domain-data-layering.png)


## Starting the web server
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

```curl 
curl -s -XGET localhost:8818/search\?q=hehe  | jq .
{
  "query": "hehe",
  "matches": []
}
```

