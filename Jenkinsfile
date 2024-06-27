pipeline {
    agent any

    tools {
        jdk 'JAVA_HOME'
        maven 'maven3'
        git 'Git Default'
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
