pipeline {
    agent any

    tools {
        maven 'MAVEN_HOME'
        git 'Default'
        dockerTool 'DOCKER_HOME'
        jdk 'JAVA_HOME'

    }

    environment {
        DB_IMAGE = 'mysql:8.0.35'
        BACKEND_IMAGE = 'back-blood'
        FRONTEND_IMAGE = 'front-blood'
       // DOCKER_COMPOSE_PATH = '/usr/local/bin'
        DOCKER_USERNAME = credentials('DOCKER_HUB_USERNAME')
        DOCKER_PASSWORD = credentials('DOCKER_HUB_PASSWORD')

    }

    stages {


        // stage('Docker Login') {
        //     steps {
        //         script {
        //             sh "echo '${DOCKER_PASSWORD}' | docker login -u '${DOCKER_USERNAME}' --password-stdin"
        //         }
        //     }
        // }

        stage('Git-checkout') {
            steps {
                dir("backend"){
                    git branch:'main', url:'https://github.com/Harintharan/git_good_back.git'
                    sh'ls -al'
                }

            }

        }

         stage('Build Backend') {
            steps {
                    dir('backend'){
                    script {
                            sh 'mvn clean install'
                            try {
                                sh 'docker build -t ${BACKEND_IMAGE} .'
                            } catch (Exception e) {
                                echo "Docker build failed: ${e}"
                                currentBuild.result = 'FAILURE'
                                error("Docker build failed")
                            }
                        }
                    }

            }
        }

         stage('Checkout Frontend') {
            steps {


                    git url: 'https://github.com/Harintharan/Blood_bank_front.git', branch: 'main'
                    sh 'ls -al'}


        }

         stage('Build Frontend') {
            steps {
                dir('my-react-app'){
                    script {
                        sh 'docker build -t ${FRONTEND_IMAGE} .'
                    }
                }
            }
        }





    }





}