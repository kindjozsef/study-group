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

# Persistence

0. Execute `kubectl create -f 010_setup_persistence.yaml`
1. The application stores logs at location /log/app.log. View the logs.
2. If the POD was to get deleted now, would you be able to view these logs.
3. Configure a volume to store these logs at /var/log/webapp on the host.
4. Create a Persistent Volume with the given specification.

- `Volume-Name`: pv-log
- `Storage`: 100Mi
- `Access Modes`: ReadWriteMany
- `Host Path`: /pv/log
- `Reclaim Policy`: Retain

5. Let us claim some of that storage for our application. Create a Persistent Volume Claim with the given specification.

- `Volume-Name`: claim-log-1
- `Storage`: 50Mi
- `Access Modes`: ReadWriteOnce

6. What is the state of the Persistent Volume Claim?

- `CREATED`
- `AVAILABLE`
- `BOUND`
- `PENDING`

7. What is the state of the Persistent Volume?

- `CREATED`
- `AVAILABLE`
- `BOUND`
- `PENDING`

8. Why is the claim not bound to the available Persistent Volume?

- `Access Modes Mismatch`
- `PV and PVC name mismatch`
- `Capacity Mismatch`
- `Reclaim Policy not set correctly`

9. Update the Access Mode on the claim to bind it to the PV. Delete and recreate the claim-log-1.

- `Volume-Name`: claim-log-1
- `Storage`: 50Mi
- `Access Modes`: ReadWriteMany

10. You requested for 50Mi, how much capacity is now available to the PVC?

- `50Mi`
- `100Mi`
- `1Gi`
- `0Mi`

11. Update the `webapp` pod to use the persistent volume claim as its storage. Replace hostPath configured earlier with the newly created PersistentVolumeClaim.

12. What is the Reclaim Policy set on the Persistent Volume pv-log?

- `Retain`
- `Scrub`
- `Delete`
- `Recycle`

13. What would happen to the PV if the PVC was destroyed?

- `The PV is made available again`
- `The PV is not deleted but not available`
- `The PV is deleted as well`
- `The PV is scrubbed`

14. Try deleting the PVC and notice what happens. If the command hangs, you can use CTRL + C to get back to the bash prompt OR check the status of the pvc from another terminal

- `The PVC is deleted`
- `The PVC is stuck in 'terminating' state`

15. Why is the PVC stuck in Terminating state?

- `The PVC is in the process of scrubbing`
- `The PVC is being used by a POD`
- `The PVC is waiting for the PV to be deleted`

16. Let us now delete the webapp Pod. Once deleted, wait for the pod to fully terminate.

17. What is the state of the PVC now?

- `Pending`
- `Bound`
- `Deleted`
- `Available`

18. What is the state of the Persistent Volume now?

- `Available`
- `Pending`
- `Deleted)`
- `Released`
