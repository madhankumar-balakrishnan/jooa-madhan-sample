node {
	echo 'Microservice CI/CD Pipeline - JOOQ-MADHAN Service ' 
	echo "#################### Initiating ${env.BRANCH_NAME} Build ####################"
		
    properties([pipelineTriggers([githubPush()])])
	
	stage ('Pull Repository') {
		checkout scm
	}	
}