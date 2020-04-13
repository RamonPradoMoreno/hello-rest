# hello-rest

Simple hello rest service implemented in basic java to be deployed in a wildfly server inside a container.

This image is public in [docker hub](https://hub.docker.com/r/rpardom/simple_rest/tags)

## Microservice

The service has a *hello* endpoint that answers with the current time.

The artifact is a simple `.ear` that is automatically published in a *Wildfly* application container.

You can try it with:

```bash
curl localhost:8080/hello -v
```



## Docker image management

**Recommended**: just pull it :stuck_out_tongue_winking_eye:

```bash
docker pull rpardom/simple_rest:wildfly
```

You may need to rebuild the docker image if you want to change it:

1. Build the artifact:

   ```bash
   mvn package
   ```

2. Build the docker image:

   ```bash
   docker build -f src/main/docker/Dockerfile.wildfly -t simple_rest:wildfly .
   ```

You may want to push it to your own repo:

1. Retag the image with your repo prefix:

   ```bash
   docker tag simple_rest:wildfly rpardom/simple_rest:wildfly
   ```

2. Push it to the repo:

   ```
   docker push rpardom/simple_rest:wildfly
   ```

## Docker image use

Just use it as a service in your own `docker-compose`:

```yml
...
wildfly:
        # This is a simple rest microservice that sends a hello response including the current time.
        # It has been built from a normal java project using a war that is deployed in a wildfly application container.
        image: simple_rest:wildfly
        container_name: perfromance_test_wildfly
        ports:
            # To receive the rest requests to respond
            - 8092:8080
        networks:
            - perfromance_test
...
```

