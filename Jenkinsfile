pipeline {
    agent any
    tools {
        maven 'Maven 3.9.5'
        allure 'Allure 2.24.1'
        dockerTool 'Docker LTS' // You may need to replace 'docker' with the actual tool name as configured in your Jenkins instance
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
        stage('Run Tests on Selenium Grid') {
            steps {
                script {
                    def browsers = ['chrome', 'MicrosoftEdge']
                    for (def browser in browsers) {
                        // Start a Docker container for the selected browser
                        def browserContainer = "selenium/node-${browser}:4.1.1"
                        def browserName = browser == 'chrome' ? 'google-chrome' : 'MicrosoftEdge'
                        def containerName = "${browser}-node"
                        if (isUnix()) {
                            sh "docker pull ${browserContainer}"
                            sh "docker run -d --link selenium-hub:hub --name ${containerName} -v /dev/shm:/dev/shm ${browserContainer}"
                        } else {
                            bat "docker pull ${browserContainer}"
                            bat "docker run -d --link selenium-hub:hub --name ${containerName} -v /dev/shm:/dev/shm ${browserContainer}"
                        }

                        // Run your tests on the current browser
                        if (isUnix()) {
                            sh "mvn -Dmaven.test.failure.ignore clean test -Dcucumber.filter.tags=@regression -DtargetBrowserName=${browserName}"
                        } else {
                            bat "mvn -Dmaven.test.failure.ignore clean test -Dcucumber.filter.tags=@regression -DtargetBrowserName=${browserName}"
                        }
                        if (isUnix()) { // Stop and remove the container
                            sh "docker stop ${containerName}"
                            sh "docker rm ${containerName}"
                        } else {
                            // Stop and remove the container
                            bat "docker stop ${containerName}"
                            bat "docker rm ${containerName}"
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