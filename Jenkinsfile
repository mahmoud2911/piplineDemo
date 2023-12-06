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
                    def mavenCommand = isUnix() ?
                        'mvn -Dmaven.test.failure.ignore clean test -Dcucumber.filter.tags=@regression' :
                        'mvn -Dmaven.test.failure.ignore clean test -Dcucumber.filter.tags=@regression'
                    sh mavenCommand
                }
            }
        }

        stage('Publish Allure to GitHub Pages') {
            steps {
                echo 'Publishing Allure results to GitHub Pages...'
                script {
                    def removeCommand = isUnix() ? 'rm -rf allure-report' : 'rmdir /s /q allure-report'
                    def moveCommand = isUnix() ? 'mv allure-report/* docs/' : 'move allure-report\\* docs\\'

                    // Run remove and move commands
                    if (isUnix()) {
                        sh removeCommand
                        allure([
                            includeProperties: true,
                            jdk: '',
                            results: [
                                [path: 'allure-results']
                            ]
                        ])
                        sh 'mkdir -p docs'
                        sh moveCommand
                    } else {
                        bat removeCommand
                        allure([
                            includeProperties: true,
                            jdk: '',
                            results: [
                                [path: 'allure-results']
                            ]
                        ])
                        bat 'mkdir docs'
                        bat moveCommand
                    }

                    // Run Git commands
                    def gitCommands = [
                        'git config user.name "mahmoud2911"',
                        'git config user.email "mahmoud.ahmed@foodics.com"',
                        'git remote add origin https://github.com/mahmoud2911/piplineDemo.git',
                        'git rm -r docs/*',
                        'git commit -m "Remove old Allure reports"',
                        'git push -u origin master',
                        'git add docs/*',
                        'git commit -m "Add latest Allure reports"',
                        'git push -u origin master'
                    ]

                    gitCommands.each { gitCmd ->
                        if (isUnix()) {
                            sh gitCmd
                        } else {
                            bat gitCmd
                        }
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