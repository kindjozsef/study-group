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
