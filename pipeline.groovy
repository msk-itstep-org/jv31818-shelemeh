
pipeline{
       agent any
       tools{
        maven 'Maven'
       }
       stages{
        stage("build"){
            when{
                expression{
                    BRANCH_NAME =='version2'
                }
            }
            steps{
                echo 'building the app..'
                sh "mvn clean compile"
            }
        }
       }
}