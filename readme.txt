Some information about project:

Technologies, frameworks, etc:

1.Build manager - Maven
2.Front-end - JSF MyFaces
3.Servlet Server - Glassfish 4.0
4.ORM - Hibernate
5.Context and dependency injection - weld
6.JSF component container - primeFaces (don't know whether we really need it.)
7. Java EE 7

JPA , javax validation, annotation, etc.

How to build project:

1.Download and install Java EE 7 SDK with glassfish.
2.Glassfish will be started automatically. You should stop it: ../glassfish/bin/stopserv.bat
    I've forgot about maven when I was at 15 item of this list :'( Don't want to change them all:
    a) install maven 3.1
    b) register in your system environments (you can make it from intellig/setting/mvn too)
3.Download and install MariaDB 10
4.MariaDB has internal tool for DB administration - HeidiSQL. Start it, by starting add password and login: root, root
5.Create DB "ctodb". You don't need to create tables or smth else within. It will be done by Hibernate later automatically
6.Register you in github.com, download and install git http://windows.github.com/
7.Generate and register SSH for git https://help.github.com/articles/generating-ssh-keys
8.Now go in IDE (hope it is Intellj, because I don't know how to make it in Eclipse :D).
9.Go in settings and check whether your github/glassfish/hibernate/git/jsf/CDI/    plugins are installed (Settings/plugins)
10.In settings/Version Control/Git provide your path to Git.exe. Git actually doesn't install normally (I mean path), that's why
    for me it is smth like C:\Users\vishn_000\AppData\Local\GitHub\PortableGit_015aa71ef18c047ce8509ffb2f9e4bb0e3e73f13\bin and here you have git.exe
11. In settings/Version Control/Github provide host: github.com, your login and password. Click test :)
12. Panel Control - VCS - Checkout From Version Control - GitHub. Provide link to git   https://github.com/SeriousSem/CTOProject.git etc. OK
13. Now you have a project. You should add Glassfish server plugin in your IDE. Right click on top package in Package tree - add new framework - server - glassfish 4.0.0 (smth like this).
14. Now configure server. Panel Control - EditConfiguration - add glassfishServer - deployment - "+" - CTOProject:war.
15. Now you can start your server. Project will be automatically deployed by clicking green play button
16. Don't know your context path to project for me it is like this http://localhost:8080/CTOProject-1.0-SNAPSHOT/ . Go to this page http://localhost:8080/CTOProject-1.0-SNAPSHOT/addCustomer.jsf
add some datas, click Submit. Your database schema should be automatically created and your data will be added in "Customer" table.

That's all I think. Write me if you have questions.
I really hope that you make it to Monday, because in this case we can start to discuss our project, but not a technical details like these.
