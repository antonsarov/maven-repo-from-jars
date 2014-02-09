maven-repo-from-jars
====================

Eclipse plugin enabling easier creation of project specific Maven repositories from JAR files

## Description ##

### tl;dr ###

Use this plugin to create a project specific Maven repository from local JAR files

### The long version ###

If you are using Maven for your project and relying on third party libraries chances are you are going to find them in the Maven Central Repository.
However sometimes you have 3rd party libraries or your own proprietary ones in the form of JAR files and they are not available in the Maven Central Repository.
There are two popular approaches of how to handle this situation:

- install the JARs to your local repository
- set the scope of the dependency to *system*

As both of these options are not optimal, you have to go with the option of creating a project specific repository. The problem arises when you have to deal with more than one JAR file - then you need to install each of the JARs separately. The plugin here provides a GUI to ease the steps you need to perform.

## What is in there ##

The workflow is defined by a Wizard (in the Eclipse plugin). A feature project is included to ease the installation.

When you install the plugin in Eclipse you will see an icon in the toolbar which you can use to start the Wizard

![](toolbar_icon.png)  