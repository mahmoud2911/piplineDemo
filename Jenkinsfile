pipeline {
    agent any
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
                    bat returnStatus: true, script: "\"C:\\Program Files\\JetBrains\\IntelliJ IDEA Community Edition 2023.1.2\\plugins\\maven\\lib\\maven3\\bin\\mvn.bat\" -Dmaven.test.failure.ignore clean package"
                 }
            }
        }
    }
}
