pipeline {
    agent: any
    stages {
        stage("build jar") {
            steps {
                script {
                    echo "bulding the application"
                    sh "mvn package"
                }
            }
        }
        stage("build image") {
            steps {
                script {
                    echo: "building the docker image..."
                    withCredentials([usernamePassword(credentialsId: 'dockerhub-credential', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
                        sh "docker build -t xuanhieu073/demo-app:jma-2.0 ."
                        sh "echo $PASS | docker login -u $USER --password-stdin"
                        sh "docker push xuanhieu073/demo-app:jma-2.0"
                    }
                }
            }
        }
        stage("deploy") {
            steps {
                script {
                    echo "deploying the application..."
                }
            }
        }
    }
}