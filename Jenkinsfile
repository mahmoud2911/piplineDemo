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
                    if (isUnix()) {
                        sh 'mvn -Dmaven.test.failure.ignore clean test -Dcucumber.filter.tags=@regression -DexecutionAddress=dockerized -DtargetOperatingSystem=LINUX'
                    } else {
                        bat 'mvn -Dmaven.test.failure.ignore clean test -Dcucumber.filter.tags=@regression'
                    }
                }
            }
        }

        stage('Publish Allure to GitHub Pages') {
            steps {
                echo 'Publishing Allure results to GitHub Pages...'
                script {
                    def removeCommand = isUnix() ? 'rm -rf allure-report' : 'rmdir /s /q allure-report'
                    def moveCommand = isUnix() ? 'mv allure-report/* docs/' : 'move allure-report\\* docs\\'

                    sh removeCommand  // Remove existing allure-report directory
                    allure([
                        includeProperties: true,
                        jdk: '',
                        results: [
                            [path: 'allure-results']
                        ]
                    ])
                    sh 'mkdir -p docs'  // Create a docs directory to host Allure reports on GitHub Pages
                    sh moveCommand  // Move Allure reports to the docs directory

                    // Configure Git
                    sh 'git init'
                    sh 'git remote add origin https://github.com/your-username/your-repository.git'

                    // Delete old reports from GitHub Pages
                    sh 'git rm -r docs/*'
                    sh 'git commit -m "Remove old Allure reports"'
                    sh 'git push -u origin master'

                    // Add and commit the latest reports
                    sh 'git add docs/*'
                    sh 'git commit -m "Add latest Allure reports"'
                    sh 'git push -u origin master'
                }
            }
        }
    }

    post {
        always {
            script {
                def allureReportUrl = "https://github.com/mahmoud2911/piplineDemo/docs"
                echo "Sending email with a link to the Allure report: ${allureReportUrl}"
                emailext subject: "Test Report for your build",
                    body: "Find the Allure report here: ${allureReportUrl}",
                    to: "m666245@gmail.com"
            }
        }
    }
}