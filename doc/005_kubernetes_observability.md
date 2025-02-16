# Observability

## Lifecycle of a POD

### POD Status

- Pending: When the scheduler determines on which node to place the Pod.
- ContainerCreating: After the scheduler assigns a node, the Pod enters the ContainerCreating status. The required images are pulled from the image registry.
- Running: Once the container starts, the Pod remains in the Running state until the program completes successfully or is terminated.

### Pod Conditions

These complement the Pod Status. They are represented as an array of boolean values (true/false).

The Ready condition indicates that the application inside the Pod is ready to accept user traffic.
For example, the application inside the Pod could be a web server or a database server, which may require several seconds or minutes to warm up.

Why is this important?\
If the Pod's status is Ready, a Kubernetes service can redirect traffic to it. However, if the application inside the container is not ready, a user request might be sent to an unprepared application, adversely affecting users.

## ReadinessProbe

Readiness probes in Kubernetes are like a health check for your application, ensuring that a container is fully ready to accept traffic before it starts receiving requests. They help prevent errors by only routing requests to pods that have successfully passed this check, keeping your application running smoothly.

There are 3 types:

- HTTP GET Readiness Probe: This probe sends an HTTP request to a specified endpoint on your container and marks it ready if it receives a successful response.
- TCP Socket Readiness Probe: This probe attempts to open a TCP connection on a specified port in your container and deems it ready if the connection is successful.
- Exec Readiness Probe: This probe runs a specified command inside your container and considers it ready if the command exits with a success status.

In a Pod/Deployment definition file

```yaml
spec:
  containers:
    - image: <IMAGE_NAME>
      name: <CONTAINER_NAME>
      readinessProbe:
        initialDelaySeconds:
        periodSeconds:
        failureThreshold:
        httpGet:
          port:
          path:
```

## LivenessProbe

Liveness probes in Kubernetes continuously check if your container is still running properly by executing commands, sending HTTP requests, or opening TCP connections. If these checks fail, Kubernetes automatically restarts the container to help keep your application healthy and running smoothly.

There are 3 types:

- HTTP GET Liveness Probe: This probe sends an HTTP request to a specified endpoint on your container and considers it healthy if it receives a successful response, otherwise the container is restarted.
- TCP Socket Liveness Probe: This probe attempts to open a TCP connection on a designated port, and if it fails, the container is deemed unhealthy and restarted.
- Exec Liveness Probe: This probe executes a defined command inside the container, and if the command returns a non-zero exit code, it indicates the container is unhealthy and should be restarted.

In a Pod/Deployment definition file

```yaml
spec:
  containers:
    - image: <IMAGE_NAME>
      name: <CONTAINER_NAME>
      livenessProbe:
        initialDelaySeconds:
        periodSeconds:
        failureThreshold:
        httpGet:
          port:
          path:
```

# Logs

We can view the logs from a pod using the following command:

```shell
kubectl logs <PODNAME>
```

If we want to follow the logs (recieve new logs) we can use

```shell
kubectl logs -f <PODNAME>
```

If we have multiple containers in one pod we can use the following command

```shell
kubectl logs -f <PODNAME> <CONTAINERNAME>
```
