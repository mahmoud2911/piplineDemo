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
                sh 'mvn clean package'
            }
        }
    }
}
