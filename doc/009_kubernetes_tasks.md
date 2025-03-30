# Labels and selectors

0. Execute `kubectl create -f 009_setup_labels.yaml`
1. We have deployed a number of PODs in the labels-test namespace. They are labelled with tier, env and bu. How many PODs exist in the dev environment (env)?
2. How many PODs are in the finance business unit (bu)?
3. Identify the POD which is part of the prod environment, the finance BU and of frontend tier?
4. A Deployment definition file is given 009_deployment-definition-9.yaml. Attempt to create the deployment; you will encounter an issue with the file. Try to fix it.
5. Delete the labels-test namespace
