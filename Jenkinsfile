pipeline {
    agent any
    tools {
        maven 'Maven 3.9.5'
        allure 'Allure 2.24.1'
    }
    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out code...'
                checkout([
                    $class: 'GitSCM',
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
                echo 'Building and generating reports...'
                script {
                    if (isUnix()) {
                        sh 'mvn -Dmaven.test.failure.ignore clean test -Dcucumber.filter.tags=@regression -DexecutionAddress=dockerized -DtargetOperatingSystem=LINUX'
                    } else {
                        bat 'mvn -Dmaven.test.failure.ignore clean test -Dcucumber.filter.tags=@regression'
                    }
                }
            }
        }
        stage('Publish Allure and Execution Summary Reports') {
            steps {
                echo 'Publishing Allure and execution summary reports...'
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
                echo 'Archiving old reports...'
                archiveArtifacts(artifacts: 'execution-summary/ExecutionSummaryReport_*-AM.html', allowEmptyArchive: true)
                archiveArtifacts(artifacts: 'allure-results/*', allowEmptyArchive: true)
            }
        }
    }
    post {
        always {
            echo 'Sending email...'
            emailext subject: "Test Report for your build",
                body: "Find attached the test report for your build.",
                attachLog: true,
                attachmentsPattern: 'allure-results/*,execution-summary/ExecutionSummaryReport_*-AM.html',
                to: "m666245@gmail.com"
        }
    }
}