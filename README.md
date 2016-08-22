# Jenkins buildpack for Cloud Foundry

### Description

This is special buildpack to run jenkins inside Cloud Foundry from war file taken from [the official site](http://mirrors.jenkins-ci.org/war/latest/jenkins.war). 

This buildpack intended to be just PoC, showing that the range applications that can be run of Cloud Foundry is very wide. You can get more details on how and why this buildpack was built in [this blog post](http://blog.altoros.com/creating-a-custom-cloud-foundry-buildpack-from-scratch-whats-under-the-hood.html). You need to understand that Jenkins deployed to Cloud Foundry with this buildpack has a number of limitations, for instance it uses file system to store blobs and can't be scaled. This is why it should be additionally configured. For production grade deployments I would recomend to use [jenkins boshreleas](https://github.com/cloudfoundry-community/jenkins-boshrelease).

### How to use

To run app with this buildpack you need do the following: 

```
cf push jenkins-app-name -p jenkins.war -m 4G -b https://github.com/Altoros/jenkins-buildpack
```

Here are descriptions of each parameter:

* `jenkins-app-name` is the name of application inside of Cloud Foundry.
* `-p <path>` shows Cloud Foundry CLI where to take sources or binaries to run the app.
* `-m <memory-quota>` stands for memory limit for the app, jenkins 2.0 with java 8 seems to require somrthing like 4Gb of memory for 
* `-b <buildpack>` sets what buildpack should be used to run this app; if you specify repo URL, CF will fetch it.

### Jenkins 2.0+

If you deploy Jenkins 2.0+, it will automatically generate admin password that will be used for first enter. You can see a message with this password using following command:

```
cf logs jenkins-2-test --recent | grep -B 1 -A 2 "following password"
```

Each time you run your app in another container, this password will be changed.

### Contribution and ideas

If you have any ideas that can be added, you can [email me directly](mailto:lomov.as@gmail.com).

May CI be with you!
