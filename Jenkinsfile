pipeline {
    agent any
    tools {
        maven 'Maven 3.9.5'
        allure 'Allure 2.24.1'
        dockerTool 'Docker'
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
        stage('Build Test Scripts') {
            steps {
                echo 'Building Test Scripts...'
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
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
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
            script {
                def projectName = env.JOB_NAME
                def buildNumber = env.BUILD_NUMBER
                def buildStatus = currentBuild.currentResult
                def customSubject = "${projectName} - Build #${buildNumber} - ${buildStatus} - Test Results"
                def executionSummaryUrl = "${BUILD_URL}Execution_20Summary_20Report/"
                def allureReportUrl = "${BUILD_URL}allure"
                echo "Sending email with a link to the Allure report: ${allureReportUrl}"
                emailext attachmentsPattern: 'execution-summary/*.html',
                body: """
                Find the Allure report here: ${allureReportUrl}<br>
                Find the Execution Summary report here: ${executionSummaryUrl}
                """,
                mimeType: 'text/html',
                subject: customSubject,
                to: 'mahmoud.ahmed@foodics.com'
            }
        }
    }
}