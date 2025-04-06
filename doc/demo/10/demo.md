# Deployment strategies

## Demo Blue Green

1.

```shell
kubectl create -f .\demo_blue_green.yaml
```

2. `minikube service demo-frontend -p multinode`
3. `kubectl exec -it test -- /bin/sh`
4. ` kubectl edit svc demo-frontend` and set the value to v2
5.

```shell
kubectl delete pod test --force=true
kubectl delete svc demo-frontend
kubectl delete deployment demo-blue
kubectl delete deployment demo-green
```

## Demo Canary

1.

```shell
kubectl create -f .\demo_canary.yaml
```

2. `kubectl exec -it test -- /bin/sh` -> show 16%
3. Scale down and up

```shell
kubectl scale deployment demo-app --replicas=0
kubectl scale deployment demo-new-app --replicas=5
```

4. `kubectl exec -it test -- /bin/sh` -> show 100%
5.

```shell
kubectl delete pod test --force=true
kubectl delete svc demo-frontend
kubectl delete deployment demo-app
kubectl delete deployment demo-new-app
```
