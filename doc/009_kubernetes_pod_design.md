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

# Rolling Updates & Rollbacks in Deployments

When we first creating a deployment it triggers a new rollout and a new revision (v1).
Later when the deployment is updated it triggers a new rollout with a new revision (v2).

To see the rollout history we can use the following command

```shell
kubectl rollout history deployment/<DEPLOYMENT_NAME>
```

There are two strategies to update a deployment: Recreate and RollingUpdate(default)

With kubectl apply we can update our deployment

```shell
kubectl apply -f deployment-definition.yaml
```

With describe command we can see the difference between Recreate and RollingUpdate

If we want to undo a change we can use the following command

```shell
kubectl rollout undo deployment/<DEPLOYMENT_NAME>
```

# Jobs and Cronjobs

A container can serve different type of workloads. Until now we have discussed about databases, webservers. They are meant to run continously.
But there are different kinds of workloads for example: analytics or batch processing, for example performing a computation. They are meant to perform a set of task and then finish.
With jobs we can create multiple pods to process the data in parallel.
Job is like a Deployment. A deployment is used to make sure that a given number of pods are always running.
Job is used to create a given number of pods und run them until completion.

## Creating a Job

### Using yaml files

```yaml
apiVersion: batch/v1
kind: Job
metadata:
  name: math-add-job
spec:
  completions: 3 # How many times we want to perform the job successfully
  parallelism: 2 # How many pods we want to run parallel
  template:
    # Hier comes the Pod definition
    spec:
      containers:
        - name: math-add
          image: ubuntu
          command: ["expr", "3", "+", "5"]
      restartPolicy: Never
```

### Using imperative commands

```shell
kubectl create job <JOB_NAME> --image=<IMAGE_NAME> -- <COMMAND>
```

Example

```shell
kubectl create job add-job --image=ubuntu -- expr 5 + 2
```

## Cronjobs

A cronjob is a job that can be scheduled

```yaml
apiVersion: batch/v1
kind: CronJob
metadata:
  name: cron-job
spec:
  # minute(0 - 59), hour(0 - 23), day of the month (1 - 31), month (1 - 12), day of the week (0 - 6, Sunday to Saturday)
  # https://bradymholt.github.io/cron-expression-descriptor/
  schedule: "*/1 * * * *"
  jobTemplate:
    # the template of the job
    spec:
      completions: 3
      parallelism: 3
      # the template of the pod
      template:
        spec:
          restartPolicy: Never
          containers:
            - name: my-container
              image: ubuntu
              command: ["sh", "-c", "echo hello world"]
```
