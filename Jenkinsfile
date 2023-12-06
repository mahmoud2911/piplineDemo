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
                    def mavenCommand = 'mvn -Dmaven.test.failure.ignore clean test -Dcucumber.filter.tags=@regression'
                    if (isUnix()) {
                        sh mavenCommand
                    } else {
                        bat mavenCommand
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
                archiveArtifacts(artifacts: 'execution-summary/*', allowEmptyArchive: true)
                archiveArtifacts(artifacts: 'allure-results/*', allowEmptyArchive: true)
            }
        }
    }

    post {
        always {
            script {
                def allureReportUrl = "${BUILD_URL}allure"
                def executionSummaryUrl = "${BUILD_URL}Execution_20Summary_20Report/" // Adjust the number as needed
                echo "Sending email with links to the Allure report and Execution Summary: ${allureReportUrl}, ${executionSummaryUrl}"
                // Attach only necessary files for Allure report and Execution Summary
                 attachmentsPattern = 'allure-results/index.html,execution-summary/*.html'

                emailext subject: "Test Report for your build",
                body: """
                Find the Allure report here: ${allureReportUrl}
                Find the Execution Summary report here: ${executionSummaryUrl}
                """,
                to: "mahmoud.ahmed@foodics.com",
                attachmentsPattern: attachmentsPattern
            }
        }
    }
}