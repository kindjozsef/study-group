# Tasks with replicasets

1. How many ReplicaSets exist on the system?
2. Execute `kubectl create -f 003_setup_rs.yaml`.
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

# Tasks with deployments

1. How many Deployments exist on the system?
2. How many ReplicaSets exist on the system?
3. How many PODs exist on the system?
4. Execute `kubectl create -f 003_setup_dep.yaml`.
5. How many Deployments, ReplicaSets and PODs exists on the system now?
4. Out of all the existing PODs, how many are ready?
5. What is the image used to create the pods in the new deployment?
6. Why do you think the deployment is not ready?
   - The image BUSYBOX888 does not exist
   - Deployment was not created correctly
   - Application has errors
   - Kubernetes is faulty
7. Create a new Deployment using the deployment-definition-1.yaml file located at /k8s_tasks/.
   There is an issue with the file, so try to fix it.
8. Create a new Deployment with the below attributes using your own deployment definition file.
   - Name: httpd-frontend;
   - Replicas: 3;
   - Image: httpd:2.4-alpine

# Tasks with namespaces

1. How many Namespaces exist on the system?
2. How many pods exist in the research namespace?
3. Create a POD in the finance namespace.
   - Name: redis
   - Image name: redis
4. Which namespace has the blue pod in it?

# Imperative commands

All the questions in this session can be done imperatively. However, for some questions, you may need to first create the YAML file using imperative methods. You can then modify the YAML according to the need and create the object using kubectl apply -f command.

1. Deploy a pod named nginx-pod using the nginx:alpine image. Use imperative commands only.
2. Deploy a redis pod using the redis:alpine image with the labels set to tier=db. Either use imperative commands to create the pod with the labels. Or else use imperative commands to generate the pod definition file, then add the labels before creating the pod using the file.
3. Create a deployment named webapp using the image nginx with 3 replicas.
4. Create a new namespace called dev-ns.
5. Create a new deployment called redis-deploy in the dev-ns namespace with the redis image. It should have 2 replicas.
