pipeline {
    agent any

    tools {
        jdk 'jdk21'
        maven 'maven3'
        git 'Default'
    }

    stages {
        stage('Git-checkout') {
            steps {
                git branch:'main', url:'https://github.com/Harintharan/git_good_back.git'
                sh'ls -al'
            }
        }




    }
}
