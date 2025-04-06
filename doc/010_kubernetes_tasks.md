# Deployment Strategies

0. Execute `kubectl create -f 010_setup_deployment.yaml`
1. Two deployments(`old-app` and `new-app`) have been created in the default namespace. What are the deployment strategies used?

- `CANARY`
- `ROLLING UPDATE`
- `RECREATE`
- `BLUE GREEN`

2. Configure the `new-app` deployment in such a way that the service called `frontend-service` routes less than 20% of traffic to the `new-app` deployment
3. Run the script `/tmp/script.sh` in the test Pod. Notice the requests now hit both the old and newer versions.
4. Check out the application from the browser. Use `minikube service frontend-service` to expose the service and refresh the browser until your requests hit both versions.
5. We have now established that the new version v2 of the application is working as expected. Scale down the `old-app` deployment to 0 and scale up the `new-app` deployment to 5.
6. Reload the app in the browser and run the `script.sh` again.
