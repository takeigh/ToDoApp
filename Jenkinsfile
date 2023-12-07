pipeline {
    agent any

    stages {
        stage('Compile') {
            steps {
                // Command to compile the code since there is no gradle
                sh 'javac -cp lib/junit-4.13.1.jar -d target src/**/*.java'
            }
        }

        stage('Run') {
            steps {
                // Run the program
                sh 'java -cp target:lib/* src/com.zynozin.Main'
            }
        }

        stage('Cleanup') {
            steps {
                sh 'rm -rf target'
            }
        }
    }

    post {
        success {
            // Actions to perform when the build is successful
            echo 'Build and Run successful!'

            // You can add additional actions or notifications here
        }

        failure {
            // Actions to perform when the build fails
            echo 'Build or Run failed!'

            // You can add additional actions or notifications here
        }
    }
}