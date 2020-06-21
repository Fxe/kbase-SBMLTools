FROM kbase/kbase:sdkbase.latest
MAINTAINER fliu@anl.gov
# -----------------------------------------
# In this section, you can install any system dependencies required
# to run your App.  For instance, you could place an apt-get update or
# install line here, a git checkout to download code, or run any other
# installation scripts.

# RUN apt-get update
RUN add-apt-repository ppa:openjdk-r/ppa \
	&& sudo apt-get update \
	&& sudo apt-get -y install openjdk-8-jdk \
	&& echo java versions: \
	&& java -version \
	&& javac -version \
	&& echo $JAVA_HOME \
	&& ls -l /usr/lib/jvm \
	&& cd /kb/runtime \
	&& rm java \
	&& ln -s /usr/lib/jvm/java-8-openjdk-amd64 java \
	&& ls -l

ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64


#RUN mkdir -p /kb/deployment/opt
#RUN cd /kb/deployment/opt && wget http://193.137.11.210/fliu/jdk-8u141-linux-x64.tar.gz && tar -xf jdk-8u141-linux-x64.tar.gz && ln -sf jdk1.8.0_141/bin/java /usr/bin/java
#/usr/bin/java

# download a data files
#RUN cd /opt/ && wget http://193.137.11.210/fliu/kbase/jdk-9.0.4_linux-x64_bin.tar.gz
#RUN mkdir -p /data/integration
#RUN cd /data/integration && wget http://193.137.11.210/fliu/integration_data.zip && unzip integration_data.zip
#RUN cd /data/integration && rm -Rf export && wget http://193.137.11.210/fliu/export.tar.gz && tar -xvf export.tar.gz
#RUN tar -xf /opt/jdk-9.0.4_linux-x64_bin.tar.gz -C /opt/
#RUN rm /usr/bin/java
#RUN rm /usr/bin/javac
#RUN rm /usr/bin/javadoc
#RUN ln -s /opt/jdk-9.0.4/bin/java /usr/bin/java
#RUN ln -s /opt/jdk-9.0.4/bin/javac /usr/bin/javac
#RUN ln -s /opt/jdk-9.0.4/bin/javadoc /usr/bin/javadoc
#ENV JAVA_HOME /opt/jdk-9.0.4

# download deps
RUN cd /kb/deployment/lib/jars \
    && wget http://bioseed.mcs.anl.gov/~fxliu/kbase_build/apps/sbml_tools/jars/guava-18.0.jar \
	&& wget http://bioseed.mcs.anl.gov/~fxliu/kbase_build/apps/sbml_tools/jars/jsoup-1.11.3.jar \
    && wget http://bioseed.mcs.anl.gov/~fxliu/kbase_build/apps/sbml_tools/jars/neo4j-kernel-2.1.5.jar \
    && wget http://bioseed.mcs.anl.gov/~fxliu/kbase_build/apps/sbml_tools/jars/biojava-core-4.2.5.jar \
    && wget http://bioseed.mcs.anl.gov/~fxliu/kbase_build/apps/sbml_tools/jars/biojava-alignment-4.2.5.jar \
    && wget http://bioseed.mcs.anl.gov/~fxliu/kbase_build/apps/sbml_tools/jars/commons-lang-2.6.jar \
#    && wget http://bioseed.mcs.anl.gov/~fxliu/kbase_build/apps/sbml_tools/jars/jfasta-2.2.0-jar-with-dependencies.jar \
    && wget http://bioseed.mcs.anl.gov/~fxliu/kbase_build/apps/sbml_tools/jars/jgrapht-core-0.9.2.jar \
    && wget http://bioseed.mcs.anl.gov/~fxliu/kbase_build/apps/sbml_tools/jars/jgrapht-ext-0.9.2.jar \
    && wget http://bioseed.mcs.anl.gov/~fxliu/kbase_build/apps/sbml_tools/jars/commons-io-2.4.jar \
    && wget http://bioseed.mcs.anl.gov/~fxliu/kbase_build/apps/sbml_tools/jars/commons-lang3-3.4.jar \
    && wget http://bioseed.mcs.anl.gov/~fxliu/kbase_build/apps/sbml_tools/jars/commons-math3-3.1.1.jar \
    && wget http://bioseed.mcs.anl.gov/~fxliu/kbase_build/apps/sbml_tools/jars/commons-math-2.2.jar \
    && wget http://bioseed.mcs.anl.gov/~fxliu/kbase_build/apps/sbml_tools/jars/spring-aop-4.0.3.RELEASE.jar \
    && wget http://bioseed.mcs.anl.gov/~fxliu/kbase_build/apps/sbml_tools/jars/spring-beans-4.0.3.RELEASE.jar \
    && wget http://bioseed.mcs.anl.gov/~fxliu/kbase_build/apps/sbml_tools/jars/spring-context-4.0.3.RELEASE.jar \
    && wget http://bioseed.mcs.anl.gov/~fxliu/kbase_build/apps/sbml_tools/jars/spring-core-4.0.3.RELEASE.jar \
    && wget http://bioseed.mcs.anl.gov/~fxliu/kbase_build/apps/sbml_tools/jars/antlr-runtime-3.5.2.jar \
    && wget http://bioseed.mcs.anl.gov/~fxliu/kbase_build/apps/sbml_tools/jars/javaluator-3.0.2.jar \
    && wget http://bioseed.mcs.anl.gov/~fxliu/kbase_build/apps/sbml_tools/jars/jbool_expressions-1.14.jar \
    && wget http://bioseed.mcs.anl.gov/~fxliu/kbase_build/apps/sbml_tools/jars/ahocorasick-0.4.0.jar

RUN cd /kb/deployment/lib/jars \
    && wget http://bioseed.mcs.anl.gov/~fxliu/kbase_build/apps/sbml_tools/jars/kbase-0.0.1-SNAPSHOT.jar \
    && wget http://bioseed.mcs.anl.gov/~fxliu/kbase_build/apps/sbml_tools/jars/biosynth-biodb-0.9.0-JRE7.jar \
    && wget http://bioseed.mcs.anl.gov/~fxliu/kbase_build/apps/sbml_tools/jars/biosynth-core-0.9.0-JRE7.jar \
    && wget http://bioseed.mcs.anl.gov/~fxliu/kbase_build/apps/sbml_tools/jars/biosynth-biodb-modelseed-0.9.0-JRE7.jar \
    && wget http://bioseed.mcs.anl.gov/~fxliu/kbase_build/apps/sbml_tools/jars/biosynth-genome-0.9.0-JRE7.jar \
    && wget http://bioseed.mcs.anl.gov/~fxliu/kbase_build/apps/sbml_tools/jars/biosynth-integration-0.9.0-JRE7.jar \
    && wget http://bioseed.mcs.anl.gov/~fxliu/kbase_build/apps/sbml_tools/jars/biosynth-aux-0.0.1-SNAPSHOT.jar

    
#RUN cd /kb/deployment/lib/jars \
#    && wget http://193.137.11.210/fliu/all-deps.zip && unzip all-deps.zip
#RUN wget http://193.137.11.210/fliu/all-deps.zip
#RUN unzip all-deps.zip
#RUN rm all-deps.zip
#RUN cd /kb/deployment/lib/jars \
#    && wget http://193.137.11.210/fliu/biojava-core-4.2.5.jara


COPY ./ /kb/module
RUN mkdir -p /kb/module/work
RUN chmod -R a+rw /kb/module

WORKDIR /kb/module

RUN make all

ENTRYPOINT [ "./scripts/entrypoint.sh" ]

CMD [ ]
