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
                    def mavenCommand = 'mvn -Dmaven.test.failure.ignore clean test -Dcucumber.filter.tags=@regression'
                    if (isUnix()) {
                        sh mavenCommand
                    } else {
                        bat mavenCommand
                    }
                }
            }
        }
        stage('Publish Allure to GitHub Pages') {
            steps {
                echo 'Publishing Allure results to GitHub Pages...'
                script {
                    def removeCommand = isUnix() ? 'rm -rf allure-results' : 'rmdir /s /q allure-results'
                    def moveCommand = isUnix() ? 'mv allure-results/* .' : 'move allure-results\\* .'
                    def gitCmdPrefix = isUnix() ? 'sh' : 'bat'

                    // Run remove and move commands
                    "${gitCmdPrefix} ${removeCommand}"

                    allure([
                        includeProperties: true,
                        jdk: '',
                        results: [
                            [path: 'allure-results']
                        ]
                    ])

                    // Create "docs" directory if it doesn't exist
                    if (isUnix()) {
                        sh 'mkdir -p docs'
                    } else {
                        bat 'if not exist docs mkdir docs'
                    }

                    // Run move command
                    "${gitCmdPrefix} ${moveCommand}"

                    // Run Git commands
                    def gitCommands = [
                        'git config user.name "mahmoud2911"',
                        'git config user.email "mahmoud.ahmed@foodics.com"',
                        'git remote rm origin', // Remove existing remote (if any)
                        'git remote add origin https://github.com/mahmoud2911/piplineDemo.git',
                        'git rm -r docs/*',
                        'git commit -m "Remove old Allure reports"',
                        'git push -u origin master',
                        'git add *',
                        'git commit -m "Add latest Allure reports"',
                        'git push -u origin master'
                    ]

                    gitCommands.each { gitCmd ->
                        "${gitCmdPrefix} ${gitCmd}"
                    }
                }
            }
        }

      }

      post {
          always {
              script {
                  def allureReportUrl = "https://github.com/mahmoud2911/piplineDemo/"
                  echo "Sending email with a link to the Allure report: ${allureReportUrl}"
                  emailext subject: "Test Report for your build",
                      body: "Find the Allure report here: ${allureReportUrl}",
                      to: "m666245@gmail.com"
              }
          }
      }
  }