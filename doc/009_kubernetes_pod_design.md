# Labels, selectors

It is a standard method to group things together.\
We can filter and group the kubernetes resources based on some criterias.\
Labels are properties attached to each item and selectors help us filter those items.\

We can set the labels when creating an object under the metadata section.

```yaml
apiVersion: v1
kind: Pod
metadata:
  name: simple-frontend
  labels:
    app: App1
    function: frontend
spec:
  containers:
    - image: nginx
      name: nginxcontainer
```

And with the following command we can filter the resources

```shell
kubectl get pods --selector app=App1,function=frontend
```

Kubernetes uses labels and selectors internally to connect objects together. For example to connect the deployments and pods together.

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: new-deployment
  # Labels from the deployment must not be equal with selector/pod labels
  labels:
    mykey: somevalue
spec:
  replicas: 4
  # We map this deployment to app:new-deployment label
  selector:
    matchLabels:
      app: new-deployment
  # this is the template for the pod
  template:
    metadata:
      labels:
        app: new-deployment
    spec:
      containers:
        - image: nginx
          name: mycontainer
```

Using this command we can create a pod with labels imperatively.

```shell
kubectl run <POD_NAME> --image=<IMAGE_NAME> --labels="<LABEL_1_KEY>=<LABEL_1_VALUE>,<LABEL_2_KEY>=<LABEL_2_VALUE>,...,<LABEL_N_KEY>=<LABEL_N_VALUE>"
```

Example

```shell
kubectl run my-pod --image=nginx --labels="env=prd,tier=frontend,bu=insurance"
```
