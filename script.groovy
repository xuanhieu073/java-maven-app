def buildJar() {
    echo "bulding the application"
    sh "mvn package"
}

def buildImage() {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'dockerhub-credential', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh 'docker build -t xuanhieu073/demo-app:jma-2.0 .'
        sh "echo $PASS | docker login -u $USER --password-stdin"
        sh "docker push xuanhieu073/demo-app:jma-2.0"
    }
}

def deployApp() {
    echo "deploying the application..."
    // def dockerCmd = "docker run -p 8080:8080 -d xuanhieu073/demo-app:jma-2.0"
    def dockerComposeCmd = "docker-compose -f docker-compose.yaml up --detach"
    sshagent(['my-server-key']) {
        sh "scp docker-compose.yaml root@178.128.88.219:/root"
        sh "ssh -o StrictHostKeyChecking=no root@178.128.88.219 ${dockerComposeCmd}"
    }
}

return this
