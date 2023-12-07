pipeline {
    agent any

    stages {
        stage('Compile') {
            steps {
                // Command to compile the code since there is no gradle
                def classpathSeparator = isUnix() ? ':' : ';'
                def classpath = "lib/hamcrest-core-1.3.jar${classpathSeparator}lib/junit-4.13.1.jar"
                sh 'javac -cp ${classpath} -d target src/com.zyconin/*.java'
            }
        }

        stage('Run') {
            steps {
                // Run your Java program (replace 'java' with your actual run command)
                sh 'java -cp target:lib/* com.zynozin.Main'
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