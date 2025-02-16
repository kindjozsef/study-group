# Observability

1. Execute `kubectl create -f 005_setup.yaml`. It will create a new deployment (`demoapp` )and a `test` pod.
   In the test pod under `/tmp/script.sh` you can find a script which sends multiple requests to a given url.
   Example usage:

```shell
cd /tmp
./script.sh demoapp/info
```

2. Run the script (use the following url `demoapp/info`) and check the application responses
3. Scale up the deployment `demoapp` and try to run the script again
4. Notice the error in the output of the script
5. Update the newly created demoapp deployment with a readinessProbe using the following spec:

- type: httpGet
- port: 8080
- port: /ready
- initialDelay: 20 Sec

6. Run the script again to test the web application. Notice that none of the requests fail now. Though all the requests hit the same POD.
7. What would happen if the application inside container on one of the PODs crashes?
8. Try to crash a pod by sending this request the test pod: `curl demoapp/poisonpill`
9. When the application crashes, the container is restarted. During this period the service directs users to the available POD, since the POD status is not READY.
10. What would happen if the application inside container on one of the PODs freezes?
11. Try to crash a pod by sending this request the test pod: `curl demoapp/freeze`
12. Update the demoapp deployment with a livenessProbe using the following spec:

- type: httpGet
- port: 8080
- port: /live
- initialDelay: 20 Sec

13. Freeze the application again and notice what happens. When a POD freezes, it would be restarted automatically.
