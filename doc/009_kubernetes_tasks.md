# Labels and selectors

0. Execute `kubectl create -f 009_setup_labels.yaml`
1. We have deployed a number of PODs in the labels-test namespace. They are labelled with tier, env and bu. How many PODs exist in the dev environment (env)?
2. How many PODs are in the finance business unit (bu)?
3. Identify the POD which is part of the prod environment, the finance BU and of frontend tier?
4. A Deployment definition file is given 009_deployment-definition-9.yaml. Attempt to create the deployment; you will encounter an issue with the file. Try to fix it.
5. Delete the labels-test namespace

# Rolling Updates & Rollbacks in Deployments

0. Execute `kubectl create -f 009_setup_deployment.yaml`
1. We have deployed a simple web application. Inspect the PODs and the Services
2. What is the current color of the web application? Execute `minikube service demo-dep` and check it from the browser
3. Run the script named /tmp/curl-test.sh in `test` pod to send multiple requests to test the web application. Take a note of the output.
4. Inspect the deployment and identify the number of PODs deployed by it
5. What is the deployment strategy used for this deployment?
6. If you were to upgrade the application now what would happen? (All PODs are taken down before upgrading any, PODs are upgraded few at a time)
7. Let us try that. Upgrade the application by setting the image on the deployment to kodekloud/webapp-color:v2 Do not delete and re-create the deployment. Only set the new image name for the existing deployment.
8. Run the script curl-test.sh again. Notice the requests now hit both the old and newer versions. However none of them fail.
9. Up to how many PODs can be down for upgrade at a time? Consider the current strategy settings and number of PODs - 4
10. Change the deployment strategy to Recreate. Delete and re-create the deployment if necessary. Only update the strategy type for the existing deployment.
11. Upgrade the application by setting the image on the deployment to kodekloud/webapp-color:v3. Do not delete and re-create the deployment. Only set the new image name for the existing deployment.
12. Run the script curl-test.sh again. Notice the failures. Wait for the new application to be ready. Notice that the requests now do not hit both the versions

# Jobs

1. A pod definition file named 009_throw-dice-pod.yaml is given. The image throw-dice randomly returns a value between 1 and 6. 6 is considered success and all others are failure. Try deploying the POD and view the POD logs for the generated number.
2. Create a Job using this POD definition file or from the imperative command and look at how many attempts does it take to get a '6'.
3. Monitor and wait for the job to succeed. Throughout this practice test remember to increase the 'BackOffLimit' to prevent the job from quitting before it succeeds.
   Check out the documentation page about the BackOffLimit property.
4. How many attempts did it take to complete the job?
5. Update the job definition to run as many times as required to get 2 successful 6's
   Delete existing job and create a new one with the given spec. Monitor and wait for the job to succeed.

```yaml
completions: 2
backoffLimit: 25 # This is so the job does not quit before it succeeds.
image: kodekloud/throw-dice
```

6. How many attempts did it take to complete the job this time?
7. That took a while. Let us try to speed it up, by running upto 3 jobs in parallel. Update the job definition to run 3 jobs in parallel.

```yaml
completions: 3
parallelism: 3
backoffLimit: 25 # This is so the job does not quit before it succeeds.
image: kodekloud/throw-dice
```

8. Let us now schedule that job to run at 21:30 hours every day.

```yaml
Schedule: 30 21 * * *
completions: 3
parallelism: 3
backoffLimit: 25 # This is so the job does not quit before it succeeds.
image: kodekloud/throw-dice
```

## Commands

```shell
# List all jobs
kubectl get jobs
# List all cronjobs
kubectl get cronojobs
# delete a job (and all pods)
kubectl delete job <JOB_NAME>
# delete a cronjob (and all jobs and pods)
kubectl delete cronjob <CRON_JOB_NAME>
```
