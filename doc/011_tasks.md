# Network policies

1. Execute `kubectl create -f 011_setup_netpol.yaml`. This will create 3 new pods: frontend, backend, db and test in a new test-netpol namespace.
2. Create services for the backend, frontend and db pods.
3. Test the services
4. Create 3 network policies:

- Frontend:
  - Ingress(Incoming) traffic should not be affected
  - Egress(Outgoing) traffic: it can access only the backend pods
- Backend:
  - Ingress(Incoming) traffic: only the frontend pods can access the backend pods
  - Egress(Outgoing) traffic: it can access only the db
- DB:
  - Ingress(Incoming) traffic: only the backend pods can access the db pods
  - Egress(Outgoing) traffic: it should block all outgoing traffic

5. Test the network policies. Use the services names instead of ips

# Ingress

1. Execute `kubectl create -f 011_setup_ingress.yaml`. We have deployed Ingress resources and applications. Explore the setup.
2. Which namespace is the Ingress Controller deployed in?
3. What is the name of the Ingress Controller Deployment?
4. Which namespace are the applications deployed in?
5. How many applications are deployed in the `app-space` namespace?
6. Which namespace is the Ingress Resource deployed in?
7. What is the name of the Ingress Resource?
8. What backend is the /wear path on the Ingress configured with?
9. At what path is the video streaming application made available on the Ingress?
10. Now view the Ingress Service using the `minikube tunnel` command and `localhost` in browser. Which page do you see?
11. View the applications by appending /wear and /watch to the URL you opened in the previous step.
12. You are requested to change the URLs at which the applications are made available.
    -- Ingress: my-ingress-resource
    -- Path: /stream
    -- Backend Service: webapp-watch-svc
    -- Backend Service Port: 8888
13. A user is trying to view the /eat URL on the Ingress Service. Which page would he see?
14. Make the application `webapp-food` from `app-space` available under `eat` url.
15. Make the application `webapp-pay` from `critical-space` available under `pay` url. Look into annotations: rewrite-target as well.
