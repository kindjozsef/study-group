# Tasks with services

1. How many Services exist on the system?
2. What is the type of the default kubernetes service?
3. What is the targetPort configured on the kubernetes service?
4. How many labels are configured on the kubernetes service?
5. How many Endpoints are attached on the kubernetes service?
6. Execute `kubectl create -f 004_setup_svc.yaml`. It will create a new deployment with two pods. Nginx runs on port 80.
7. Access(with curl) one of the created pods (example-pod-svc) using its IP address.
   You can create a temporary pod in default namespace.
   `kubectl run temp -it --restart=Never --rm=true --image=nginx -- /bin/sh`
8. Create a ClusterIP service (using yaml files) for the newly created deployment. Use `something-service` as servicename and 8111 as service port. After it is created check the service Endpoints.
9. Access the service using its ip address. Try to figure it out which of the pods will be called using in two separate windows
   `kubectl logs -f <PODNAME>`
10. Access the service using the servicename (`something-service`). Try to figure it out which of the pods will be called using in two separate windows
    `kubectl logs -f <PODNAME>`
11. Execute `kubectl create -f 004_setup_svc_2.yaml`. It will create a new deployment in the test-svc namespace. Nginx runs on port 80.
12. Access(with curl) one of the created pods (example-svc-test-ns) using its IP address.
    You can create a temporary pod in default namespace.
    `kubectl run temp -it --restart=Never --rm=true --image=nginx -- /bin/sh`
13. Create a ClusterIP service (using imperative commands) for the newly created deployment. Use `something-service-2` as servicename and 8222 as service-port. After it is created check the service Endpoints.
14. Access the service using its ip address. Try to figure it out which of the pods will be called using in two separate windows
    `kubectl logs -f <PODNAME>`
15. Access the service using its name address. Try to figure it out which of the pods will be called using in two separate windows
    `kubectl logs -f <PODNAME>`
