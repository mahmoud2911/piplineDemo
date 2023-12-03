pipeline {
    agent any
    environment {
        // Set the timezone (example: America/New_York)
        TZ = 'America/New_York'
    }
    tools {
        // Define the Maven tool and version to use
        maven 'Maven 3.9.5'
        allure 'Allure 2.24.1'
    }
    triggers {
        cron('0 0 * * *')  // Schedule the pipeline to run daily at 12 AM
    }
    stages {
        stage('Checkout') {
            steps {
                checkout([$class: 'GitSCM',
                          branches: [[name: '*/master']],
                          doGenerateSubmoduleConfigurations: false,
                          extensions: [],
                          submoduleCfg: [],
                          userRemoteConfigs: [[url: 'https://github.com/mahmoud2911/piplineDemo']]
                ])
            }
        }
        stage('Build and Generate Reports') {
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
        stage('Publish Allure and Execution Summary Reports') {
            steps {
                script {
                    allure([
                        includeProperties: true,
                        jdk: '',
                        results: [
                            [path: 'allure-results']
                        ]
                    ])
                    publishHTML(target: [
                        allowMissing: false,
                        alwaysLinkToLastBuild: false,
                        keepAll: true,
                        reportDir: 'execution-summary',
                        reportFiles: 'ExecutionSummaryReport_*.html',
                        reportName: 'Execution Summary Report',
                        reportTitles: ''
                    ])
                }
            }
        }
        stage('Archive Old Reports') {
            steps {
                archiveArtifacts(artifacts: 'execution-summary/ExecutionSummaryReport_*-AM.html', allowEmptyArchive: true)
                archiveArtifacts(artifacts: 'allure-results/*', allowEmptyArchive: true)
            }
        }
    }
    post {
        always {
            emailext subject: "Test Report for your build",
                body: "Find attached the test report for your build.",
                attachLog: true,
                attachmentsPattern: 'allure-results/*,execution-summary/ExecutionSummaryReport_*-AM.html',
                to: "mahmoud.ahmed@foodics.com"
        }
    }
}
