# Taints and tolerations

0. How many nodes exist on the system?
1. Do any taints exist on minikube node?
2. Create a taint on minikube with key of spray, value of mortein and effect of NoSchedule
3. Create a new pod with the nginx image and pod name as mosquito
4. What is the state of the POD?
5. Why do you think the pod is in a pending state? (Application Error, POD Mosquito cannot tolerate taint Mortein, Image is not available)
6. Create another pod named bee with the nginx image, which has a toleration set to the taint mortein.
7. Notice the bee pod was scheduled despite the taint.
8. Remove the taint on minikube, which currently has the taint effect of NoSchedule
9. What is the state of the pod mosquito now?

# Node Affinity
0. Create a new cluster and add the zscaler certificate for every node. To create a new cluster with multiple node you can use this command:
```shell
minikube start --cni=calico --nodes 3 -p multinode
```
1. How many Labels exist on node multinode?
2. Apply a label color=blue to node multinode-m02
3. Create a new deployment named blue with the nginx image and 3 replicas.
4. Set Node Affinity to the deployment to place the pods on multinode-m02 only.
5. Which nodes are the pods placed on now? Delete and recreated the pods using the scale command

# MultiContainer Pods
0. Execute `kubectl create -f 008_setup.yaml`.
1. Identify the number of containers created in the red pod.
2. Identify the name of the containers running in the blue pod.
3. Create a multi-container pod with 2 containers.

    Name: yellow
    Container 1 Name: lemon
    Container 1 Image: busybox
    Container 2 Name: gold
    Container 2 Image: redis

4. Identify the pod that has an initContainer configured
5. What is the image used by the initContainer on the purple pod?
6. What is the state of the initcontainer on pod purple? (Terminated, Running, Alive, Completed)
7. Why is the initcontainer terminated? (The process cannot start, The process crashed, The process completed successfully)
8. How many initcontainer does the pod black have?
9. What is the state of the Pod? (Running, Terminated, Pending)
10. Update the pod red to use an initcontainer that uses the busybox image and sleeps for 20 seconds
11. There is something wrong with the orange pod. Identify and fix the issue
12. Delete all newly created pods