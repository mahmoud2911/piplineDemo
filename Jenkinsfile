pipeline {
    agent any
    tools {
        maven 'Maven 3.9.5'
        allure 'Allure 2.24.1'
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
                        sh "mvn -Dmaven.test.failure.ignore clean test -Dcucumber.filter.tags=@regression -DtargetBrowserName=${browser}"
                    } else {
                        bat "mvn -Dmaven.test.failure.ignore clean test -Dcucumber.filter.tags=@regression -DtargetBrowserName=${browser}"
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
                        reportDir: "execution-summary",
                        reportFiles: "ExecutionSummaryReport_*.html",
                        reportName: "Execution Summary Report",
                        reportTitles: ''
                    ])
                }
            }
        }
    } // Close the stages block here

    post { // This section should be at the pipeline level
        always {
            emailext subject: 'Test Report for your build',
            body: 'Find attached the test report for your build.',
            attachLog: true,
            attachmentsPattern: 'allure-results/*,execution-summary/*',
            to: 'mahmoud.ahmed@foodics.com'
        }
    }
}
