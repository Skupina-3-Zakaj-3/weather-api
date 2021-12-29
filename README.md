# Weather-api microservice

## Build the image

```bash
docker build -t weather-api .
```

## Create network for all our microservices

```bash
docker network create rso
```

## Run the container in network

```bash
docker run -d -p 8086:8086 --name weather-api --network="rso" -e APPPROPERTIES_WEATHERAPI_ACCESSKEY=<API_KEY_FROM_RAPID_API> weather-api
```


## Run the container from Docker hub in network

```bash
docker run -d -p 8086:8086 --name weather-api --network="rso" -e APPPROPERTIES_WEATHERAPI_ACCESSKEY=<API_KEY_FROM_RAPID_API> anzeha/weather-api:latest
```