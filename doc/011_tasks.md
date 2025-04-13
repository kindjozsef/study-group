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

3. Test the network policies. Use the services names instead of ips
