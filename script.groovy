def buildJar() {
    echo "bulding the application"
    sh "mvn package"
}

def buildImage() {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'dockerhub-credential', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh "docker build -t xuanhieu073/demo-app:jma-2.0 ."
        sh "echo $PASS | docker login -u $USER --password-stdin"
        sh "docker push xuanhieu073/demo-app:jma-2.0"
    }
}

def deployApp() {
    echo "deploying the application..."
}

return this