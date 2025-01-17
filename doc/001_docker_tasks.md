0. Verify the Docker Installation
1. Pull the latest official `nginx` image and list all local images.
2. List all running containers. Create a container from the nginx image. Ensure that it runs in the background in `detached` mode.
3. What is the name of the created container? Can you assign it a custom name?
4. Try to access the nginx on port 80 from the browser? 
5. Publish/expose port 80 by mapping it to port 8081 and access it from the host (http://localhost:8081).
6. Remove all nginx related images and containers.
7. Pull a specific version of the nginx.
8. List all docker images on your system. How would you give for the new nginx image a new tag?
9. Run the nginx image in detached mode, expose port 80 in container and map it to port 8081 on host. Give a custom name to the container.
10. Open a new shell in the created container(you can use `docker exec -it <containername> /bin/sh`) and try to access `http://localhost:8081` via curl. Can you access it?
11. Do you know what is the difference between `RUN`, `CMD`, and `ENTRYPOINT`? Create a Dockerfile and experiment with various combinations.
12. Given [this](https://github.com/kindjozsef/study-group/tree/master/app) spring boot project try to containerize it:
    - create a runnable `fat` jar from the application and start it locally (`./gradlew.bat clean build`)
    - create a Dockerfile from a base image which contains `jre` (you can use eclipse temurin 17 jre)
    - copy the jar into the image ([hier](https://docs.docker.com/get-started/docker-concepts/building-images/writing-a-dockerfile/) you can find some basic commands)
    - start the application within Dockerfile
    - create a container from the newly created image
14. This spring boot project creates a logfile.
     - What happens to the logfile after you stop the container? 
     - What happens after you remove the container? 
     - How could you persist this file? If you set the LOG_FILE_PATH Environment variable you can change the log file location *inside* the container. Can you use a volume for this new folder?