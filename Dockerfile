FROM kbase/kbase:sdkbase.latest
MAINTAINER KBase Developer
# -----------------------------------------
# In this section, you can install any system dependencies required
# to run your App.  For instance, you could place an apt-get update or
# install line here, a git checkout to download code, or run any other
# installation scripts.

# RUN apt-get update

# download a data fiels
RUN mkdir -p /data/integration
RUN cd /data/integration && wget http://193.137.11.210/fliu/integration_data.zip && unzip integration_data.zip

# download a fasta reader/writer
RUN cd /kb/deployment/lib/jars \
    && wget http://193.137.11.210/fliu/kbase/jfasta-2.2.0-jar-with-dependencies.jar \
    && wget http://193.137.11.210/fliu/kbase/biosynth-core-0.0.1-SNAPSHOT.jar \
    && wget http://193.137.11.210/fliu/kbase/biosynth-integration-0.0.1-SNAPSHOT.jar \
    && wget http://193.137.11.210/fliu/kbase/neo4j-kernel-2.1.5.jar \
    && wget http://193.137.11.210/fliu/kbase/commons-io-2.4.jar \
    && wget http://193.137.11.210/fliu/kbase/commons-math-2.2.jar \
    && wget http://193.137.11.210/fliu/kbase/commons-lang3-3.4.jar \
    && wget http://193.137.11.210/fliu/kbase/guava-18.0.jar



    
#RUN cd /kb/deployment/lib/jars \
#    && wget http://193.137.11.210/fliu/all-deps.zip && unzip all-deps.zip
#RUN wget http://193.137.11.210/fliu/all-deps.zip
#RUN unzip all-deps.zip
#RUN rm all-deps.zip
#RUN cd /kb/deployment/lib/jars \
#    && wget http://193.137.11.210/fliu/biojava-core-4.2.5.jar
RUN java -version
    
# -----------------------------------------

COPY ./ /kb/module
RUN mkdir -p /kb/module/work
RUN chmod -R a+rw /kb/module

WORKDIR /kb/module

RUN make all

ENTRYPOINT [ "./scripts/entrypoint.sh" ]

CMD [ ]
