pipeline {
    agent any
    tools{
        maven 'Maven 3.6.3'
        jdk 'OpenJDK 11'
    }
    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/Ashutosh-Ray/CollaborationTool-backend.git',branch: 'main', credentialsId: 'git',changelog: false
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn install'
            }
        }

        stage('Archive artifacts') {
            steps {
                archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
            }
        }
    }

    post {
        always {
            mail to: 'manoj.ray.puri@gmail.com',
                 subject: "Pipeline",
                 body: "Completed"
        }
    }
}
