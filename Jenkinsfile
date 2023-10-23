pipeline {
    agent any
    tools {
        // Define the Maven tool and version to use
        maven 'Maven 3.9.5'
        allure 'Allure 2.24.1'
       // jdk 'Java 7u80'

    }
    stages {
        stage('Checkout') {
            steps {
                // Check out the code from the GitHub repository using the token
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/mahmoud2911/piplineDemo']]])
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
                    // Publish Allure report from the 'allure-results' directory in the project root
                    allure([
                        includeProperties: true,
                        jdk: '',
                        results: [
                            [path: 'allure-results']
                        ]
                    ])

                    // Publish the execution summary report from the 'execution-summary' directory in the project root
                    publishHTML(target: [
                        allowMissing: false,
                        alwaysLinkToLastBuild: false,
                        keepAll: true,
                        reportDir: 'execution-summary',
                        reportFiles: 'ExecutionSummaryReport_*-AM.html',
                        reportName: 'Execution Summary Report',
                        reportTitles: ''
                    ])
                }
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
