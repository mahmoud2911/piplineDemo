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

        stage('Generate Allure Report') {
            steps {
                script {
                    def allureHome = tool name: 'Allure 2.24.1', type: 'io.qameta.allure.jenkins.tools.AllureCommandlineInstallation'
                    def allureCommand = "${allureHome}/bin/allure"

                    // Run the Allure command to generate the report
                    if (isUnix()) {
                        sh "${allureCommand} generate allure-results -o allure-report"
                    } else {
                        bat "${allureCommand} generate allure-results -o allure-report"
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

        stage('Archive Reports') {
            steps {
                echo 'Archiving reports...'
                archiveArtifacts(artifacts: 'execution-summary/*.html,allure-results/*', allowEmptyArchive: true)
            }
        }
    }

    post {
        always {
            echo 'Sending email with reports...'
            emailext attachmentsPattern: 'execution-summary/*.html,allure-report/index.html',
                    body: 'Please find attached the Allure and Execution Summary reports.',
                    mimeType: 'text/html',
                    subject: 'Test Execution Report',
                    to: 'mahmoud.ahmed@foodics.com'
        }
    }
}
