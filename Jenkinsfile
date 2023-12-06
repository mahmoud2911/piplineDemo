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
                def gitRepoUrl = 'https://github.com/mahmoud2911/piplineDemo' // Replace with your actual repository URL
                def allureReportRelativePath = 'allure-report/index.html'
                def executionSummaryRelativePath = 'execution-summary/ExecutionSummaryReport_*.html'

                def allureReportUrl = "${gitRepoUrl}/blob/master/${allureReportRelativePath}"
                def executionSummaryUrl = "${gitRepoUrl}/blob/master/${executionSummaryRelativePath}"

                echo "Sending email with links to the Allure report and Execution Summary: ${allureReportUrl}, ${executionSummaryUrl}"

                emailext subject: "Test Report for your build",
                        body: """
                        Find the Allure report here: ${allureReportUrl}
                        Find the Execution Summary report here: ${executionSummaryUrl}
                        """,
                        to: "mahmoud.ahmed@foodics.com",
                        mimeType: 'text/html',
                        attachmentsPattern: 'allure-results/**/*,execution-summary/*.html'
            }
        }
    }
}
