pipeline {
    agent any
      tools {
            // Define the Maven tool and version to use
            maven 'Maven 3.9.5'
        }
    stages {
        stage('Checkout') {
            steps {
                // Check out the code from the GitHub repository using the token
               checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/mahmoud2911/piplineDemo']]])
            }
        }
        stage('Build') {
            steps {
                script {
                      bat returnStatus: true, script: 'mvn -Dmaven.test.failure.ignore clean package'
                 }
            }
        }
    }
}
