# Tasks with pods

1. How many pods exist on the system?
2. Create a new pod with the nginx image.
3. Execute `kubectl create -f 002_setup.yaml`. How many pods are created now?
4. What is the image used to create the new pods?
5. Which nodes are these pods placed on? (You must look at all the pods in detail to figure this out.)
6. How many containers are part of the pod webapp? (Ignore the state of the POD for now.)
7. What images are used in the new webapp pod?
8. What is the state of the container agentx in the pod webapp? (Wait for it to finish the ContainerCreating state)
   - Success
   - Running
   - Error or Waiting
   - Ready
9. Why do you think the container agentx in pod webapp is in error? (Try to figure it out from the events section of the pod.)
   - A Docker image with this name does not exist on Docker Hub
   - NGINX application is not configured to communicate to this image
   - The application inside this image is faulty
   - Faulty K8s cluster
10. What does the READY column in the output of the kubectl get pods command indicate?
    - Total Pods/Running Pods
    - Running Pods/Total Pods
    - Running containers in POD/Total Containers in POD
    - Total containers in POD/Running Containers in POD
11. Delete the webapp Pod.
12. Create a new pod with the name redis and the image redis123. (Use a pod-definition YAML file. And yes the image name is wrong!)
13. Now change the image on this pod to redis. (Once done, the pod should be in a running state.) Use the `kubectl edit` command

# Tasks with replicasets

1. How many ReplicaSets exist on the system?
2. Execute `kubectl create -f 002_setup.yaml`.
3. How many PODs are DESIRED in the new-replica-set?
4. What image is used in the replicaset?
5. How many PODs are READY in the new-replica-set?
6. Why do you think the PODs are not ready?

- The image BUSYBOX777 does not exist
- Application has errors
- Kubernetes is faulty
- Replicaset was not created correctly

7. Delete any one of the 4 PODs. (from the new-replica-set)
8. How many PODs exist now?
9. Why are there still 4 PODs, even after you deleted one?

- ReplicaSet ensures that desired number of PODs always run
- I did not delete it properly
- You cannot delete a POD from a ReplicaSet

10. Create a ReplicaSet using the replicaset-definition-1.yaml file located at ./k8s_tasks. There is an issue with the file, so try to fix it.
11. Fix the issue in the replicaset-definition-2.yaml file and create a ReplicaSet using it.
12. Delete the two newly created ReplicaSets - replicaset-1 and replicaset-2
13. Fix the original replica set new-replica-set to use the correct busybox image.
    Either delete and recreate the ReplicaSet or Update the existing ReplicaSet and then delete all PODs, so new ones with the correct image will be created.
14. Scale the ReplicaSet to 5 PODs. Use kubectl scale command or edit the replicaset using kubectl edit replicaset.
15. Now scale the ReplicaSet down to 2 PODs. Use the kubectl scale command or edit the replicaset using kubectl edit replicaset.
