pipeline{
   agent any
    stages{
        stage('SCM Checkout'){
            steps{
                git url: "https://github.com/laxmi2018/lk_test.git"
                branches: [{name: '*/main'}]
            }
        }
        stages('archiveArtifacts'){
            steps{
                archiveArtifatcs '**/*.html'
            }
        }
        stage('Deployment'){
            steps{
                sshpublisher(publishers:[sshPublisherDesc(configName:'webserver')],[sshTransfer(exclude:'',execCommand:'',execTimeout:120000,flatten:true,makeEmptyDirs:flase,noDefaultExclude:false,patternSeparator:'[,]+',remoteDirectory:'',remoteDirectorySDF:false,removePrefix:'',sourceFiles:'**/*.html')],userPromotionTimestamp:false,useWorkspacePro)])
            }
        }
    }
}