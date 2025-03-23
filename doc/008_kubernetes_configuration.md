# Taints and tolerations

Taints and tolerations in Kubernetes are mechanisms that control how pods are scheduled on nodes by preventing unwanted pods from being placed on specific nodes (taints) unless they have matching tolerations. This is useful for dedicating nodes to specific workloads, ensuring better resource allocation, isolation, and improved cluster performance.

## How to taint a node

```shell
kubectl taint nodes <NODE_NAME> <KEY>=<VALUE>:<EFFECT>
```

The effect can be either: NoSchedule, PreferNoSchedule or NoExecute

`Example`
```shell
kubectl taint nodes node1 key=value:NoSchedule
```

## How to untaint a node

```shell
kubectl taint nodes <NODE_NAME> <KEY>=<VALUE>:<EFFECT>-
```

The effect can be either: NoSchedule, PreferNoSchedule or NoExecute

`Example`
```shell
kubectl taint nodes node1 key=value:NoSchedule-
```

This removes the taint from the node


## How to Tolerate a Taint in a Pod

To allow a pod to be scheduled on a tainted node, add a toleration to its YAML definition:

```yaml
apiVersion: v1
kind: Pod
metadata:
  name: my-pod
spec:
  tolerations:
  - key: "key"
    operator: "Equal"
    value: "value"
    effect: "NoSchedule"
  containers:
  - name: my-container
    image: nginx
```
This pod can now be scheduled on nodes with the key=value:NoSchedule taint.

# Node Affinity

The primary feature of Node Affinity is to ensure that a pod is hosted on a particular node using the labels of the node.

```yaml
apiVersion: v1
kind: Pod
metadata:
  name: my-pod
spec:
  containers:
    - name: my-container
      image: nginx
  affinity:
    nodeAffinity:
      # Check this out if this property is too long `kubectl explain pod.spec.affinity.nodeAffinity`
      requiredDuringSchedulingIgnoredDuringExecution:
        nodeSelectorTerms:
          - matchExpressions:
            - key: <KEY>
              # Complete list: `kubectl explain pod.spec.affinity.nodeAffinity.requiredDuringSchedulingIgnoredDuringExecution.nodeSelectorTerms.matchExpressions`
              operator: # It can be In, NotIn, Exists, DoesNotExists, Gt, Lt
              values: [<VALUE_1>, <VALUE_2>, ..., <VALUE_N>]
```

What if there are no nodes corresponding the matching expression or someone removes a label after the pod was placed on a specific node?
This long `requiredDuringSchedulingIgnoredDuringExecution` sentence answers our questions.
`requiredDuringSchedulingRequiredDuringExecution` - is a planned feature.

You can add a label to a node using this command

```shell
kubectl label node <NODE_NAME> <KEY>=<VALUE>
```

You can remove a label with the following command

```shell
kubectl label node <NODE_NAME> <KEY>-
```

# Multi Container Pods

They share:
- the same lifecycle (created and destroyed together)
- the same networkspace (they can refer each other as localhost)
- they have access the same volumes

## Multiple Containers

```yaml
apiVersion: v1
kind: Pod
metadata:
  name: my-pod
spec:
  containers:
    - name: <CONTAINER_NAME_1>
      image: <IMAGE_1>
    - name: <CONTAINER_NAME_2>
      image: <IMAGE_2>
    ...
    - name: <CONTAINER_NAME_N>
      image: <IMAGE_3>
```

In a multi-container pod, each container is expected to run a process that stays alive as long as the POD's lifecycle.

## Init Containers

But at times you may want to run a process that runs to completion in a container. For example a process that pulls a code or binary from a repository that will be used by the main web application. That is a task that will be run only one time when the pod is first created. Or a process that waits for an external service or database to be up before the actual application starts. That's where initContainers comes in.

An initContainer is configured in a pod like all other containers, except that it is specified inside a initContainers section, like this:

```yaml
apiVersion: v1
kind: Pod
metadata:
  name: my-pod
spec:
  containers:
    - name: <CONTAINER_NAME_1>
      image: <IMAGE_1>
    - name: <CONTAINER_NAME_2>
      image: <IMAGE_2>
    ...
    - name: <CONTAINER_NAME_N>
      image: <IMAGE_3>
  initContainers:
    - name: <INIT_CONTAINER_NAME_1>
      image: <IMAGE_1>
    - name: <INIT_CONTAINER_NAME_2>
      image: <IMAGE_2>
    ...
    - name: <INIT_CONTAINER_NAME_N>
      image: <IMAGE_3>
```

When a POD is first created the initContainer is run, and the process in the initContainer must run to a completion before the real container hosting the application starts.
You can configure multiple such initContainers as well, like how we did for multi-pod containers. In that case each init container is run one at a time in sequential order.
If any of the initContainers fail to complete, Kubernetes restarts the Pod repeatedly until the Init Container succeeds.