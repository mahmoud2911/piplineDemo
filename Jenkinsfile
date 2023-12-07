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

        stage('Build branch') {
            steps {
                echo 'Building and generating reports...'
                script {
                    // Use double-quotes for string interpolation
                    def mavenCommand = "mvn -Dmaven.test.failure.ignore clean test -Dcucumber.filter.tags=@regression"

                    // Use sh for Unix and bat for Windows
                    if (isUnix()) {
                        sh mavenCommand
                    } else {
                        bat mavenCommand
                    }
                }
            }
        }

        stage('Serve Allure Report') {
            steps {
                script {
                    // Use the configured Allure installation
                    def allureExecutable = tool 'Allure 2.24.1'

                    // Run the Allure serve command in the background
                    if (isUnix()) {
                        sh "${allureExecutable}/bin/allure serve allure-results --port 61058 &"
                    } else {
                        bat "start /B ${allureExecutable}\\bin\\allure serve allure-results --port 61058"
                    }

                    // Wait for the Allure server to start (adjust the sleep duration as needed)
                    sleep time: 30, unit: 'SECONDS'
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

            script {
                // Get the project name, build number, and build status
                def projectName = env.JOB_NAME
                def buildNumber = env.BUILD_NUMBER
                def buildStatus = currentBuild.currentResult

                // Build the custom subject with an identifier for test results
                def customSubject = "${projectName} - Build #${buildNumber} - ${buildStatus} - Test Results"
                //allure report url
                def allureReportUrl = "http://localhost:61058/index.html"
                emailext attachmentsPattern: 'execution-summary/*.html',
                body: "Find attached Execution summary report and Allure report here: ${allureReportUrl} ",
                mimeType: 'text/html',
                subject: customSubject,
                to: 'mahmoud.ahmed@foodics.com,m.azab@foodics.com'
            }
        }
    }
}