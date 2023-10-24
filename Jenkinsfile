pipeline {
    agent any
    tools {
        // Define the Maven tool and version to use
        maven 'Maven 3.9.5'
        allure 'Allure 2.24.1'
    }
    stages {
        stage('Checkout') {
            steps {
                // Check out the code from the GitHub repository using the token
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/mahmoud2911/piplineDemo']]])
            }
        }
        stage('Build and Generate Reports') {
            steps {
                   script {
                            // Add a loop to run the tests with multiple browsers
                            def browsers = ['chrome','MicrosoftEdge','MicrosoftInternetExplorer'] // You can add more browsers
                            for (def browser in browsers) {
                            if (isUnix()) {
                                          sh "mvn -Dmaven.test.failure.ignore clean test -Dcucumber.filter.tags=@regression -DtargetBrowserName=${browser}"
                                      } else {
                                          bat "mvn -Dmaven.test.failure.ignore clean test -Dcucumber.filter.tags=@regression -DtargetBrowserName=${browser}"
                                      }
                                       // Create a separate directory for each browser
                                                              def browserReportDir = "allure-results-${browser}"
                                                              dir(browserReportDir) {
                                                                  // Copy the Allure results from the default directory to the browser-specific directory
                                                                  if (isUnix()) {
                                                                  sh "cp -r ../allure-results/* ."
                                                                  } else {
                                                                  bat 'xcopy /s ..\\allure-results\\* .'
                                                               }
                                                              }
                                  }
                              }
                           }
        }
        stage('Publish Allure and Execution Summary Reports') {
            steps {
                script {
                    // Publish Allure report from the 'allure-results' directory in the project root
                    allure([
                        includeProperties: true,
                        jdk: '',
                        results: [
                            [path: 'allure-results']
                        ]
                    ])
                    // Publish the execution summary report from the 'execution-summary' directory in the project root
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
    }
    post {
        always {
            emailext subject: "Test Report for your build",
                body: "Find attached the test report for your build.",
                attachLog: true,
                attachmentsPattern: 'allure-results/*,execution-summary/ExecutionSummaryReport_*-AM.html',
                to: "mahmoud.ahmed@foodics.com"
        }
    }
}
