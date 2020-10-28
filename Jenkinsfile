#!groovy?
pipeline {

    agent any
    tools {
        maven 'apache-maven-3'
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Checkout'
            }
        }

        stage('Compile') {
            steps {
                echo 'Clean Compile'
                sh 'mvn clean compile -U'
            }
        }

        stage('Package') {
            steps {
                echo 'Packaging'
                sh 'mvn package -DskipTests -U '
            }
        }

        stage('Tests') {
            steps {
                echo 'Tests'
                sh 'mvn clean test -U'
            }
        }

        stage("build & SonarQube analysis") {
            steps{
                withSonarQubeEnv('sonarqube') {
                    sh 'mvn clean package sonar:sonar -Dsonar.host.url=http://localhost:9000'
                }
            }
        }

        stage("Quality Gate"){
            steps{
                script{
                    timeout(time: 1, unit: 'HOURS') {
                        def qg = waitForQualityGate()
                        if (qg.status != 'OK') {
                            error "Pipeline aborted due to quality gate failure: ${qg.status}"
                        }
                    }
                }
            }
        }

        stage('Publish artifact') {
          steps {
                echo 'Publish artifact in Nexus repository'
                sh 'mvn deploy -Dmaven.test.skip=true -Dnexus.url.snapshots=http://localhost:8081 -Dnexus.server.id=nexus'
          }
        }

        stage('Deploy') {
            steps {
                echo 'Deploy in dev'
                sh 'docker rm ejemplo-pruebas -f'
                sh 'docker build -t ejemplo-pruebas .'
                sh 'docker run -d --name ejemplo-pruebas -p 9090:9090 ejemplo-pruebas'
            }
        }
    }

    post {
        always {
            echo 'JENKINS PIPELINE EXECUTION FINISH'
        }
        success {
            echo 'JENKINS PIPELINE HAS BEEN EXECUTED SUCCESSFULLY'
        }
        failure {
            echo 'JENKINS PIPELINE HAS BEEN FAILED'
        }
        unstable {
            echo 'JENKINS PIPELINE WAS MARKED AS UNSTABLE'
        }
        changed {
            echo 'JENKINS PIPELINE STATUS HAS CHANGED SINCE LAST EXECUTION'
        }
    }


}