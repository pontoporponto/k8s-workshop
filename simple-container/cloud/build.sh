mvn clean install

docker build -t gcr.io/pontoporponto/simple-container .

docker push gcr.io/pontoporponto/simple-container:latest