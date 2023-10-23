pipeline {
    agent any
    tools {
        // Define the Maven tool and version to use
        maven 'Maven 3.9.5'
    }
    stages {
        stage('Checkout') {
            steps {
                // Check out the code from the GitHub repository using the token
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/mahmoud2911/piplineDemo']]])
            }
        }
        stage('Build and Generate Allure Report') {
            steps {
                script {
                    if (isUnix()) {
                        sh 'mvn -Dmaven.test.failure.ignore clean test -Dcucumber.filter.tags=@regression'
                    } else {
                        bat 'mvn -Dmaven.test.failure.ignore clean test -Dcucumber.filter.tags=@regression'
                    }
                }
            }
        }
        stage('Publish Allure Report') {
            steps {
                script {
                    // Publish Allure report from the 'allure-results' directory in the project root
                    allure([
                        includeProperties: true,
                        jdk: '',
                        results: [
                            [path: 'allure-results']
                        ]
                    ])
                }
            }
        }
        stage('Execution Summary Report') {
            steps {
                script {
                    // Publish the execution summary report from the 'execution-summary' directory in the project root
                    publishHTML(target: [
                        allowMissing: false,
                        alwaysLinkToLastBuild: false,
                        keepAll: true,
                        reportDir: 'execution-summary',
                        reportFiles: 'summary.html',
                        reportName: 'Execution Summary Report',
                        reportTitles: ''
                    ])
                }
            }
        }
        stage('Archive Artifacts') {
            steps {
                // Archive Allure reports
                archiveArtifacts artifacts: 'allure-results/*', allowEmptyArchive: true
            }
        }
    }
    post {
        always {
            emailext subject: "Allure Report for your build",
                body: "Find attached the Allure report for your build.",
                attachLog: true,
                attachmentsPattern: 'allure-results/*',
                to: "mahmoud.ahmed@foodics.com"
        }
    }
}
