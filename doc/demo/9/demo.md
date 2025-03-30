# Labels and selectors

0. Create a pod with some labels using yaml file
1. Create a pod with some labels using the `kubectl run` command
2. Demo using selectors
3. Deployment Pod mapping

# Recreate, RollingUpdate

0. Create a deployment(Recreate) and show the history, version of the app and the replicaSets
1. Create a second deployment(RollingUpdate) and show the history, version of the app and the replicaSets

```shell
kubectl expose deployment demo-rolling-dep --port=80 --target-port=8080 --type="NodePort" -n demo-strategy
kubectl expose deployment demo-recreate-dep --port=80 --target-port=8080 --type="NodePort" -n demo-strategy
minikube service demo-rolling-dep -p multinode -n demo-strategy
minikube service demo-recreate-dep -p multinode -n demo-strategy
kubectl rollout history deployment/demo-rolling-dep -n demo-strategy
kubectl rollout history deployment/demo-recreate-dep -n demo-strategy
kubectl get rs -n demo-strategy
kubectl apply -f .\demo_recreate.yaml
kubectl apply -f .\demo_rolling.yaml
```

2. Update the definition files and show the application and the history and with the describe command the deployments

```shell
kubectl describe deployment demo-recreate-dep -n demo-strategy
kubectl describe deployment demo-rollout-dep -n demo-strategy
```

3. Show the history, version of the app and the ReplicaSets
4. Go back to an earlier revision

# Jobs, Cronjobs

0. Docker container performing a simple math operation

```shell
docker run ubuntu expr 3 + 2
```

1. Kubernetes demo for the same example (always and never)

```yaml
apiVersion: v1
kind: Pod
metadata:
  name: math-pod
spec:
  containers:
    - name: math-add
      image: ubuntu
      command: ["expr", "3", "+", "2"]
```

2. Kubernetes demo for normal job
3. Kubernetes demo for random-error job
4. Kubernetes demo for random-error job with parallelism
5. Creating jobs with `kubectl create`
6. Creating a cronjob

```yaml
apiVersion: batch/v1beta1
kind: CronJob
metadata: cron-job
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
          containers:
            - name: my-container
              image: ubuntu
              command: ["sh", "-c", "echo hello world"]
```
