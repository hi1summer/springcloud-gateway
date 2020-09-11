pipeline {
    agent any

    stages {
        stage('clean') {
            steps {
                script{
                    try{
                        powershell "docker stop \$(docker ps --filter name=gateway -qa)"
                        powershell "docker container rm \$(docker ps --filter name=gateway -qa)"
                    }
                    catch(exc){
                        echo 'no container'
                    }
                    try {
                        powershell "docker image rm \$(docker image ls --filter reference=gateway -qa)"
                    }
                    catch(exc){
                        echo 'no image'
                    }
                }
            }
        }
        stage('cleanzipkin') {
            steps {
                script{
                    try{
                        powershell "docker stop \$(docker ps --filter name=zipkin -qa)"
                        powershell "docker container rm \$(docker ps --filter name=zipkin -qa)"
                    }
                    catch(exc){
                        echo 'no container'
                    }
                }
            }
        }
        stage('build') {
            steps {
                bat "mvn clean package"
            }
        }
        stage('sonar') {
            steps {
                bat "mvn sonar:sonar \
                      -Dsonar.projectKey=GATEWAY \
                      -Dsonar.host.url=http://localhost:1001 \
                      -Dsonar.login=8dbd1aa5aaecbf60711560ff1c458d4e0e3597fe"
            }
        }
        stage('zipkin'){
            steps {
                script{
                    image = docker.pull("openzipkin/zipkin:latest")
                    image.run("-p 9411:9411 --name zipkin.${env.BUILD_ID} --restart=always")
                }
            }
        }
        stage('docker') {
            steps {
                script{
                    image = docker.build("gateway:${env.BUILD_ID}")
                    image.run("-p 9000:9000 --name gateway.${env.BUILD_ID} --restart=always")
                }
            }
        }
    }
}
