# Observability

## Lifecycle of a POD

### POD Status

- Pending: when the scheduler tries to figure it out where (on which) Node place the Pod
- ContainerCreating: after the scheduler finds a Node then the Pod will be in ContainerCreating status. The required images are pulled from image registry
- Running: after the container is started the pod will be in Running state until the program completes successfully or it is terminated

### Pod Conditions

It complement the Pod Status. It is an array of true/false.

The Ready condition indicates the the application inside the Pod is ready to accept user traffic.
For example the application inside the pod could be a webserver or a database server. They can take several seconds/minutes to warm up.

Why is it important?
If the status of the Pod is Ready a kubernetes service can redirect traffic to the pod. But if the application inside in the container is not ready it can happen that a user request is redirected to a non-ready application thus affecting users.

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
