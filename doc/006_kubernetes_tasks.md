# ConfigMap tasks

0. Execute `kubectl create -f 006_setup_cm.yaml`
1. How many PODs exist on the system?
2. What is the environment variable name set on the container in the `cm-task` pod? (pink, COLOR, APP_COLOR, Sleep, APP-Color, App)
3. What is the value set on the environment variable APP_COLOR on the container in the `cm-task` pod? (Sleep, APP-COLOR, blue, pink, COLOR, green)
4. Check the logs of the `cm-task` pod.
5. Update the environment variable on the POD to print a green background. Recreate the pod and check the logs again
6. How many ConfigMaps exists in the default namespace?
7. Create a new ConfigMap for the `cm-task` POD. Use the spec given below. (ConfigMap Name: cm-task-config-map, Data: APP_COLOR=darkblue, APP_OTHER: ignore me)
8. Update the environment variable on the POD to use only the APP_COLOR key from the newly created ConfigMap.
9. Recreate the pod and check the logs again

# Secrets tasks

1. How many Secrets exist on the system?
2. How many secrets are defined in the dashboard-token secret?
3. What is the type of the dashboard-token secret? (Secret, kubernetes.io/service-account-token, Opaque)
4. Which of the following is not a secret data defined in dashboard-token secret? (ca.crt, type, token, namespace)
5. Check the logs of the `secret-task` pod. Note that the user, host and password are not set.
6. Create a new secret named db-secret with the data given below. (Secret Name: db-secret, Secret 1: DB_HOST=sql01, Secret 2: DB_USER=root, Secret3: DB_PASSWORD=password123)
7. Configure `secret-task` pod to load environment variables from the newly created secret.
8. Check the logs again
