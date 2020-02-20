pipeline {
    agent any
    stages {
        stage('Clean & Build') {
            steps {
                sh 'mvn clean'
            }
        }
        stage('Tests') {
            steps {
                script {
                    try {
                        sh 'mvn test'
                    } catch (err) {
                        echo err.message
                    }
                }
                echo currentBuild.result
            }
        }
        stage('Publish Report') {
            steps {
                publishHTML (target: [
                  allowMissing: false,
                  alwaysLinkToLastBuild: false,
                  keepAll: true,
                  reportDir: 'target/surefire-reports',
                  reportFiles: 'report.html',
                  reportName: "Cucumber Report"
                  ])
            }
        }
    }
}