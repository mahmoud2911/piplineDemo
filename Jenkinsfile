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
                    def mavenCommand = "mvn -Dmaven.test.failure.ignore clean test -Dcucumber.filter.tags=@regression"

                    // Use the appropriate shell based on the OS
                    sh (isUnix() ? mavenCommand : "cmd /c $mavenCommand")
                }
            }
        }

    stage('Commit to GitHub') {
        steps {
            script {
                def userEmail = "mahmoud.ahmed@foodics.com"
                def userName = "mahmoud2911"

                // Set user email and name based on OS
                if (isUnix()) {
                    sh "git config --global user.email '${userEmail}'"
                    sh "git config --global user.name '${userName}'"
                } else {
                    // For Windows
                    bat "git config --global user.email '${userEmail}'"
                    bat "git config --global user.name '${userName}'"
                }

               // Commit and push to master branch
               if (isUnix()) {
                   sh 'git add allure-results'
                   sh 'git commit -m "Add Allure report"'
                   sh 'git push origin master'
               } else {
                   // For Windows
                   bat 'git add allure-results'
                   bat 'git commit -m "Add Allure report"'
                   bat 'git push origin master'
               }

                // Switch to gh-pages branch and publish Allure report
                if (isUnix()) {
                    sh 'git checkout -b gh-pages'
                    sh 'git rm -rf .'
                    sh 'cp -r allure-report/* .'
                    sh 'git add .'
                    sh 'git commit -m "Publish Allure report to GitHub Pages"'
                    sh 'git push origin gh-pages'
                } else {
                    // For Windows
                    bat 'git checkout -b gh-pages'
                    bat 'git rm -rf .'
                    bat 'xcopy /s allure-report .'
                    bat 'git add .'
                    bat 'git commit -m "Publish Allure report to GitHub Pages"'
                    bat 'git push origin gh-pages'
                }
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
                        results: [[path: 'allure-results']]
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
    }

    post {
        always {
            echo 'Sending email with reports...'

            script {
                def projectName = env.JOB_NAME
                def buildNumber = env.BUILD_NUMBER
                def buildStatus = currentBuild.currentResult

                def customSubject = "${projectName} - Build #${buildNumber} - ${buildStatus} - Test Results"
                def allureReportUrl = "https://mahmoud2911.github.io/pipelineDemo/"
                emailext attachmentsPattern: 'execution-summary/*.html',
                        body: "Find the Allure report here: ${allureReportUrl}",
                        mimeType: 'text/html',
                        subject: customSubject,
                        to: 'mahmoud.ahmed@foodics.com'
            }
        }
    }
}
