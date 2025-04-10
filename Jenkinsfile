pipeline {
    agent {
        kubernetes {
            yaml '''
            apiVersion: v1
            kind: Pod
            metadata:
              name: jenkins-agent
            spec:
              containers:
              - name: maven
                image: maven:3.9.9-eclipse-temurin-21-alpine
                command: ["cat"]
                tty: true
              - name: docker
                image: docker:27.2.0-alpine3.20
                command: ["cat"]
                tty: true
                resources:
                  requests:
                    memory: "2Gi"
                    cpu: "1"
                  limits:
                    memory: "4Gi"
                    cpu: "2"
                volumeMounts:
                - mountPath: "/var/run/docker.sock"
                  name: docker-socket
              volumes:
              - name: docker-socket
                hostPath:
                  path: "/var/run/docker.sock"
            '''
        }
    }

    environment {
        DOCKER_IMAGE_NAME = 'yourdockerhubid/backend-project'
        DOCKER_CREDENTIALS_ID = 'dockerhub-access'
    }

    stages {
        stage('Maven Build') {
            steps {
                container('maven') {
                    sh 'mvn -v'
                    sh 'mvn clean package'
                    sh 'ls -al ./target'
                }
            }
        }

        stage('SonarQube Analysis') {
            steps {
                container('maven') {
                    withSonarQubeEnv('sonarqube-server') {
                        sh '''mvn clean verify sonar:sonar \
                            -Dsonar.projectKey=aesopwow-backend \
                            -Dsonar.projectName=aesopwow-backend \
                            -Dsonar.branch.name=develop'''
                    }
                }
            }
        }

        stage('Docker Build & Push') {
            steps {
                container('docker') {
                    script {
                        def dockerImageVersion = "${env.BUILD_NUMBER}"
                        sh 'docker logout'

                        withCredentials([usernamePassword(
                            credentialsId: 'dockerhub-access-aesopwow',
                            usernameVariable: 'DOCKER_USERNAME',
                            passwordVariable: 'DOCKER_PASSWORD'
                        )]) {
                            sh 'echo $DOCKER_PASSWORD | docker login -u $DOCKER_USERNAME --password-stdin'
                        }

                        withEnv(["DOCKER_IMAGE_VERSION=${dockerImageVersion}"]) {
                            sh 'docker build --no-cache -t $DOCKER_IMAGE_NAME:$DOCKER_IMAGE_VERSION ./'
                            sh 'docker push $DOCKER_IMAGE_NAME:$DOCKER_IMAGE_VERSION'
                        }
                    }
                }
            }
        }
    }

    post {
        always {
            withCredentials([string(
                credentialsId: 'discord-webhook',
                variable: 'DISCORD_WEBHOOK_URL'
            )]) {
                discordSend description: """
                제목 : ${currentBuild.displayName}
                결과 : ${currentBuild.result}
                실행 시간 : ${currentBuild.duration / 1000}s
                """,
                result: currentBuild.currentResult,
                title: "${env.JOB_NAME} : ${currentBuild.displayName}",
                webhookURL: "${DISCORD_WEBHOOK_URL}"
            }
        }
    }
}
