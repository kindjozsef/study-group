# SecurityContexts

0. Execute `kubectl create -f 007_setup_sc.yaml`.
1. What is the user used to execute the sleep process within the `ubuntu-sleeper` pod?
2. Edit the pod ubuntu-sleeper to run the sleep process with user ID 1010.
3. A Pod definition file named `007_multi-pod.yaml` is given. With what user are the processes in the web container started?
4. With what user are the processes in the sidecar container started?
5. Update pod ubuntu-sleeper to run as Root user and with the SYS_TIME capability.
6. Now update the pod to also make use of the NET_ADMIN capability.

# Service Accounts

1. How many Service Accounts exist in the default namespace?
2. What is the secret token used by the default service account? (default-token-87x8w, default-token-j4hkv, default-token-zf6x9, none)
3. Execute `kubectl create -f 007_setup_sa.yaml`. Inspect the `dashboard` deployment. What is the image used by the deployment?
4. Execute `minikube service dashboard`
5. What is the state of the dashboard? Have the pod details loaded successfully?
6. What type of account does the Dashboard application use to query the Kubernetes API? (Service Account, LDAP Account, OAuth Account, User Account)
7. Which account does the Dashboard application use to query the Kubernetes API? (Administrator, Kube-Admin, Admin, Default)
8. Inspect the Dashboard Application POD and identify the Service Account mounted on it. (default, service-account, dashboard-sa)
9. At what location is the ServiceAccount credentials available within the pod? (/opt/run/secrets, /etc/run/secrets/, var/run/token, /var/run/secrets)
10. The application needs a ServiceAccount with the Right permissions to be created to authenticate to Kubernetes. The default ServiceAccount has limited access. Create a new ServiceAccount named dashboard-sa.
11. We just added additional permissions for the newly created dashboard-sa account using RBAC. (We will discuss about it later but you can find details in `007_setup_sa.yaml`)
12. Enter the access token in the UI of the dashboard application. Click Load Dashboard button to load Dashboard
    Create an authorization token for the newly created service account, copy the generated token and paste it into the token field of the UI.
    To do this, run kubectl create token dashboard-sa for the dashboard-sa service account, copy the token and paste it in the UI.
13. You shouldn't have to copy and paste the token each time. The Dashboard application is programmed to read token from the secret mount location. However currently, the default service account is mounted. Update the deployment to use the newly created ServiceAccount. Edit the deployment to change ServiceAccount from default to dashboard-sa.
14. Refresh the Dashboard application UI and you should now see the PODs listed automatically.
    This time you shouldn't have to put in the token manually.

# Resource Requirements and limits

0. Execute `kubectl create -f 007_setup_rl.yaml`.
1. A pod called rabbit is deployed. Identify the CPU requirements set on the Pod
2. Delete the rabbit Pod.
3. Another pod called elephant has been deployed in the default namespace. It fails to get to a running state. Inspect this pod and identify the Reason why it is not running.
   (OOMKilled, CrashLoopBackOff, Running, Ready)
4. The status OOMKilled indicates that it is failing because the pod ran out of memory. Identify the memory limit set on the POD.
5. The elephant pod runs a process that consumes 15Mi of memory. Increase the limit of the elephant pod to 20Mi.
6. Inspect the status of POD. Make sure it's running
7. Delete the elephant Pod.

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