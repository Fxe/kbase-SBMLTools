FROM kbase/kbase:sdkbase.latest
MAINTAINER KBase Developer
# -----------------------------------------
# In this section, you can install any system dependencies required
# to run your App.  For instance, you could place an apt-get update or
# install line here, a git checkout to download code, or run any other
# installation scripts.

# RUN apt-get update


#RUN mkdir -p /kb/deployment/opt
#RUN cd /kb/deployment/opt && wget http://193.137.11.210/fliu/jdk-8u141-linux-x64.tar.gz && tar -xf jdk-8u141-linux-x64.tar.gz && ln -sf jdk1.8.0_141/bin/java /usr/bin/java
#/usr/bin/java

# download a data files
RUN cd /opt/ && wget http://193.137.11.210/fliu/kbase/jdk-9.0.4_linux-x64_bin.tar.gz
RUN mkdir -p /data/integration
RUN cd /data/integration && wget http://193.137.11.210/fliu/integration_data.zip && unzip integration_data.zip
RUN cd /data/integration && rm -Rf export && wget http://193.137.11.210/fliu/export.tar.gz && tar -xvf export.tar.gz
RUN tar -xf /opt/jdk-9.0.4_linux-x64_bin.tar.gz -C /opt/
RUN rm /usr/bin/java
RUN rm /usr/bin/javac
RUN rm /usr/bin/javadoc
RUN ln -s /opt/jdk-9.0.4/bin/java /usr/bin/java
RUN ln -s /opt/jdk-9.0.4/bin/javac /usr/bin/javac
RUN ln -s /opt/jdk-9.0.4/bin/javadoc /usr/bin/javadoc
ENV JAVA_HOME /opt/jdk-9.0.4

# download deps
RUN cd /kb/deployment/lib/jars \
    && wget http://193.137.11.210/fliu/kbase/guava-18.0.jar \
    && wget http://193.137.11.210/fliu/kbase/biojava-core-4.2.5.jar \
    && wget http://193.137.11.210/fliu/kbase/biojava-alignment-4.2.5.jar \
    && wget http://193.137.11.210/fliu/kbase/commons-lang-2.6.jar \
    && wget http://193.137.11.210/fliu/kbase/jfasta-2.2.0-jar-with-dependencies.jar \
    && wget http://193.137.11.210/fliu/kbase/jgrapht-core-0.9.2.jar \
    && wget http://193.137.11.210/fliu/kbase/jgrapht-ext-0.9.2.jar \
    && wget http://193.137.11.210/fliu/kbase/neo4j-kernel-2.1.5.jar \
    && wget http://193.137.11.210/fliu/kbase/commons-io-2.4.jar \
    && wget http://193.137.11.210/fliu/kbase/commons-lang3-3.4.jar \
    && wget http://193.137.11.210/fliu/kbase/commons-math3-3.1.1.jar \
    && wget http://193.137.11.210/fliu/kbase/commons-math-2.2.jar \
    && wget http://193.137.11.210/fliu/kbase/spring-aop-4.0.3.RELEASE.jar \
    && wget http://193.137.11.210/fliu/kbase/spring-beans-4.0.3.RELEASE.jar \
    && wget http://193.137.11.210/fliu/kbase/spring-context-4.0.3.RELEASE.jar \
    && wget http://193.137.11.210/fliu/kbase/spring-core-4.0.3.RELEASE.jar \
    && wget http://193.137.11.210/fliu/kbase/antlr-runtime-3.5.2.jar \
    && wget http://193.137.11.210/fliu/kbase/javaluator-3.0.2.jar \
    && wget http://193.137.11.210/fliu/kbase/jbool_expressions-1.14.jar \
    && wget http://193.137.11.210/fliu/kbase/biosynth-core-0.0.1-SNAPSHOT.jar \
    && wget http://193.137.11.210/fliu/kbase/biosynth-integration-0.0.1-SNAPSHOT.jar \
    && wget http://193.137.11.210/fliu/kbase/biosynth-aux-0.0.1-SNAPSHOT.jar \
    && wget http://193.137.11.210/fliu/kbase/biosynth-genome-0.0.1-SNAPSHOT.jar \
    && wget http://193.137.11.210/fliu/kbase/biosynth-biodb-0.0.1-SNAPSHOT.jar \
    && wget http://193.137.11.210/fliu/kbase/ahocorasick-0.4.0.jar

    
#RUN cd /kb/deployment/lib/jars \
#    && wget http://193.137.11.210/fliu/all-deps.zip && unzip all-deps.zip
#RUN wget http://193.137.11.210/fliu/all-deps.zip
#RUN unzip all-deps.zip
#RUN rm all-deps.zip
#RUN cd /kb/deployment/lib/jars \
#    && wget http://193.137.11.210/fliu/biojava-core-4.2.5.jara


#RUN echo $(java -version)
#RUN echo $(which java)
#RUN echo $(ls -la /usr/bin/java)
    
# -----------------------------------------

COPY ./ /kb/module
RUN mkdir -p /kb/module/work
RUN chmod -R a+rw /kb/module

WORKDIR /kb/module

RUN make all

ENTRYPOINT [ "./scripts/entrypoint.sh" ]

CMD [ ]
