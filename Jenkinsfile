pipeline {
    agent any

    tools {
        maven 'Maven 3.9.5'
        allure 'Allure 2.24.1'
    }

    stages {
        // ... (your existing stages remain unchanged)

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

        // ... (your existing stages remain unchanged)
    }

    post {
        always {
            script {
                def allureReportUrl = "${BUILD_URL}allure"
                def executionSummaryUrl = "${BUILD_URL}execution-summary/ExecutionSummaryReport_1.html" // Adjust the number as needed
                echo "Sending email with links to the Allure report and Execution Summary: ${allureReportUrl}, ${executionSummaryUrl}"
                // Attach only necessary files for Allure report and Execution Summary
                attachmentsPattern = 'allure-results/index.html,execution-summary/*.html'

                emailext subject: "Test Report for your build",
                    body: """
                    Find the Allure report here: ${allureReportUrl}
                    Find the Execution Summary report here: ${executionSummaryUrl}
                    """,
                    to: "m666245@gmail.com",
                    attachmentsPattern: attachmentsPattern
            }
        }
    }
}
