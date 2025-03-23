# Security Contexts

The security context is a set of security-related configurations applied to a pod or container. It specifies user and group IDs, file system permissions, and other security settings. This configuration controls how processes inside the container run and interact with the host system.

## Why do we use it?

- **Enhanced security**: Running containers with limited privileges helps reduce the risk if a container is compromised.
- **Least Privilege Principle**: By not running as the root user, we limit potential damage since the container only has the minimum permissions required to function.
- **Compliance**: Many security best practices and policies require containers to run as non-root users.

## What Happens When a Container is Launched with a Non-root User?

- The container runs with a user ID other than 0 (the root user), which means its processes have restricted privileges.
- This limits the container’s ability to modify system files or access sensitive parts of the host, reducing the risk of a full system compromise.
- However, if the application inside the container expects to run as root or requires certain permissions, it might fail to execute correctly unless those permissions are explicitly granted.

## What are capabilities?

Capabilities are fine-grained Linux permissions that allow a process to perform specific privileged operations without granting full root privileges. Instead of giving a container complete root access, you can grant only the necessary capabilities required for its tasks.

## Yaml

### At Pod level

```yaml
apiVersion: v1
kind: Pod
metadata:
  name: ubuntu-sleeper
spec:
  securityContext:
    runAsUser: <USER_ID> # 0 == root
  containers:
    - image: ubuntu
      name: ubuntu-sleeper
      command: ["sh", "-c", "sleep 3600"]
```

### At Container level

```yaml
apiVersion: v1
kind: Pod
metadata:
  name: ubuntu-sleeper
spec:
  containers:
    - image: ubuntu
      name: ubuntu-sleeper
      command: ["sh", "-c", "sleep 3600"]
      securityContext:
        runAsUser: <USER_ID> # 0 == root
        capabilities:
          add: [""]
          drop: [""]
```

# Service Accounts

There are two types of accounts in Kubernetes:

- User accounts: used by humans for example an Administrator or a developer
- Service accounts: used by an application to interact with the kubernetes cluster (for example jenkins, prometheus)

## Creating service accounts

```shell
kubectl create serviceaccount <SA_NAME>
```

# Resource Requests and Limits

## Requests: Definition

A resource request specifies the minimum amount of CPU and memory that a container requires to run. It informs the scheduler about the guaranteed resources for a pod.

- Ensures that a pod is scheduled onto a node with sufficient available resources.
- Example: Requesting "250m" CPU (i.e., 0.25 CPU cores) and "64Mi" memory guarantees these amounts.

Containers are guaranteed these resources once scheduled, which helps avoid resource starvation.

## Limits: Definition

A resource limit sets the maximum amount of CPU and memory that a container can consume. It prevents a single container from overusing node resources.

- CPU: If a container exceeds its CPU limit, Kubernetes throttles its CPU usage.
- Memory: Exceeding the memory limit can lead to the container being terminated (commonly resulting in an "OOMKilled" error).

Limits help maintain stability and fairness in resource distribution across all containers running on a node.

```yaml
apiVersion: v1
kind: Pod
metadata:
  name: example-pod
spec:
  containers:
    - name: example-container
      image: nginx
      resources:
        requests:
          cpu: "250m"
          memory: "64Mi"
        limits:
          cpu: "500m"
          memory: "128Mi"
```

## Values

1 CPU means:

- 1 AWS vCPU
- 1 GCP Core
- 1 Azure Core
- 1 HyperThread

The minimum value us 0.1 or 1m
1 G = 1_000_000_000 bytes
1 M = 1_000_000 bytes
1 K = 1_000 bytes

1 Gi = 1_073_741_824 bytes
1 Mi = 1_048_576 bytes
1 Ki = 1_024 bytes