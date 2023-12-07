pipeline {
    agent any

    stages {
        stage('Compile') {
            steps {
                // Command to compile the code since there is no gradle
                sh 'javac -cp lib/junit-4.13.1.jar -d target src/com/zynozin/*.java src/components/*.java src/tests/*.java'
            }
        }

        stage('Run') {
            steps {
                // Command to run
                sh 'java -cp out/production/ToDo com.zynozin.Main'
            }
        }
    }

    post {
        success {
            echo 'Compile successful!'
        }

        failure {
            echo 'Compile failed!'
        }
    }
}