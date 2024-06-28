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

        stage('Find Docker Compose Path') {
            steps {
                script {
                    DOCKER_COMPOSE_PATH = sh(script: 'which docker-compose', returnStdout: true).trim()
                    echo "Docker Compose Path: ${DOCKER_COMPOSE_PATH}"
                }
            }
        }

        stage('Git-checkout') {
            steps {
                dir("backend") {
                    git branch: 'main', url: 'https://github.com/Harintharan/git_good_back.git'
                    sh 'ls -al'
                }
            }
        }

        stage('Build Backend') {
            steps {
                dir('backend') {
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
                sh 'ls -al'
            }
        }

        stage('Build Frontend') {
            steps {
                dir('my-react-app') {
                    script {
                        sh 'docker build -t ${FRONTEND_IMAGE} .'
                    }
                }
            }
        }

        stage('Deploy with Docker Compose') {
            steps {
                script {
                                    // Ensure we are in the directory containing docker-compose.yml
                                    dir('.') {
                                        withEnv(["PATH+DOCKER_COMPOSE=${DOCKER_COMPOSE_PATH}"]) {
                                            sh '''
                                                docker-compose down
                                                docker-compose up -d
                                            '''
                                        }
                                    }
                                }
            }
        }
    }
}
