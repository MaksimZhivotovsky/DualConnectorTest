pipeline {
    options {
        buildDiscarder(logRotator(numToKeepStr: '10'))
    }

    environment {
        CI = false
    }

    stages {

        stage('Build Stage') {
            steps {
                echo "Build Stage"
                sh '''echo off
                    echo "============ java -version ============"
                    java -version
                    echo "======================================="
                    ls -alh
                    echo "======================================="
                    mvn --version
                    echo "======================================="
                    mvn clean install -DskipTests'''
            }
        } //end stage

        stage('Deploy Stage') {
            steps {
                echo "Deploy Stage"
                sh '''echo off
                    echo "============ java -version ============"
                    java -version
                    echo "======================================="
                    mvn deploy -DskipTests'''
            }
        } //end stage

    } //end stages

    post {
        always {
            emailext body: '$DEFAULT_CONTENT', recipientProviders: [developers(), requestor()], subject: '$DEFAULT_SUBJECT'
        }
    }

} // end pipeline
