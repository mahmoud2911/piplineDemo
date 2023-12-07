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

        stage('Build') {
            steps {
                echo 'Building and generating reports...'
                script {
                    // Use double-quotes for string interpolation
                    def mavenCommand = "mvn -Dmaven.test.failure.ignore clean test -Dcucumber.filter.tags=@regression"

                    // Use sh for Unix and bat for Windows
                    sh (script: mavenCommand, label: 'Run Maven Build')
                }
            }
        }

        stage('Publish Allure and Execution Summary Reports in Jenkins') {
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

        stage('Publish Allure to GitHub Pages') {
            steps {
                echo 'Publishing Allure report to GitHub Pages...'
                script {
                    // Use the configured Allure installation
                    def allureExecutable = tool 'Allure 2.24.1'

                    // Run the Allure command to generate the report
                    sh script: "${allureExecutable}/bin/allure generate allure-results -o allure-report --clean", label: 'Generate Allure Report'

                    // Publish to GitHub Pages using gh-pages branch
                    sh '''
                        git checkout -b gh-pages
                        cp -R allure-report/* .
                        git add .
                        git config user.email "mahmoud.ahmed@foodics.com"
                        git config user.name "mahmoud2911"
                        git commit -m "Publish Allure report to GitHub Pages"
                        git push origin gh-pages
                    '''
                }
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
                    // GitHub Pages URL
                    def allureGitHubPagesUrl = "https://github.com/mahmoud2911/piplineDemo/gh-pages/index.html"
                    // Execution Summary URL (if available)
                    def executionSummaryUrl = "${BUILD_URL}Execution_20Summary_20Report/"

                    emailext attachmentsPattern: 'execution-summary/*.html',
                        body: """
                        Find the Allure report on GitHub Pages: ${allureGitHubPagesUrl}<br>
                        Find the Execution Summary report here: ${executionSummaryUrl}
                        """,
                        mimeType: 'text/html',
                        subject: customSubject,
                        to: 'mahmoud.ahmed@foodics.com'
                }
            }
        }
}
