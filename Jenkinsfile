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
                sh 'mvn -Dmaven.test.failure.ignore clean test -Dcucumber.filter.tags=@regression'
            }
        }

        stage('Publish Allure to GitHub Pages') {
            steps {
                echo 'Publishing Allure results to GitHub Pages...'
                script {
                    def gitInit = !fileExists('.git')
                    if (gitInit) {
                        sh 'git init'
                        sh 'git remote add origin https://github.com/mahmoud2911/piplineDemo.git'
                    }

                    sh 'rm -rf allure-report' // Remove existing allure-report directory
                    allure([
                        includeProperties: true,
                        jdk: '',
                        results: [
                            [path: 'allure-results']
                        ]
                    ])
                    sh 'mkdir -p docs' // Create a docs directory to host Allure reports on GitHub Pages
                    sh 'mv allure-report/* docs/' // Move Allure reports to the docs directory

                    if (gitInit) {
                        sh 'git add .'
                        sh 'git commit -m "Initial commit"'
                        sh 'git push -u origin master'
                    } else {
                        sh 'git rm -r docs/*'
                        sh 'git commit -m "Remove old Allure reports"'
                        sh 'git push -u origin master'

                        sh 'git add docs/*'
                        sh 'git commit -m "Add latest Allure reports"'
                        sh 'git push -u origin master'
                    }
                }
            }
        }
    }

    post {
        always {
            script {
                def allureReportUrl = "https://mahmoud2911.github.io/piplineDemo/"
                echo "Sending email with a link to the Allure report: ${allureReportUrl}"
                emailext subject: "Test Report for your build",
                    body: "Find the Allure report here: ${allureReportUrl}",
                    to: "m666245@gmail.com"
            }
        }
    }
}