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

# Network policies

1. Execute `kubectl create -f 004_setup_netpol.yaml`. This will create 3 new pods: frontend, backend and db in a new test-netpol namespace.
2. Create 3 network policies:

- Frontend:
  - Ingress(Incoming) traffic should not be affected
  - Egress(Outgoing) traffic: it can access only the backend pods
- Backend:
  - Ingress(Incoming) traffic: only the frontend pods can access the backend pods
  - Egress(Outgoing) traffic: it can access only the db
- DB:
  - Ingress(Incoming) traffic: only the backend pods can access the db pods
  - Egress(Outgoing) traffic: it should block all outgoing traffic

3. Test the network policies. Do not create services, use the pods Ip addresses instead
