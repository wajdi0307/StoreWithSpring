pipeline {
    agent any
environment {
        registry = "wajdi03/storewithspring"
        registryCredential = 'dockerHub'
        dockerImage = ''
        NEXUS_VERSION = "nexus3"
        NEXUS_PROTOCOL = "http"
        NEXUS_URL = "192.168.1.103:8081"
        NEXUS_REPOSITORY = "maven-releases"
        NEXUS_CREDENTIAL_ID = "nexus-user-credentials"
    }

    stages {
        stage('Hello') {
            steps {
                echo 'Hello World'
            }
        }
        stage("Maven CLEAN"){
            steps {
                sh "mvn clean"

            }
        }
         stage('Maven PACKAGE') {
		 steps {
			    sh "mvn package"
			  }
		  }
         stage("Maven COMPILE"){
            steps {
                sh"mvn compile"

            }
        }
        stage("Publish to Nexus Repository Manager") {
            steps {
                script {
                    pom = readMavenPom file: "pom.xml";
                    filesByGlob = findFiles(glob: "target/*.${pom.packaging}");
                    echo "${filesByGlob[0].name} ${filesByGlob[0].path} ${filesByGlob[0].directory} ${filesByGlob[0].length} ${filesByGlob[0].lastModified}"
                    artifactPath = filesByGlob[0].path;
                    artifactExists = fileExists artifactPath;
                    if(artifactExists) {
                        echo "*** File: ${artifactPath}, group: ${pom.groupId}, packaging: ${pom.packaging}, version ${pom.version}";
                        nexusArtifactUploader(
                            nexusVersion: NEXUS_VERSION,
                            protocol: NEXUS_PROTOCOL,
                            nexusUrl: NEXUS_URL,
                            groupId: pom.groupId,
                            version: pom.version,
                            repository: NEXUS_REPOSITORY,
                            credentialsId: NEXUS_CREDENTIAL_ID,
                            artifacts: [
                                [artifactId: pom.artifactId,
                                classifier: '',
                                file: artifactPath,
                                type: pom.packaging],
                                [artifactId: pom.artifactId,
                                classifier: '',
                                file: "pom.xml",
                                type: "pom"]
                            ]
                        );
                    } else {
                        error "*** File: ${artifactPath}, could not be found";
                    }
                }
            }
        }
         stage('JUNIT TESTS') {
            steps {
                sh 'mvn test'
            }
        }
        stage('Maven SONARQUBE') {
            steps {
                sh "mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=wajdi54040245"
            }
        }
        stage('Building our image') {
            steps {
                script {
                    dockerImage = docker.build registry + ":$BUILD_NUMBER"
                }
            }
        }

        stage('Deploy our image') {
            steps {
                script {
                    docker.withRegistry( '', registryCredential ) {
                        dockerImage.push()
                    }
                }
            }
        }


        stage('DOCKER COMPOSE') {
             steps {
                sh 'docker-compose up -d '
            }
        }

    }
       post {
          success {
                         mail to: "wajdi.bel.haj.yahya@gmail.com",
                         subject: "Pipeline Success",
                         body: "success on job ${env.JOB_NAME}, Build Number: ${env.BUILD_NUMBER}, Build URL: ${env.BUILD_URL}"
                     }
                     failure {
                         mail to: "wajdi.bel.haj.yahya@gmail.com",
                         subject: "Pipeline Failure",
                         body: "Failure on job ${env.JOB_NAME}, Build Number: ${env.BUILD_NUMBER}, Build URL: ${env.BUILD_URL} "
                     }
     }

}
