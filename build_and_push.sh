./mvn clean install
docker build . -t 327412/app-1.0.0
docker push 327412/app-1.0.0