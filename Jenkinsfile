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
                    def browsers = ['chrome', 'MicrosoftEdge', 'MicrosoftInternetExplorer']

                    for (def browser in browsers) {
                        if (isUnix()) {
                            sh "mvn -Dmaven.test.failure.ignore clean test -Dcucumber.filter.tags=@regression -DtargetBrowserName=${browser}"
                        } else {
                            bat "mvn -Dmaven.test.failure.ignore clean test -Dcucumber.filter.tags=@regression -DtargetBrowserName=${browser}"
                        }

                        def browserReportDir = "allure-results-${browser}"
                        dir(browserReportDir) {
                            if (isUnix()) {
                                sh "cp -r ../allure-results/* ."
                            } else {
                                bat 'xcopy /s ..\\allure-results\\* .'
                            }
                        }

                        // Copy or move your execution summary reports to the 'execution-summary' directory
                        // Ensure the report files for each browser have unique names to avoid overwriting
                        def executionSummaryDir = "execution-summary-${browser}"
                        dir(executionSummaryDir) {
                            if (isUnix()) {
                                sh "cp -r ../execution-summary/* ."
                            } else {
                                bat 'xcopy /s ..\\execution-summary\\* .'
                            }
                        }
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

                    for (def browser in browsers) {
                        publishHTML(target: [
                            allowMissing: false,
                            alwaysLinkToLastBuild: false,
                            keepAll: true,
                            reportDir: "execution-summary-${browser}",
                            reportFiles: "ExecutionSummaryReport_*.html",
                            reportName: "Execution Summary Report for ${browser}",
                            reportTitles: ''
                        ])
                    }
                }
            }
        }
    }
    post {
        always {
            emailext subject: 'Test Report for your build',
                body: 'Find attached the test report for your build.',
                attachLog: true,
                attachmentsPattern: 'allure-results/*,execution-summary/*',
                to: 'mahmoud.ahmed@foodics.com'
        }
    }
}
