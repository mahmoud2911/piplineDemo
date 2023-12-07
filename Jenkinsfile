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
                    if (isUnix()) {
                        sh mavenCommand
                    } else {
                        bat mavenCommand
                    }
                }
            }
        }
     stage('Generate Allure Report') {
       steps {
           script {
               // Use the configured Allure installation
               def allureExecutable = tool 'Allure 2.24.1'

               // Run the Allure command to generate the report
               if (isUnix()) {
                   sh "${allureExecutable}/bin/allure generate allure-results -o allure-report --clean"
               } else {
                   bat "${allureExecutable}\\bin\\allure generate allure-results -o allure-report --clean"
               }
           }
       }
       }
        stage('Publish Allure and Execution Summary Reports in jenkins') {
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
        stage('Publish to GitHub Pages') {
            steps {
                script {
                    // Set Git user configuration
                   // def gitConfigCmd = isUnix() ? 'git config' : 'git config.exe'
                   // sh "$gitConfigCmd --global user.email 'mahmoud.ahmed@foodics.com'"
                    //sh "$gitConfigCmd --global user.name 'mahmoud2911'"
                  // Define the Git executable based on the operating system
                              def gitExecutable = isUnix() ? 'sh' : 'bat'

                              // Set Git user configuration
                              if (isUnix()) {
                                  sh '''
                                      git config --global user.email 'mahmoud.ahmed@foodics.com'
                                      git config --global user.name 'mahmoud2911'
                                  '''
                              } else {
                                  bat '''
                                      git config --global user.email 'mahmoud.ahmed@foodics.com'
                                      git config --global user.name 'mahmoud2911'
                                  '''
                              }

        // Publish to master branch
        if (isUnix()) {
            sh 'git branch --show-current | grep -q "master" || git checkout master'
            sh 'git pull origin master'  // Pull changes from remote master
            sh 'git add allure-results'
            sh 'git commit -m "Add Allure report"'
            sh 'git push origin master'
        } else {
            bat 'git branch | findstr /C:"* master" || git checkout master'
            bat 'git pull origin master'  // Pull changes from remote master
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
    }

    post {
        always {
            // Send email with reports
            echo 'Sending email with reports...'
            script {
                // Get the project name, build number, and build status
                def projectName = env.JOB_NAME
                def buildNumber = env.BUILD_NUMBER
                def buildStatus = currentBuild.currentResult

                // Build the custom subject with an identifier for test results
                def customSubject = "${projectName} - Build #${buildNumber} - ${buildStatus} - Test Results"
                // GitHub Pages URL for Allure report
                def allureReportUrl = "https://mahmoud2911.github.io/pipelineDemo/allure-report"
                emailext attachmentsPattern: 'execution-summary/*.html',
                        body: "Find the Allure report here: ${allureReportUrl}",
                        mimeType: 'text/html',
                        subject: customSubject,
                        to: 'mahmoud.ahmed@foodics.com'
            }
        }
    }
}
