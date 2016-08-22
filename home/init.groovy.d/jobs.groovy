import jenkins.model.*;
import static groovy.io.FileType.*
import static groovy.io.FileVisitResult.*

def jobDir = new File('/home/vcap/.jenkins/jobs-config')

createJobs(dirMap(jobDir))
runSeed()

def createJobs(map){
  map.each {
    jobName, file ->
    def stream = new ByteArrayInputStream(file.text.getBytes())
    Jenkins.getInstance().createProjectFromXML(jobName, stream)
  }
}

def runSeed(){
  def seed = Jenkins.getInstance().getItem("seed")
  Jenkins.getInstance().getQueue().schedule(seed)
}

def dirMap(File source){
  def map = [:]
  source.traverse(type: DIRECTORIES) {
    dir -> null
    dir.traverse(type: FILES, nameFilter: ~/.*\.xml$/) {
         map.put(dir.name, it)
    }
  }
  return map
}
