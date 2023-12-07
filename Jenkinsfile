pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Check out the source code from version control
                checkout scm
            }
        }

        stage('Compile') {
            steps {
                // Compile your Java code (replace 'javac' with your actual compile command)
                sh 'javac -d target src/**/*.java'
            }
        }

        stage('Run') {
            steps {
                // Run your Java program (replace 'java' with your actual run command)
                sh 'java -cp target com.zynozin.Main'
            }
        }

        stage('Test') {
            steps {
                // Run the tests
                sh ''
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