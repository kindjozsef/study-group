# Tasks with deployments

-- itt is kell egy setup ami megmutatja, hogy deploymentel egyutt jon az rs is (3 kerdes)

1. How many Deployments exist on the system?
2. How many ReplicaSets exist on the system?
3. How many PODs exist on the system now?
4. Out of all the existing PODs, how many are ready?
5. What is the image used to create the pods in the new deployment?
6. Why do you think the deployment is not ready?
   - The image BUSYBOX888 does not exist
   - Deployment was not created correctly
   - Application has errors
   - Kubernetes is faulty
7. Create a new Deployment using the deployment-definition-1.yaml file located at /root/.
   There is an issue with the file, so try to fix it.
8. Create a new Deployment with the below attributes using your own deployment definition file.
   - Name: httpd-frontend;
   - Replicas: 3;
   - Image: httpd:2.4-alpine

# Tasks with namespaces

1. How many Namespaces exist on the system?
2. How many pods exist in the research namespace? (itt kell valami)
3. Create a POD in the finance namespace.
   - Name: redis
   - Image name: redis
4. Which namespace has the blue pod in it? (4 namespace itt es mas mas podok)

# Imperative commands

All the questions in this session can be done imperatively. However, for some questions, you may need to first create the YAML file using imperative methods. You can then modify the YAML according to the need and create the object using kubectl apply -f command.

1. Deploy a pod named nginx-pod using the nginx:alpine image. Use imperative commands only.
2. Deploy a redis pod using the redis:alpine image with the labels set to tier=db. Either use imperative commands to create the pod with the labels. Or else use imperative commands to generate the pod definition file, then add the labels before creating the pod using the file.
3. Create a service redis-service to expose the redis application within the cluster on port 6379. (ezt kesobbre)
4. Create a deployment named webapp using the image kodekloud/webapp-color with 3 replicas.
5. Create a new pod called custom-nginx using the nginx image and run it on container port 8080.
6. Create a new namespace called dev-ns.
7. Create a new deployment called redis-deploy in the dev-ns namespace with the redis image. It should have 2 replicas.
8. Create a pod called httpd using the image httpd:alpine in the default namespace. Next, create a service of type ClusterIP by the same name (httpd). The target port for the service should be 80.
